package com.wolvescoding.nownovel.core.constant;
/*
缓存相关常量
 */
public class CacheConsts {
    public static final String REDIS_CACHE_PREFIX = "Cache::Novel::";
    public static final String CAFFEINE_CACHE_MANAGER ="CaffeineCacheManager";
    public static final String REDIS_CACHE_MANAGER = "RedisCacheManager";
    public static final String HOME_BOOK_CACHE_NAME = "HomeBookCache";
    public static final String HOME_FRIEND_LINK_CACHE_NAME = "HomeFriendLinkCache";
    public static final String IMG_VERIFY_CODE_CACHE_KEY ="imgVerifyCodeCacheKey" ;
    public static final String USER_INFO_CACHE_NAME = "userInfoCache";

    public enum CacheEnum{
        HOME_BOOK_CACHE(1,HOME_BOOK_CACHE_NAME, 0, 1),
        HOME_FRIEND_LINK_CACHE(2, HOME_FRIEND_LINK_CACHE_NAME, 1000, 1);
        //缓存类型 0 -本地 1-本地和远程 2-远程
        private int type;
        //缓存名称
        private String name;
        //失效时间 0 -永久
        private int ttl;
        private int maxSize;
        CacheEnum(int type, String name, int ttl, int maxSize){
            this.type = type;
            this.name = name;
            this.ttl = ttl;
            this.maxSize = maxSize;
        }
        public boolean isLocal(){
            return type<=1;
        }
        public boolean isRemote() {
            return type >= 1;
        }
        public String getName(){
            return name;
        }
        public int getTtl(){
            return ttl;
        }
        public int getMaxSize(){
            return maxSize;
        }
    }
}
