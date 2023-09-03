package com.monster.framework.core.context;

import com.monster.framework.core.pojo.entity.LoginUser;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

public final class UserContextHolder {

    /**
     * 存放在日志文件里面的用户标识，通过<strong>%X{login_user}</strong>可以打印出当前登录用户的工号信息
     * <pre class="code">
     *   logging.pattern.console = "[登陆用户:%X{login_user}] %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}"
     * </pre>
     */
    public static final String MDC_KEY_USER = "login_user";
    /**
     * 存放用户信息的线程变量
     */
    private static final ThreadLocal<LoginUser> USER_INFO_THREAD_LOCAL = new ThreadLocal<>();
    /**
     * 存放用户角色信息的线程变量
     */
    private static final ThreadLocal<List<String>> USER_ROLE_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 将登录的用户信息设置到线程变量中
     *
     * @param ssoUserBean 当前登录用户实体
     */
    public static void setUserInfo(LoginUser ssoUserBean) {
        USER_INFO_THREAD_LOCAL.set(ssoUserBean);
        MDC.put(MDC_KEY_USER, ssoUserBean.getUserName());
    }

    /**
     * 将登录的用户角色信息设置到线程变量中
     *
     * @param roles 当前登录用户的角色集合
     */
    public static void setRole(List<String> roles) {
        USER_ROLE_THREAD_LOCAL.set(roles);
    }

    /**
     * 从当前线程中获取登录的用户信息，包括工号和姓名等
     *
     * @return 登录用户信息
     */
    public static LoginUser get() {
        return USER_INFO_THREAD_LOCAL.get();
    }

    /**
     * 从当前线程中获取登录用户工号
     *
     * @return 登录用户工号
     */
    public static String getUserAccount() {
        return Optional.ofNullable(get()).map(LoginUser::getUserName).orElse(null);
    }

    /**
     * 从当前线程中获取登录用户姓名
     *
     * @return 登录用户姓名
     */
    public static String getUserName() {
        return Optional.ofNullable(get()).map(LoginUser::getDisplayName).orElse(null);
    }

    public static List<String> getRoles() {
        return USER_ROLE_THREAD_LOCAL.get();
    }

    public static boolean isUser() {
        return StringUtils.hasText(getUserAccount());
    }

    /**
     * 移除上下文的用户信息
     */
    public static void remove() {
        USER_INFO_THREAD_LOCAL.remove();
        USER_ROLE_THREAD_LOCAL.remove();
        MDC.remove(MDC_KEY_USER);
    }

}
