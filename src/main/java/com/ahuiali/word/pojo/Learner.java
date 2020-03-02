package com.ahuiali.word.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shkstart on 2019/10/20
 */
public class Learner implements Serializable {

    private Integer id;

    //激活码
    private String activecode;

    //密码
    private String password;

    //昵称，唯一
    private String nickname;

    //邮箱，唯一
    private String email;

    //状态 0未激活，1 正常， 2 封禁中
    private Integer status;

    //创建日期
    private Date created;

    //修改日期
    private Date modified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setActivecode(String activecode) {
        this.activecode = activecode;
    }

    public String getActivecode() {
        return activecode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "Learner{" +
                "id=" + id +
                ", activecode='" + activecode + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
