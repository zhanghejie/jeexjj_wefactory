package com.xjj.wefactory.wx.token;

import java.time.LocalDateTime;

public class UserToken {
    private Long userId;
    private String token;
    private LocalDateTime expireTime;
    private LocalDateTime updateTime;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
}
