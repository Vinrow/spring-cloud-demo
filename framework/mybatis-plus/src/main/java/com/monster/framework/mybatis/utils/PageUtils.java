package com.monster.framework.mybatis.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.monster.framework.core.pojo.vo.PageResultVO;

import java.util.List;

public class PageUtils {

    public static  <T> PageResultVO<T> buildPage(IPage<T> page) {
        return new PageResultVO<>(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
    }

    public static  <T> PageResultVO<T> buildPage(IPage<T> page, List<T> rows) {
        return new PageResultVO<>(page.getCurrent(), page.getSize(), page.getTotal(), rows);
    }
}
