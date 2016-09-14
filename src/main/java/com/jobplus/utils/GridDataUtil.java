package com.jobplus.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridDataUtil implements Serializable {

    /**
     * jqgrid返回对象
     *
     * @param pageSize  每页数量
     * @param pageIndex 当前页
     * @param records      总数
     * @param list      数据
     * @return
     */
    public static Map<String, Object> getGridMap(int pageSize, int pageIndex, long records, List list) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", records % pageSize == 0 ? records / pageSize : records / pageSize + 1);
        map.put("page", pageIndex);
        map.put("records", records);
        map.put("rows", list);
        return map;
    }
}
