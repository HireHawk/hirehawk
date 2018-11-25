package com.hirehawk.payment_service.paymentservice.dao;

import com.hirehawk.payment_service.paymentservice.dao.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Wallet extends AbstractEntity {
    @Column(name = "money_amount")
    private BigDecimal moneyAmount;

    @Column(name = "user_id")
    private Long userId;

    public Wallet() {
        this.moneyAmount = new BigDecimal(0);
    }

    public Wallet(BigDecimal moneyAmount, Long userId) {
        this.moneyAmount = moneyAmount;
        this.userId = userId;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
