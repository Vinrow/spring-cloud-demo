package com.monster.framework.core.pojo.entity;

import com.monster.framework.core.utils.json.JsonUtil;

import java.io.Serializable;

public class SerializableObject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return JsonUtil.objectToString(this);
    }
}
