package com.wolvescoding.nownovel.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 支付宝
 * </p>
 *
 * @author wolvescoding
 * @since 2024/12/03
 */
@TableName("pay_ali")
public class PayAli implements Serializable {

private static final long serialVersionUID = 1L;

                        private Long id;

        /**
         * 商户订单号
         */
        private String outTradeId;

        /**
         * 支付宝交易号
         */
        private String tradeNo;

        /**
         * 买家支付宝账号id
         */
        private String buyerId;

        /**
         * 交易状态
         */
        private String tradeStatus;

        /**
         * 订单金额
         */
        private Integer tradeAmount;

        /**
         * 实收金额
         */
        private Integer receiptAmount;

        /**
         * 开票金额
         */
        private Integer invoiceAmount;

        /**
         * 交易付款时间
         */
        private LocalDateTime gmtPayment;

        /**
         * 交易创建时间
         */
        private LocalDateTime gmtCreate;

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

    public String getOutTradeId() {
            return outTradeId;
            }

        public void setOutTradeId(String outTradeId) {
            this.outTradeId = outTradeId;
            }

    public String getTradeNo() {
            return tradeNo;
            }

        public void setTradeNo(String tradeNo) {
            this.tradeNo = tradeNo;
            }

    public String getBuyerId() {
            return buyerId;
            }

        public void setBuyerId(String buyerId) {
            this.buyerId = buyerId;
            }

    public String getTradeStatus() {
            return tradeStatus;
            }

        public void setTradeStatus(String tradeStatus) {
            this.tradeStatus = tradeStatus;
            }

    public Integer getTradeAmount() {
            return tradeAmount;
            }

        public void setTradeAmount(Integer tradeAmount) {
            this.tradeAmount = tradeAmount;
            }

    public Integer getReceiptAmount() {
            return receiptAmount;
            }

        public void setReceiptAmount(Integer receiptAmount) {
            this.receiptAmount = receiptAmount;
            }

    public Integer getInvoiceAmount() {
            return invoiceAmount;
            }

        public void setInvoiceAmount(Integer invoiceAmount) {
            this.invoiceAmount = invoiceAmount;
            }

    public LocalDateTime getGmtPayment() {
            return gmtPayment;
            }

        public void setGmtPayment(LocalDateTime gmtPayment) {
            this.gmtPayment = gmtPayment;
            }

    public LocalDateTime getGmtCreate() {
            return gmtCreate;
            }

        public void setGmtCreate(LocalDateTime gmtCreate) {
            this.gmtCreate = gmtCreate;
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
        return "PayAli{" +
                "id = " + id +
                ", outTradeId = " + outTradeId +
                ", tradeNo = " + tradeNo +
                ", buyerId = " + buyerId +
                ", tradeStatus = " + tradeStatus +
                ", tradeAmount = " + tradeAmount +
                ", receiptAmount = " + receiptAmount +
                ", invoiceAmount = " + invoiceAmount +
                ", gmtPayment = " + gmtPayment +
                ", gmtCreate = " + gmtCreate +
                ", createTime = " + createTime +
                ", updateTime = " + updateTime +
        "}";
        }
        }
