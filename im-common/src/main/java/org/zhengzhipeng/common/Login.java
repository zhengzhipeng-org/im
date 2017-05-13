package org.zhengzhipeng.common;

/**
 * 登陆对象
 *
 * @author zhengzhipeng
 * @since 2017/5/12
 */
public class Login {

    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;

    public Login() {
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
