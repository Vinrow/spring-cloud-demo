package com.monster.core.swagger;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

@ConfigurationProperties("uem.swagger")
public class SwaggerProperties {

    /**
     * 标题
     */
    @NotEmpty(message = "标题不能为空")
    private String title;
    /**
     * 描述
     */
    @NotEmpty(message = "描述不能为空")
    private String description;
    /**
     * 作者
     */
    @NotEmpty(message = "作者不能为空")
    private String author;
    /**
     * 版本
     */
    @NotEmpty(message = "版本不能为空")
    private String version;
    /**
     * 扫描的包
     */
    @NotEmpty(message = "扫描的 package 不能为空")
    private String basePackage;

    /**
     * 不需要auth的接口， 多个路径使用","分割
     */
    private String authExclude;

    public SwaggerProperties() {
    }

    public SwaggerProperties(String title, String description, String author, String version, String basePackage, String authExclude) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.version = version;
        this.basePackage = basePackage;
        this.authExclude = authExclude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBasePackage() {
        return basePackage;
    }


    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getAuthExclude() {
        return authExclude;
    }

    public void setAuthExclude(String authExclude) {
        this.authExclude = authExclude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwaggerProperties that = (SwaggerProperties) o;

        if (!Objects.equals(title, that.title)) return false;
        if (!Objects.equals(description, that.description)) return false;
        if (!Objects.equals(author, that.author)) return false;
        if (!Objects.equals(version, that.version)) return false;
        if (!Objects.equals(basePackage, that.basePackage)) return false;
        return Objects.equals(authExclude, that.authExclude);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (basePackage != null ? basePackage.hashCode() : 0);
        result = 31 * result + (authExclude != null ? authExclude.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SwaggerProperties{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", version='" + version + '\'' +
                ", basePackage='" + basePackage + '\'' +
                ", authExclude='" + authExclude + '\'' +
                '}';
    }
}
