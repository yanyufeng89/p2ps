package com.jobplus.controller;

import com.jobplus.pojo.DataType;
import com.jobplus.pojo.OauthLoginInfo;
import com.jobplus.service.IOauthLoginInfoService;
import com.jobplus.service.ISysLoginLogService;
import com.jobplus.service.IUserService;
import com.jobplus.thirdparty.weibo4j.Users;
import com.jobplus.thirdparty.weibo4j.model.User;
import com.jobplus.thirdparty.weibo4j.model.WeiboException;
import com.jobplus.thirdparty.weibo4j.util.BareBonesBrowserLaunch;
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
                System.out.print("没有获取到响应参数");
            } else {
                accessToken = accessTokenObj.getAccessToken();
                request.getSession().setAttribute("demo_access_token", accessToken);

                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj = new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();
                System.out.println("欢迎你，代号为 " + openID + " 的用户!");
                System.out.println("<a href=" + "shuoshuoDemo.html" + " target=\"_blank\">去看看发表说说的demo吧</a>");
                // 利用获取到的accessToken 去获取当前用户的openid --------- end
                HttpClient htc = new HttpClient();
                Response rsp = htc.get("https://graph.qq.com/user/get_user_info?oauth_consumer_key=101341795&access_token=" + accessToken + "&openid=" + openID + "&format=json");
                String str2 = rsp.asString();
                /**
                 * ret	返回码
                 msg	如果ret<0，会有相应的错误信息提示，返回数据全部用UTF-8编码。
                 nickname	用户在QQ空间的昵称。
                 figureurl	大小为30×30像素的QQ空间头像URL。
                 figureurl_1	大小为50×50像素的QQ空间头像URL。
                 figureurl_2	大小为100×100像素的QQ空间头像URL。
                 figureurl_qq_1	大小为40×40像素的QQ头像URL。
                 figureurl_qq_2	大小为100×100像素的QQ头像URL。需要注意，不是所有的用户都拥有QQ的100x100的头像，但40x40像素则是一定会有。
                 gender	性别。 如果获取不到则默认返回"男"
                 is_yellow_vip	标识用户是否为黄钻用户（0：不是；1：是）。
                 vip	标识用户是否为黄钻用户（0：不是；1：是）
                 yellow_vip_level	黄钻等级
                 level	黄钻等级
                 is_yellow_year_vip	标识是否为年费黄钻用户（0：不是； 1：是）
                 */
                JSONObject job = new JSONObject(str2);
                System.out.println("<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 --------------------------- start </p>");
                System.out.println("<br/>");
                if ((int) job.get("ret") == 0) {
                    System.out.println("姓名： " + job.getString("nickname") + "<br/>");
                    System.out.println("性别： " + job.getString("gender") + "<br/>");
                    System.out.println("<image src=" + job.getString("figureurl_qq_1") + " /><br/>");
                    System.out.println("<image src=" + job.getString("figureurl_qq_2") + " /><br/>");
                } else {
                    System.out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + job.getString("msg"));
                }
                System.out.println("<p> end -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- end </p>");
                OauthLoginInfo oauthLoginInfo = new OauthLoginInfo();
                oauthLoginInfo.setOauthname(DataType.QQ.getValue());
                oauthLoginInfo.setOauthaccesstoken(accessTokenObj.getAccessToken());
                oauthLoginInfo.setOauthexpires(new Long(accessTokenObj.getExpireIn()).intValue());
                oauthLoginInfo.setOauthopenid(openID);
                return login(request, oauthLoginInfoService.getUserFromOauth(oauthLoginInfo), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            BareBonesBrowserLaunch.openURL(oauth.authorize("code"));
        } catch (WeiboException e) {
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
            com.jobplus.thirdparty.weibo4j.Oauth oauth = new com.jobplus.thirdparty.weibo4j.Oauth();
            com.jobplus.thirdparty.weibo4j.http.AccessToken accessToken = oauth.getAccessTokenByCode(code);
            if (accessToken == null) {

            } else {
                Users um = new Users(accessToken.getAccessToken());
                User user = um.showUserById(accessToken.getUid());
                System.out.println("weibo------------------------------" + user.toString());
                OauthLoginInfo oauthLoginInfo = new OauthLoginInfo();
                oauthLoginInfo.setOauthname(DataType.WEIBO.getValue());
                oauthLoginInfo.setOauthaccesstoken(accessToken.getAccessToken());
                oauthLoginInfo.setOauthexpires(Integer.parseInt(accessToken.getExpireIn()));
                oauthLoginInfo.setOauthopenid(accessToken.getUid());
                return login(request, oauthLoginInfoService.getUserFromOauth(oauthLoginInfo), null);
            }
        } catch (WeiboException e) {
            e.printStackTrace();
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
            mv.setViewName(StringUtils.isNotBlank(backurl) ? "redirect:" + backurl : "redirect:/index");
            return mv;
        }
        try {
            String username = user.getUsername();
            String password = "";
            user = userService.findUserByName(username);
            if (null != user && password.equals(user.getPasswd()) && user.getIsvalid() != 0) {
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
                mv.setViewName("redirect:/index");
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
}
