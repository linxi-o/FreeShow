package com.kb.search.pojo.search;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author syg
 * @version 1.0
 */
@Document(indexName = "user-infos")
public class UserInfo {

    @Id
    private Long id;

    /**
     * 用户编号,唯一
     */
    private Long userId;

    /**
     * 用户昵称
     */
    @Field(type = FieldType.Text)
    private String nickname;

    /**
     * 头像url
     */
    private String face;

    /**
     * 生日
     */
    private String birthday;
    /**
     * 签名
     */
    private String sign;

    /**
     * 性别 男/女/保密 ,0/1/2
     */
    private Integer sex;

    /**
     * 是否被禁言,0-未被禁言,1-被禁言
     */
    private Integer silence;

    /**
     * 加入时间
     */
    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
    private Date createTime;

    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
    private Date updateTime;

    /**
     * 是否删除,0-未删除,1-已删除
     */
    private Integer deleteState;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSilence() {
        return silence;
    }

    public void setSilence(Integer silence) {
        this.silence = silence;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(Integer deleteState) {
        this.deleteState = deleteState;
    }
}
