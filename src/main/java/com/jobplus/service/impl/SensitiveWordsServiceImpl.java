package com.jobplus.service.impl;

import com.jobplus.service.IRedisService;
import com.jobplus.service.ISensitiveWordsService;
import com.jobplus.utils.ConstantManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service("sensitiveWordsService")
public class SensitiveWordsServiceImpl implements ISensitiveWordsService {

    @Resource
    private IRedisService redisService;

    @Override
    public List<String> getSensitiveWords() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("sensitive-words.properties"), "utf-8"));
            String tempString = null;
            List<String> list = new ArrayList<>();
            while ((tempString = reader.readLine()) != null) {
                list.add(tempString.trim());
            }
            reader.close();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return null;
    }

    @Override
    public boolean initRedisSensitiveWords() {
        List<String> words = getSensitiveWords();
        if (words != null) {
            redisService.set(ConstantManager.REDIS_KEY_SENSITIVE_WORDS, words);
            return true;
        }
        return false;
    }

    /**
     * 是否敏感词
     *
     * @param content
     * @return
     */
    @Override
    public boolean isSensitive(String content) {
        List<String> words = null;
        if (redisService.exists(ConstantManager.REDIS_KEY_SENSITIVE_WORDS)) {
            words = (List<String>) redisService.getObject(ConstantManager.REDIS_KEY_SENSITIVE_WORDS);
        } else {
            words = getSensitiveWords();
        }
        if (words != null && words.size() > 0) {
            for (String word : words) {
                if (content.indexOf(word) > -1)
                    return true;
            }
        }
        return false;
    }
}
