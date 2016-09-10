package com.jobplus.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ISensitiveWordsService {

    /**
     * 获取敏感词列表
     *
     * @return
     */
    public List<String> getSensitiveWords();

    /**
     * 初始化redis敏感词
     */
    public boolean initRedisSensitiveWords();

    /**
     * 是否敏感词
     *
     * @param content
     * @return
     */
    public boolean isSensitive(String content);
}
