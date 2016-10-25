package com.jobplus.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jobplus.pojo.Article;
import com.jobplus.pojo.ArticleShare;
import com.jobplus.pojo.BookShare;
import com.jobplus.pojo.Books;
import com.jobplus.pojo.Courses;
import com.jobplus.pojo.CoursesShare;
import com.jobplus.pojo.DocComment;
import com.jobplus.pojo.Docs;
import com.jobplus.pojo.GridQuery;
import com.jobplus.pojo.ReportInfo;
import com.jobplus.pojo.SiteShare;
import com.jobplus.pojo.Sites;
import com.jobplus.pojo.Suggestion;
import com.jobplus.pojo.Topics;
import com.jobplus.pojo.TopicsComment;
import com.jobplus.pojo.User;
import com.jobplus.pojo.response.BaseResponse;
import com.jobplus.service.IAccountDetailService;
import com.jobplus.service.IArticleService;
import com.jobplus.service.IArticleShareService;
import com.jobplus.service.IBookShareService;
import com.jobplus.service.IBooksService;
import com.jobplus.service.ICoursesService;
import com.jobplus.service.ICoursesShareService;
import com.jobplus.service.IDocCommentService;
import com.jobplus.service.IDocsService;
import com.jobplus.service.IReportInfoService;
import com.jobplus.service.ISiteShareService;
import com.jobplus.service.ISitesService;
import com.jobplus.service.ISuggestionService;
import com.jobplus.service.ISysLoginLogService;
import com.jobplus.service.ITopicsCommentService;
import com.jobplus.service.ITopicsService;
import com.jobplus.service.IUpdTableColumnService;
import com.jobplus.service.IUserService;
import com.jobplus.service.impl.SmsServiceImpl;
import com.jobplus.utils.ConstantManager;
import com.jobplus.utils.SHAUtil;

