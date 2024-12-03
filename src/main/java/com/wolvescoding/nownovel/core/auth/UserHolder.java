package com.wolvescoding.nownovel.core.auth;

import lombok.experimental.UtilityClass;

@UtilityClass //默认为一个静态类
public class UserHolder {
    private static final ThreadLocal<Long> userIdTl = new ThreadLocal<>();
    private static final ThreadLocal<Long> authorIdTl = new ThreadLocal<>();
    public void setUserId(Long userId) {
        userIdTl.set(userId);
    }
    public Long getUserId() {
        return userIdTl.get();
    }
    public void setAuthor(Long author) {
        authorIdTl.set(author);
    }
    public Long getAuthor() {
        return authorIdTl.get();
    }
    public void remove() {
        userIdTl.remove();
        authorIdTl.remove();
    }
    public void setAuthorId(Long authorId){
        authorIdTl.set(authorId);
    }
    public Long getAuthorId(){
        return authorIdTl.get();
    }

}
