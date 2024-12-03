package com.wolvescoding.nownovel.core.common.resp;

import lombok.Getter;

import java.util.List;

/*
 *  分页响应数据格式封装
 * 泛型编程，支持动态的改变对象，从而减小代码的冗余
 */
@Getter
public class PageRespDto<T> {
    private final long pageNum;
    private final long pageSize;
    // 总页数
    private final long total;
    // 泛型通配符，可支持T的子类
    private final List< ? extends T> list;
    public PageRespDto(long pageNum, long pageSize, long total, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
    }
    // 静态工厂方法
    public static <T> PageRespDto of(long pageNum, long pageSize, long total, List<T> list) {
        return new PageRespDto<>(pageNum, pageSize, total, list);
    }
    public long getPage(){
        if(this.pageSize==0L){
            return 0L;
        }
        else{
            long pages = this.total/this.pageSize;
            if(this.total%this.pageSize!=0L){
                ++pages;
            }
            return pages;
        }
    }
}
