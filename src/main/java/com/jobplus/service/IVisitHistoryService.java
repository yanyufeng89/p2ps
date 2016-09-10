package com.jobplus.service;

import com.jobplus.pojo.Page;
import com.jobplus.pojo.VisitHistory;

public interface IVisitHistoryService {

    int insert(VisitHistory record);

    int insertOrUpdate(VisitHistory record);
    
    int insertSelective(VisitHistory record);

    /**
     * 最近访问我的主页的人
     * @param record
     * @return
     */
    Page<VisitHistory> getRecentVistors(VisitHistory record);
}
