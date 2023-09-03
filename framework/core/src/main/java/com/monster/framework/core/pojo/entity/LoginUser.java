package com.monster.framework.core.pojo.entity;

public class LoginUser extends SerializableObject{
    private String userName;

    private String displayName;

    public LoginUser() {
    }

    public LoginUser(String userName,String displayName) {
        this.userName = userName;
        this.displayName = displayName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
