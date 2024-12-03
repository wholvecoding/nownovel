package com.wolvescoding.nownovel.core.common.constant;

public class CommonConsts {
    public static final Integer YES = 1;
    public static final Integer NO = 0;
    public  enum SexEnum{
        MALE(0,"男"),
        FEMALE(1,"女");

        SexEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
        private Integer code;
        private String desc;
        public int getCode(){
            return code;
        }
        public String getDesc(){
            return desc;
        }
    }
}
