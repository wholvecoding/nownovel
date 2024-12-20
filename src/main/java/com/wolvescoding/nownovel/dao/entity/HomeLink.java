package com.wolvescoding.nownovel.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 主页链接
 * </p>
 *
 * @author wolvescoding
 * @since 2024/12/03
 */
@TableName("home_link")
public class HomeLink implements Serializable {

private static final long serialVersionUID = 1L;

                        private Long id;

        /**
         * 链接名
         */
        private String linkName;

        /**
         * 链接路径
         */
        private String linkUrl;

        /**
         * 排序号
         */
        private Byte sort;

        /**
         * 是否开启
         */
        private Byte isOpen;

        /**
         * 创建时间
         */
        private LocalDateTime createTime;

        /**
         * 更新时间
         */
        private LocalDateTime updateTime;

    public Long getId() {
            return id;
            }

        public void setId(Long id) {
            this.id = id;
            }

    public String getLinkName() {
            return linkName;
            }

        public void setLinkName(String linkName) {
            this.linkName = linkName;
            }

    public String getLinkUrl() {
            return linkUrl;
            }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
            }

    public Byte getSort() {
            return sort;
            }

        public void setSort(Byte sort) {
            this.sort = sort;
            }

    public Byte getIsOpen() {
            return isOpen;
            }

        public void setIsOpen(Byte isOpen) {
            this.isOpen = isOpen;
            }

    public LocalDateTime getCreateTime() {
            return createTime;
            }

        public void setCreateTime(LocalDateTime createTime) {
            this.createTime = createTime;
            }

    public LocalDateTime getUpdateTime() {
            return updateTime;
            }

        public void setUpdateTime(LocalDateTime updateTime) {
            this.updateTime = updateTime;
            }
    
@Override
public String toString() {
        return "HomeLink{" +
                "id = " + id +
                ", linkName = " + linkName +
                ", linkUrl = " + linkUrl +
                ", sort = " + sort +
                ", isOpen = " + isOpen +
                ", createTime = " + createTime +
                ", updateTime = " + updateTime +
        "}";
        }
        }
