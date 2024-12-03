package com.wolvescoding.nownovel.core.constant;


public class ApiRouterConsts {
  public static final String API_URL_PREFIX = "/api";
    //前台门户系统
    public static final String API_FRONT_URL_PREFIX = API_URL_PREFIX+"/front";
    public static final  String API_AUTHOR_URL_PREFIX = API_URL_PREFIX+"/author";
    public static final  String API_ADMIN_URL_PREFIX = API_URL_PREFIX+"/admin";
    public static final  String HOME_URL_PREFIX = "/home";
    public static final  String BOOK_URL_PRRFIX = "/book";
    //会员路径
    public static final String USER_URL_PREFIX = "/user";
    public static final String RESOURCE_URL_PREFIX = "/resource";
    //前台门户首页API请求路径前缀
    public static  final String API_FRONT_HOME_URL_PREFIX = API_FRONT_URL_PREFIX + HOME_URL_PREFIX;
    public static final String API_FRONT_BOOK_URL_PREFIX = API_FRONT_URL_PREFIX + BOOK_URL_PRRFIX;
    public static final  String API_FRONT_USER_URL_PREFIX = API_FRONT_URL_PREFIX + USER_URL_PREFIX;

  public static final String API_FRONT_RESOURCE_URL_PREFIX =API_FRONT_URL_PREFIX+RESOURCE_URL_PREFIX;// /api/front/resource
  //http://127.0.0.1:8080/api/front/resource/img_verify_code
}
