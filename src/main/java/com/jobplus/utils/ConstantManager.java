package com.jobplus.utils;

/**
 * 常亮类
 * @author Jerry
 * 2016年6月22日上午11:53:10
 *
 */
/**
 * @author Jerry
 * 2016年7月6日下午4:34:42
 *
 */
public class ConstantManager {

	/** 返回状态失败 **/
	public static final String ERROR_STATUS = "999";

	/** 返回状态成功 **/
	public static final String SUCCESS_STATUS = "000";
	
	/** 不合法 **/
	public static final String INVALID_STATUS = "-999";

	/** 返回状态等待 **/
	public static final String IN_PROGRESS = "666";

	/** 返回信息失败 **/
	public static final String ERROR_MESSAGE = "FAILED";

	/** 返回信息成功 **/
	public static final String SUCCESS_MESSAGE = "SUCCESS";
	


	/** 文档上传、下载财富默认值 */
	public static final String DEAAULT_DOWN_VALUE = "1";

	/** 文档上传、下载默认类型ID  需数据库有此值*/
	public static final String DEFAULT_DOCTYPE_ID = "101";

	/** 文档上传是否公开默认值     默认公开*/
	public static final String DEFAULT_DOC_ISPUBLISHED_PUBLISH = "1";

//	/** 话题分享成功财富默认值 */
//	public static final String DEAAULT_ADD_TOPICS_VALUE = "1";
//	/** 话题回答成功财富默认值 */
//	public static final String DEAAULT_REPLY_TOPICS_VALUE = "1";

	/** 标签类型    对应tbl_tags:tagtype字段   默认暂时0 */
	public static final String TAGS_TYPE_DEFAULT = "0";
	/** 标签类型    对应tbl_tags:tagtype字段   文档 */
	public static final String TAGS_TYPE_DOCS = "1";
	/** 标签类型    对应tbl_tags:tagtype字段   话题 */
	public static final String TAGS_TYPE_TOPICS = "2";


	/** 下载       0     对应tbl_collect:actionType */
	public static final String TBL_COLLECT_ACTIONTYPE_DOWNLOAD = "0";
	/** 收藏       1    对应tbl_collect:actionType */
	public static final String TBL_COLLECT_ACTIONTYPE_COLLECT = "1";

	/**
	 * 我的文档收藏现在区分字段
	 */
	public static final String MY_DOCS_DOWNLOADORCOLLECT_DOWNLOAD = "0";
	public static final String MY_DOCS_DOWNLOADORCOLLECT_COLLECT = "1";
	/**
	 * 话题回答列表默认排序字段     默认是按照时间倒序   sortType排序方式      1是时间排序      2是评论数排序    默认按照时间排序
	 */
	public static final String DEFAULT_SORT_TYPE_VALUE = "1";


	/**
	 * 话题评论表  type字段值  回复类型    回答1 回答评论2   话题评论3
	 */
	public static final Integer TBL_TOPICSCOMMENT_TYPE_REPLY = 1;
	public static final Integer TBL_TOPICSCOMMENT_TYPE_REPLYCOMMENT = 2;
	public static final Integer TBL_TOPICSCOMMENT_TYPE_TOPICCOMMENT = 3;


    /**
     * 首页redis缓存key
     */
    public static final String REDIS_KEY_HOTSHARE = "INDEX_DATE:HOTSHARE";//热门分享
    public static final String REDIS_KEY_LATESTSHARE = "INDEX_DATE:LATESTSHARE";//最新分享
    public static final String REDIS_KEY_HOTRECOMM = "INDEX_DATE:HOTRECOMM";//热门推荐
    
    public static final String REDIS_KEY_ALLPARENTTYPES = "INDEX_DATE:ALLPARENTTYPES";//所有的父级分类
    public static final String REDIS_KEY_ALLCHAILDTYPES = "INDEX_DATE:ALLCHAILDTYPES";//所有的自己分类
    public static final String REDIS_KEY_TWOPATYPELIST = "INDEX_DATE:TWOPATYPELIST";//首页父类
    public static final String REDIS_KEY_HOMETYPELIST = "INDEX_DATE:HOMETYPELIST";//首页分类
    public static final String REDIS_KEY_SEARCHTYPELIST = "SEARCH_DATE:SEARCHTYPELIST";//搜索页分类

	public static final String REDIS_KEY_TYPE_CONFIG= "TYPE_CONFIG:";//分类
	public static final String REDIS_KEY_TOPICS_SEARCH= "TOPICS_SEARCH:";//话题专区
	public static final long REDIS_KEY_TOPICS_SEARCH_EXPIRE= 3600;//缓存过期时间


	public static final String REDIS_KEY_SENSITIVE_WORDS = "SENSITIVE_WORDS";//敏感词
}
