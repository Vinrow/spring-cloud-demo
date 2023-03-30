package com.monster.core.config;

public class ResultConfig {

    private boolean successPackageEnabled = true;

    private String successCode = "0";

    private String successMessage = "ok";

    private String errorCode = "400";

    private boolean causeEnable = false;

    public boolean isSuccessPackageEnabled() {
        return successPackageEnabled;
    }

    public void setSuccessPackageEnabled(boolean successPackageEnabled) {
        this.successPackageEnabled = successPackageEnabled;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isCauseEnable() {
        return causeEnable;
    }

    public void setCauseEnable(boolean causeEnable) {
        this.causeEnable = causeEnable;
    }
}
