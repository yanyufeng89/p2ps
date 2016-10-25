package com.jobplus.controller;

import com.jobplus.pojo.DataType;
import com.jobplus.pojo.OauthLoginInfo;
import com.jobplus.service.IOauthLoginInfoService;
import com.jobplus.service.ISysLoginLogService;
import com.jobplus.service.IUserService;
import com.jobplus.thirdparty.weibo4j.Users;
import com.jobplus.thirdparty.weibo4j.model.User;
import com.jobplus.utils.Common;
import com.jobplus.utils.HttpClientUtils;
import com.jobplus.utils.ParsProperFile;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;
import com.qq.connect.utils.http.HttpClient;
import com.qq.connect.utils.http.Response;
import com.qq.connect.utils.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
public class ThirdPartyLoginController {

    @Resource
    private IUserService userService;
    @Resource
    private ISysLoginLogService sysLoginLogService;
    @Resource
    private IOauthLoginInfoService oauthLoginInfoService;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(ThirdPartyLoginController.class);

    /**
     * qq登录
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/authorize/qq/login", method = RequestMethod.GET)
    public void qqLogin(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        try {
            response.sendRedirect(new Oauth().getAuthorizeURL(request));
        } catch (QQConnectException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * qq回调
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/authorize/qq/callback")
    public ModelAndView qqLoginCallback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            String accessToken = null;
            String openID = null;
            if (accessTokenObj.getAccessToken().equals("")) {
                ModelAndView mv = new ModelAndView();
                mv.setViewName("authorize");
                return mv;
            } else {
                accessToken = accessTokenObj.getAccessToken();
                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj = new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();

                OauthLoginInfo oauthLoginInfo = new OauthLoginInfo();
                oauthLoginInfo.setOauthname(DataType.QQ.getValue());
                oauthLoginInfo.setOauthaccesstoken(accessTokenObj.getAccessToken());
                oauthLoginInfo.setOauthexpires(new Long(accessTokenObj.getExpireIn()).intValue());
                oauthLoginInfo.setOauthopenid(openID);
                OauthLoginInfo loginInfo = oauthLoginInfoService.selectByNameAndOpenId(oauthLoginInfo);
                if (loginInfo == null) {
                    HttpClient htc = new HttpClient();
                    Response rsp = htc.get("https://graph.qq.com/user/get_user_info?oauth_consumer_key=" + ParsProperFile.getQQConfigString("app_ID") + "&access_token=" + accessToken + "&openid=" + openID + "&format=json");
                    JSONObject userObj = new JSONObject(rsp.asString());
                    oauthLoginInfo.setNickname(Common.filterEmoji(userObj.getString("nickname")));
                    oauthLoginInfo.setHeadicon(userObj.getString("figureurl_qq_1"));
                }
                return login(request, oauthLoginInfoService.getUserFromOauth(loginInfo, oauthLoginInfo), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("500");
        return mv;
    }

    /**
     * 微博登录
     *
     * @param response
     */
    @RequestMapping(value = "/authorize/weibo/login", method = RequestMethod.GET)
    public void weiboLogin(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        try {
            com.jobplus.thirdparty.weibo4j.Oauth oauth = new com.jobplus.thirdparty.weibo4j.Oauth();
            response.sendRedirect(oauth.authorize("code"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 微博回调
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/authorize/weibo/callback")
    public ModelAndView weiboLoginCallback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String code = request.getParameter("code");
            String error_code = request.getParameter("error_code");
            if (StringUtils.isNotBlank(error_code) && "21330".equals(error_code)) {
                ModelAndView mv = new ModelAndView();
                mv.setViewName("index");
                return mv;
            }
            com.jobplus.thirdparty.weibo4j.Oauth oauth = new com.jobplus.thirdparty.weibo4j.Oauth();
            com.jobplus.thirdparty.weibo4j.http.AccessToken accessToken = oauth.getAccessTokenByCode(code);
            if (accessToken == null) {
                ModelAndView mv = new ModelAndView();
                mv.setViewName("authorize");
                return mv;
            } else {
                Users um = new Users(accessToken.getAccessToken());
                OauthLoginInfo oauthLoginInfo = new OauthLoginInfo();
                oauthLoginInfo.setOauthname(DataType.WEIBO.getValue());
                oauthLoginInfo.setOauthopenid(accessToken.getUid());
                oauthLoginInfo.setOauthaccesstoken(accessToken.getAccessToken());
                oauthLoginInfo.setOauthexpires(Integer.parseInt(accessToken.getExpireIn()));
                OauthLoginInfo loginInfo = oauthLoginInfoService.selectByNameAndOpenId(oauthLoginInfo);
                if (loginInfo == null) {
                    User user = um.showUserById(accessToken.getUid());
                    oauthLoginInfo.setNickname(user.getName());
                    oauthLoginInfo.setHeadicon(user.getProfileImageUrl());
                }
                return login(request, oauthLoginInfoService.getUserFromOauth(loginInfo, oauthLoginInfo), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("500");
        return mv;
    }

    /**
     * 微信登录
     *
     * @param response
     */
    @RequestMapping(value = "/authorize/wechat/login", method = RequestMethod.GET)
    public void wechatLogin(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        try {
            String url = "https://open.weixin.qq.com/connect/qrconnect?appid=" + ParsProperFile.getString("wechat.AppID") + "&redirect_uri=" + ParsProperFile.getString("wechat.redirect_URI") + "&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect";
            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
    }

    /**
     * 微信回调
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/authorize/wechat/callback")
    public ModelAndView wechatLoginCallback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String code = request.getParameter("code");
            //获取access_token
            String tokenStr = HttpClientUtils.doGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + ParsProperFile.getString("wechat.AppID") + "&secret=" + ParsProperFile.getString("wechat.AppSecret") + "&code=" + code + "&grant_type=authorization_code");
            if (StringUtils.isBlank(tokenStr) || tokenStr.indexOf("errcode") > -1) {
                ModelAndView mv = new ModelAndView();
                mv.setViewName("authorize");
                return mv;
            }
            JSONObject tokenObj = new JSONObject(tokenStr);
            String access_token = tokenObj.getString("access_token");
            String expires_in = tokenObj.getString("expires_in");
            String openid = tokenObj.getString("openid");
            OauthLoginInfo oauthLoginInfo = new OauthLoginInfo();
            oauthLoginInfo.setOauthname(DataType.WECHAT.getValue());
            oauthLoginInfo.setOauthaccesstoken(access_token);
            oauthLoginInfo.setOauthexpires(Integer.parseInt(expires_in));
            oauthLoginInfo.setOauthopenid(openid);
            OauthLoginInfo loginInfo = oauthLoginInfoService.selectByNameAndOpenId(oauthLoginInfo);
            //获取用户信息
            if (loginInfo == null) {
                String userStr = HttpClientUtils.doGet("https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid);
                JSONObject userObj = new JSONObject(userStr);
                oauthLoginInfo.setNickname(Common.filterEmoji(transStringCoding(userObj.getString("nickname"))));
                oauthLoginInfo.setHeadicon(userObj.getString("headimgurl"));
                if (StringUtils.isBlank(oauthLoginInfo.getNickname())) {
                    logger.info("******获取用户信息失败******access_token=" + access_token + "&openid=" + openid + ">>>>>>>>result:" + userStr);
                    ModelAndView mv = new ModelAndView();
                    mv.setViewName("authorize");
                    return mv;
                }
            }
            return login(request, oauthLoginInfoService.getUserFromOauth(loginInfo, oauthLoginInfo), null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("500");
        return mv;
    }

    /**
     * 模拟登陆
     *
     * @param request
     * @param user
     * @param backurl
     * @return
     */
    public ModelAndView login(HttpServletRequest request, com.jobplus.pojo.User user, String backurl) {
        ModelAndView mv = new ModelAndView();
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            mv.setViewName(StringUtils.isNotBlank(backurl) ? "redirect:" + backurl : "redirect:/index/loginSuccess");
            return mv;
        }
        try {
            String username = user.getUsername();
            String password = "";
            user = userService.findUserByName(username);
            if (user != null && user.getIsvalid().intValue() != 0) {
                username = String.valueOf(user.getUserid());
            } else {
                // 返回前端数据设置
                mv.addObject("message", "用户名或密码错误");
                mv.addObject("backurl", backurl);
                // 返回视图名设置
                mv.setViewName("login");
                return mv;
            }
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            // 获取当前的Subject
            // 使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！
            currentUser.login(token);
            if (currentUser.isAuthenticated()) {
                currentUser.getSession().setAttribute("userid", username);
                currentUser.getSession().setAttribute("user", user);
                //记录登录日志
                sysLoginLogService.insert(request, user);
                logger.info("用户[" + username + "]登录认证通过");
                //个人操作数之类的信息放入session
                userService.getMyHeadTopAndOper(request);
                //更新用消息统计数 放入session
                userService.getSmsOper(request);
            } else {
                token.clear();
            }
            if (StringUtils.isNotBlank(backurl)) {
                mv.setViewName("redirect:" + backurl);
            } else {
                // 返回前端数据设置
                mv.addObject("message", "登录成功");
                mv.addObject("user", user);
                // 返回视图名设置
                mv.setViewName("redirect:/index/loginSuccess");
            }
            return mv;
        } catch (AuthenticationException e) {
            // 返回前端数据设置
            mv.addObject("message", "用户名或密码错误");
            mv.addObject("backurl", backurl);
            // 返回视图名设置
            mv.setViewName("login");
            return mv;
        }
    }

    public String transStringCoding(String str) throws UnsupportedEncodingException {
        if (StringUtils.isNotBlank(str))
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        return null;
    }
}