@Controller
@RequestMapping("/manage")
public class ManageController {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(ManageController.class);
    protected Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    @Resource
    private IUserService userService;
    @Resource
    private IAccountDetailService accountDetailService;
    @Resource
    private ISuggestionService suggestionService;
    @Resource
    private IReportInfoService reportService;
    @Resource
    private ISysLoginLogService sysLoginLogService;
    @Resource
    private IDocsService docsService;
    @Resource
    private ITopicsService topicsService;
    @Resource
    private IBooksService booksService;
    @Resource
    private ICoursesService coursesService;
    @Resource
    private ISitesService sitesService;
    @Resource
    private IArticleService articleService;
    @Resource
    private IDocCommentService docCommentService;
    @Resource
    private ITopicsCommentService topicsCommentService;
    @Resource
    private IBookShareService bookShareService;
    @Resource
    private ICoursesShareService coursesShareService;
    @Resource
    private ISiteShareService siteShareService;
    @Resource
    private IArticleShareService articleShareService;
    @Resource
    private IUpdTableColumnService updTableColumnService;

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage/login");
        return mv;
    }

    /**
     * 登录
     *
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, User user) {
        ModelAndView mv = new ModelAndView();
        Subject currentUser = SecurityUtils.getSubject();
        try {
            String username = user.getUsername();
            String password = SHAUtil.shaEncode(user.getPasswd());
            user = userService.findUserByName(username);
            if (null != user && user.getUsertype() != null && user.getUsertype() == 9 && password.equals(user.getPasswd()) && user.getIsvalid() != 0) {
                username = String.valueOf(user.getUserid());
            } else {
                if (null == user || !password.equals(user.getPasswd()) || user.getIsvalid() == 0)
                    mv.addObject("message", "用户名或密码错误");
                else if (user.getUsertype() == null || user.getUsertype() != 9)
                    mv.addObject("message", "非管理员无法登录");
                // 返回视图名设置
                mv.setViewName("manage/login");
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
            // 返回前端数据设置
            mv.addObject("message", "登录成功");
            mv.addObject("user", user);
            // 返回视图名设置
            mv.setViewName("redirect:/manage/backstage/main");
            return mv;
        } catch (AuthenticationException e) {
            // 返回前端数据设置
            mv.addObject("message", "用户名或密码错误");
            // 返回视图名设置
            mv.setViewName("manage/login");
            return mv;
        }
    }

    @RequestMapping(value = "/backstage/logout")
    public ModelAndView logout() {
        // 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        ModelAndView mv = new ModelAndView();
        // 返回视图名设置
        mv.setViewName("manage/login");
        logger.info("用户[" + SecurityUtils.getSubject().getPrincipal() + "]退出登录!");
        return mv;
    }

    @RequestMapping(value = "/backstage/main", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage/main");
        return mv;
    }

    @RequestMapping(value = "/backstage/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage/index");
        return mv;
    }

    @RequestMapping(value = "/backstage/resource", method = RequestMethod.GET)
    public ModelAndView resource() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage/resource");
        return mv;
    }

    @RequestMapping(value = "/backstage/user", method = RequestMethod.GET)
    public ModelAndView user() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage/user");
        return mv;
    }

    @RequestMapping(value = "/backstage/user", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String user(@RequestBody GridQuery<User> gridQuery) {
        return gson.toJson(userService.getAllUsers(gridQuery));
    }

    @RequestMapping(value = "/backstage/user/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String userDelete(@PathVariable int id) {
        return String.valueOf(userService.delete(id));
    }

    @RequestMapping(value = "/backstage/sensitive", method = RequestMethod.GET)
    public ModelAndView sensitive() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage/index");
        return mv;
    }

    /**
     * 获取所有的建议反馈  分页
     */
    @RequestMapping(value = "/backstage/suggest", method = RequestMethod.GET)
    public ModelAndView getAllSug() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage/suggest");
        return mv;
    }

    @RequestMapping(value = "/backstage/suggest", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAllSug(@RequestBody GridQuery<Suggestion> gridQuery) {
        return gson.toJson(suggestionService.getAllSug(gridQuery));
    }

    @RequestMapping(value = "/backstage/suggest/deal/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String dealSug(@PathVariable int id) {
        return String.valueOf(suggestionService.dealSug(id));
    }

    /**
     * 获取所有举报信息     *
     */
    @RequestMapping(value = "/backstage/complaints", method = RequestMethod.GET)
    public ModelAndView getAllReport() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage/complaints");
        return mv;
    }

    @RequestMapping(value = "/backstage/complaints", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAllReport(@RequestBody GridQuery<ReportInfo> gridQuery) {
        return gson.toJson(reportService.getAllReportInfo(gridQuery));
    }


    @RequestMapping(value = "/backstage/complaints/deal/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String dealComplaints(@PathVariable int id) {
        return String.valueOf(reportService.dealComplaints(id));
    }

    /**
     * 根据id type获取obj
     * TABLENAMES =  new String[]{"tbl_docs","tbl_books","tbl_topics","tbl_courses","tbl_article","tbl_sites", 5
     * "tbl_docs_comment","tbl_books_share","tbl_topics_comment","tbl_courses_share","tbl_article_share","tbl_sites_share"};
     *
     * @param request
     * @param response
     * @param id
     * @param type
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @RequestMapping(value = "/backstage/getOneObj", method = RequestMethod.POST)
    @ResponseBody
    public String getOneObj(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true) Integer id,
                            @RequestParam(required = true) String type) {
        BaseResponse baseResponse = new BaseResponse();
        Map retMap = new HashMap<>();
        try {
            switch (type) {
                case "0": {
                    //文档
                    Docs obj = docsService.selectByPrimaryKey(id);
                    retMap.put("objId", id);
                    retMap.put("objCreator", obj.getUserid());
                    retMap.put("objName", obj.getTitle());
                    retMap.put("objTblName", "tbl_docs");
                    //
                    retMap.put("objUrl", request.getContextPath() + SmsServiceImpl.urlMap.get("tbl_docs") + id + "&isAdmin=7");
                    break;
                }
                case "1": {
                    //书籍
                    Books obj = booksService.selectByPrimaryKey(id);
                    retMap.put("objId", id);
                    retMap.put("objCreator", "");
                    retMap.put("objName", obj.getBookname());
                    retMap.put("objTblName", "tbl_books");
                    retMap.put("objUrl", request.getContextPath() + SmsServiceImpl.urlMap.get("tbl_books") + id + "&isAdmin=7");
                    break;
                }
                case "2": {
                    //话题
                    Topics obj = topicsService.selectByPrimaryKey(id);
                    retMap.put("objId", id);
                    retMap.put("objCreator", obj.getCreateperson());
                    retMap.put("objName", obj.getTitle());
                    retMap.put("objTblName", "tbl_topics");
                    retMap.put("objUrl", request.getContextPath() + SmsServiceImpl.urlMap.get("tbl_topics") + id + "&isAdmin=7");
                    break;
                }
                case "3": {
                    //课程
                    Courses obj = coursesService.selectByPrimaryKey(id);
                    retMap.put("objId", id);
                    retMap.put("objCreator", obj.getUserid());
                    retMap.put("objName", obj.getCoursesname());
                    retMap.put("objTblName", "tbl_courses");
                    retMap.put("objUrl", request.getContextPath() + SmsServiceImpl.urlMap.get("tbl_courses") + id + "&isAdmin=7");
                    break;
                }
                case "4": {
                    //文章
                    Article obj = articleService.selectByPrimaryKey(id);
                    retMap.put("objId", id);
                    retMap.put("objCreator", obj.getUserid());
                    retMap.put("objName", obj.getTitle());
                    retMap.put("objTblName", "tbl_article");
                    retMap.put("objUrl", request.getContextPath() + SmsServiceImpl.urlMap.get("tbl_article") + id + "&isAdmin=7");
                    break;
                }
                case "5": {
                    //站点
                    Sites obj = sitesService.selectByPrimaryKey(id);
                    retMap.put("objId", id);
                    retMap.put("objCreator", obj.getUserid());
                    retMap.put("objName", obj.getTitle());
                    retMap.put("objTblName", "tbl_sites");
                    retMap.put("objUrl", request.getContextPath() + SmsServiceImpl.urlMap.get("tbl_sites") + id + "&isAdmin=7");
                    break;
                }
                case "6": {
                    //文档评论
                    DocComment obj = docCommentService.selectByPrimaryKey(id);
                    retMap.put("objId", id);
                    retMap.put("objCreator", obj.getUserid());
                    retMap.put("objName", obj.getComments());
                    retMap.put("objTblName", "tbl_docs_comment");
                    retMap.put("objUrl", request.getContextPath() + SmsServiceImpl.urlMap.get("tbl_docs") + obj.getDocid() + "&isAdmin=7");
                    break;
                }
                case "7": {
                    //书籍评论
                    BookShare obj = bookShareService.selectByPrimaryKey(id);
                    retMap.put("objId", id);
                    retMap.put("objCreator", obj.getUserid());
                    retMap.put("objName", obj.getRecommend());
                    retMap.put("objTblName", "tbl_books_share");
                    retMap.put("objUrl", request.getContextPath() + SmsServiceImpl.urlMap.get("tbl_books") + obj.getBookid() + "&isAdmin=7");
                    break;
                }
                case "8": {
                    //话题评论
                    TopicsComment obj = topicsCommentService.selectByPrimaryKey(id);
                    retMap.put("objId", id);
                    retMap.put("objCreator", obj.getUserid());
                    retMap.put("objName", obj.getCommcontent());
                    retMap.put("objTblName", "tbl_topics_comment");
                    retMap.put("objUrl", request.getContextPath() + SmsServiceImpl.urlMap.get("tbl_topics") + obj.getTopicsid() + "&isAdmin=7");
                    break;
                }
                case "9": {
                    //课程评论
                    CoursesShare obj = coursesShareService.selectByPrimaryKey(id);
                    retMap.put("objId", id);
                    retMap.put("objCreator", obj.getUserid());
                    retMap.put("objName", obj.getRecommend());
                    retMap.put("objTblName", "tbl_courses_share");
                    retMap.put("objUrl", request.getContextPath() + SmsServiceImpl.urlMap.get("tbl_courses") + obj.getCoursesid() + "&isAdmin=7");
                    break;
                }
                case "10": {
                    //文章评论
                    ArticleShare obj = articleShareService.selectByPrimaryKey(id);
                    retMap.put("objId", id);
                    retMap.put("objCreator", obj.getUserid());
                    retMap.put("objName", obj.getRecommend());
                    retMap.put("objTblName", "tbl_article_share");
                    retMap.put("objUrl", request.getContextPath() + SmsServiceImpl.urlMap.get("tbl_article") + obj.getArticleid() + "&isAdmin=7");
                    break;
                }
                case "11": {
                    //站点评论
                    SiteShare obj = siteShareService.selectByPrimaryKey(id);
                    retMap.put("objId", id);
                    retMap.put("objCreator", obj.getUserid());
                    retMap.put("objName", obj.getRecommend());
                    retMap.put("objTblName", "tbl_sites_share");
                    retMap.put("objUrl", request.getContextPath() + SmsServiceImpl.urlMap.get("tbl_sites") + obj.getSiteid() + "&isAdmin=7");
                    break;
                }

                default:
                    break;
            }
            logger.info("**getOneObj*根据id获取某个obj****=");
            baseResponse.setObj(retMap);
            baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
            baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
            return JSON.toJSONString(baseResponse);

        } catch (Exception e) {
            baseResponse.setReturnMsg(e.getMessage());
            baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
            logger.info("**getOneObj*根据id获取某个ob    失败  999 *********" + e.getMessage());
            return JSON.toJSONString(baseResponse);
        }
    }


    /**
     * 删除obj或者评论
     * TABLENAMES =  new String[]{"tbl_docs","tbl_books","tbl_topics","tbl_courses","tbl_article","tbl_sites", 5
     * "tbl_docs_comment","tbl_books_share","tbl_topics_comment","tbl_courses_share","tbl_article_share","tbl_sites_share"};
     *
     * @return
     */
    @RequestMapping(value = "/backstage/delObjOrComment", method = RequestMethod.POST)//, method = RequestMethod.POST
    @ResponseBody
    public String delObjOrComment(HttpServletRequest request, @RequestParam(required = true) Integer id, @RequestParam(required = true) String tableName) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            int ret = 0;
            ret = updTableColumnService.delOneById(tableName, id);
            if (ret > 0) {
                logger.info("*delComment**--删除obj或者评论**record==");
                baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
            } else {
                baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
                logger.info("*delComment** --删除obj或者评论    失败  999   删除失败*********");
            }
            return JSON.toJSONString(baseResponse);
        } catch (Exception e) {
            baseResponse.setReturnMsg(e.getMessage());
            baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
            logger.info("*delComment** --删除obj或者评论    失败  999 *********" + e.getMessage());
            return JSON.toJSONString(baseResponse);
        }
    }

    @RequestMapping(value = "/backstage/course/{id}", method = RequestMethod.GET)
    public ModelAndView getCourse(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("course", coursesService.selectByPrimaryKey(id));
        mv.setViewName("manage/editCourse");
        return mv;
    }

    @RequestMapping(value = "/backstage/updateCourse")
    @ResponseBody
    public String updateCourse(HttpServletRequest request, HttpServletResponse response, Courses courses) {
        if (courses.getCoursesimgFile() != null && StringUtils.isNotBlank(courses.getCoursesimgFile().getOriginalFilename()))
            return gson.toJson(coursesService.updateByAdmin(courses.getCoursesimgFile(), request, response, courses));
        return gson.toJson(coursesService.updateByAdmin(courses));
    }
}
