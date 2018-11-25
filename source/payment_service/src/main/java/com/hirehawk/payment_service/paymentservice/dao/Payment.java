package com.hirehawk.payment_service.paymentservice.dao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Payment extends AbstractEntity {

    @Column(name = "wallet_id_from")
    private Long walletIdFrom;

    @Column(name = "wallet_id_to")
    private Long walletIdTo;

    @Column(name = "amount")
    private BigDecimal amount;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date timespamp;

    private Long orderId;

    public Payment() {
    }

    public Payment(Long walletIdFrom, Long walletIdTo, BigDecimal amount, Date timespamp, Long orderId) {
        this.walletIdFrom = walletIdFrom;
        this.walletIdTo = walletIdTo;
        this.amount = amount;
        this.timespamp = timespamp;
        this.orderId = orderId;
    }

    public Long getWalletIdFrom() {
        return walletIdFrom;
    }

    public void setWalletIdFrom(Long walletIdFrom) {
        this.walletIdFrom = walletIdFrom;
    }

    public Long getWalletIdTo() {
        return walletIdTo;
    }

    public void setWalletIdTo(Long walletIdTo) {
        this.walletIdTo = walletIdTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTimespamp() {
        return timespamp;
    }

    public void setTimespamp(Date timespamp) {
        this.timespamp = timespamp;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
