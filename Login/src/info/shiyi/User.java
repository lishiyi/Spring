package info.shiyi;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


public class User {
    private String username;
    private String password;
    private String email;
    private String nickname;

    public User() {
        super();
    }
    public User(String username, String password, String email, String nickname) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    @NotEmpty(message="用户名不能为空")
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty(message="密码不能为空")
    @Size(min=1,max=20,message="密码长度应在1-20之间")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Pattern(regexp="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$",message="邮箱格式不正确")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}