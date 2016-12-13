package com.jobplus.controller;

import com.alibaba.fastjson.JSON;
import com.jobplus.pojo.DataType;
import com.jobplus.pojo.OauthLoginInfo;
import com.jobplus.pojo.response.BaseResponse;
import com.jobplus.service.IOauthLoginInfoService;
import com.jobplus.service.ISysLoginLogService;
import com.jobplus.service.IUserService;
import com.jobplus.thirdparty.weibo4j.Users;
import com.jobplus.thirdparty.weibo4j.model.User;
import com.jobplus.thirdparty.weibo4j.model.WeiboException;
import com.jobplus.thirdparty.weibo4j.util.WeiboConfig;
import com.jobplus.utils.Common;
import com.jobplus.utils.ConstantManager;
import com.jobplus.utils.HttpClientUtils;
import com.jobplus.utils.ParsProperFile;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;
import com.qq.connect.utils.QQConnectConfig;
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
import org.springframework.web.bind.annotation.*;
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
            updateQQRedirectUrl("0");
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
     * @throws IOException
     */
    @RequestMapping(value = "/authorize/qq/callback")
    public ModelAndView qqLoginCallback(HttpServletRequest request, @RequestParam(required = false) Integer type) throws IOException {
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            String accessToken = null;
            String openID = null;
            if (accessTokenObj.getAccessToken().equals("")) {
                if (type == 1) {
                    ModelAndView mv = new ModelAndView();
                    mv.addObject("returnStatus", ConstantManager.ERROR_STATUS);
                    mv.setViewName("bind");
                    return mv;
                } else {
                    ModelAndView mv = new ModelAndView();
                    mv.setViewName("authorize");
                    return mv;
                }
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
                    oauthLoginInfo.setHeadicon(userObj.getString("figureurl_qq_2"));
                }
                if (type == 1) {//绑定
                    ModelAndView mv = new ModelAndView();
                    if (loginInfo != null && loginInfo.getUserid() != null) {
                        mv.addObject("returnMsg", "该QQ已绑定账号");
                        mv.addObject("returnStatus", ConstantManager.INVALID_STATUS);
                    } else {
                        oauthLoginInfoService.bindUserFromOauth(loginInfo, oauthLoginInfo);
                        mv.addObject("returnMsg", ConstantManager.SUCCESS_MESSAGE);
                        mv.addObject("returnStatus", ConstantManager.SUCCESS_STATUS);
                    }
                    mv.setViewName("bind");
                    return mv;
                } else {//第三方登录
                    return login(request, oauthLoginInfoService.getUserFromOauth(loginInfo, oauthLoginInfo), null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        if (type == 1) {
            ModelAndView mv = new ModelAndView();
            mv.addObject("returnStatus", ConstantManager.ERROR_STATUS);
            mv.setViewName("bind");
            return mv;
        } else {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("500");
            return mv;
        }
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
            updateWeiboRedirectUrl(0);
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
                    oauthLoginInfo.setHeadicon(user.getAvatarLarge());
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
            response.sendRedirect(getWeChatRedirectUrl(0));
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
     * QQ绑定
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/authorize/qq/bind", method = RequestMethod.GET)
    public void qqBind(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        try {
            updateQQRedirectUrl("1");
            response.sendRedirect(new Oauth().getAuthorizeURL(request));
        } catch (QQConnectException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * weibo绑定
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/authorize/weibo/bind", method = RequestMethod.GET)
    public void weiboBind(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        try {
            updateWeiboRedirectUrl(1);
            com.jobplus.thirdparty.weibo4j.Oauth oauth = new com.jobplus.thirdparty.weibo4j.Oauth();
            response.sendRedirect(oauth.authorize("code"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WeiboException e) {
            e.printStackTrace();
        }
    }

    /**
     * wechat绑定
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/authorize/wechat/bind", method = RequestMethod.GET)
    public void wechatBind(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        try {
            response.sendRedirect(getWeChatRedirectUrl(1));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
    }

    /**
     * 绑定微博回调
     *
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/authorize/weibo/callback/bind")
    @ResponseBody
    public ModelAndView weiboBindCallback(HttpServletRequest request) throws IOException {
        ModelAndView mv = new ModelAndView();
        try {
            String code = request.getParameter("code");
            String error_code = request.getParameter("error_code");
            if (StringUtils.isNotBlank(error_code) && "21330".equals(error_code)) {
                mv.addObject("returnMsg", error_code);
                mv.addObject("returnStatus", ConstantManager.ERROR_STATUS);
            } else {
                com.jobplus.thirdparty.weibo4j.Oauth oauth = new com.jobplus.thirdparty.weibo4j.Oauth();
                com.jobplus.thirdparty.weibo4j.http.AccessToken accessToken = oauth.getAccessTokenByCode(code);
                if (accessToken == null) {
                    mv.addObject("returnMsg", "授权失败");
                    mv.addObject("returnStatus", ConstantManager.ERROR_STATUS);
                } else {
                    Users um = new Users(accessToken.getAccessToken());
                    OauthLoginInfo oauthLoginInfo = new OauthLoginInfo();
                    oauthLoginInfo.setOauthname(DataType.WEIBO.getValue());
                    oauthLoginInfo.setOauthopenid(accessToken.getUid());
                    oauthLoginInfo.setOauthaccesstoken(accessToken.getAccessToken());
                    oauthLoginInfo.setOauthexpires(Integer.parseInt(accessToken.getExpireIn()));
                    OauthLoginInfo loginInfo = oauthLoginInfoService.selectByNameAndOpenId(oauthLoginInfo);
                    if (loginInfo != null && loginInfo.getUserid() != null) {
                        mv.addObject("returnMsg", "该微博已绑定账号");
                        mv.addObject("returnStatus", ConstantManager.INVALID_STATUS);
                    } else {
                        User user = um.showUserById(accessToken.getUid());
                        oauthLoginInfo.setNickname(user.getName());
                        oauthLoginInfo.setHeadicon(user.getAvatarLarge());
                        oauthLoginInfoService.bindUserFromOauth(loginInfo, oauthLoginInfo);
                        mv.addObject("returnMsg", ConstantManager.SUCCESS_MESSAGE);
                        mv.addObject("returnStatus", ConstantManager.SUCCESS_STATUS);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject("returnMsg", e.getMessage());
            mv.addObject("returnStatus", ConstantManager.ERROR_STATUS);
        }
        mv.setViewName("bind");
        return mv;
    }


    /**
     * 绑定微信回调
     *
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/authorize/wechat/callback/bind")
    @ResponseBody
    public ModelAndView wechatBindCallback(HttpServletRequest request) throws IOException {
        ModelAndView mv = new ModelAndView();
        try {
            String code = request.getParameter("code");
            //获取access_token
            String tokenStr = HttpClientUtils.doGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + ParsProperFile.getString("wechat.AppID") + "&secret=" + ParsProperFile.getString("wechat.AppSecret") + "&code=" + code + "&grant_type=authorization_code");
            if (StringUtils.isBlank(tokenStr) || tokenStr.indexOf("errcode") > -1) {
                mv.addObject("returnMsg", "授权失败");
                mv.addObject("returnStatus", ConstantManager.ERROR_STATUS);
            } else {
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
                if (loginInfo != null && loginInfo.getUserid() != null) {
                    mv.addObject("returnMsg", "该微信已绑定账号");
                    mv.addObject("returnStatus", ConstantManager.INVALID_STATUS);
                } else {
                    String userStr = HttpClientUtils.doGet("https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid);
                    JSONObject userObj = new JSONObject(userStr);
                    oauthLoginInfo.setNickname(Common.filterEmoji(transStringCoding(userObj.getString("nickname"))));
                    oauthLoginInfo.setHeadicon(userObj.getString("headimgurl"));
                    oauthLoginInfoService.bindUserFromOauth(loginInfo, oauthLoginInfo);
                    mv.addObject("returnMsg", ConstantManager.SUCCESS_MESSAGE);
                    mv.addObject("returnStatus", ConstantManager.SUCCESS_STATUS);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject("returnMsg", e.getMessage());
            mv.addObject("returnStatus", ConstantManager.ERROR_STATUS);
        }
        mv.setViewName("bind");
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
            String password = user.getPasswd();
            if (user.getUserid() == null) {
                user = userService.findUserByName(username);
                password = "";
            }
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

    /**
     * 解绑
     *
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/account/unbind/{id}")
    @ResponseBody
    public String unBind(@PathVariable int id) throws IOException {
        BaseResponse baseResponse = new BaseResponse();
        try {
            int status = oauthLoginInfoService.unbindUserFromOauth(id);
            if (status == -1) {
                baseResponse.setReturnMsg("无权解绑");
                baseResponse.setReturnStatus(ConstantManager.INVALID_STATUS);
            } else {
                baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
                baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
            }
            return JSON.toJSONString(baseResponse);
        } catch (Exception e) {
            e.printStackTrace();
            baseResponse.setReturnMsg(e.getMessage());
            baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
            return JSON.toJSONString(baseResponse);
        }
    }

    public String transStringCoding(String str) throws UnsupportedEncodingException {
        if (StringUtils.isNotBlank(str))
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        return null;
    }

    /**
     * 更新QQ回调参数
     *
     * @param type
     */
    public void updateQQRedirectUrl(String type) {
        String redirect_URI = QQConnectConfig.getValue("redirect_URI");
        QQConnectConfig.updateProperties("redirect_URI", (redirect_URI.substring(0, redirect_URI.length() - 1) + type));
    }

    /**
     * 更新微博回调url
     *
     * @param type
     */
    public void updateWeiboRedirectUrl(int type) {
        String redirect_URI = WeiboConfig.getValue("redirect_URI");
        if (type == 0)
            WeiboConfig.updateProperties("redirect_URI", redirect_URI.endsWith("/bind") ? redirect_URI.replace("/bind", "") : redirect_URI);
        else if (type == 1)
            WeiboConfig.updateProperties("redirect_URI", redirect_URI.endsWith("/bind") ? redirect_URI : redirect_URI + "/bind");
    }

    /**
     * 获取微信回调url
     *
     * @param type
     */
    public String getWeChatRedirectUrl(int type) {
        String url = null;
        if (type == 0)
            url = ParsProperFile.getString("wechat.redirect_URI");
        else if (type == 1)
            url = ParsProperFile.getString("wechat.bind_redirect_URI");
        return "https://open.weixin.qq.com/connect/qrconnect?appid=" + ParsProperFile.getString("wechat.AppID") + "&redirect_uri=" + url + "&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect";
    }
}
