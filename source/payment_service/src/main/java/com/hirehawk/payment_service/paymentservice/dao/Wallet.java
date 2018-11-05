package com.hirehawk.payment_service.paymentservice.dao;

import com.hirehawk.payment_service.paymentservice.dao.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Wallet extends AbstractEntity {
    @Column(name = "money_amount")
    private BigDecimal moneyAmount;

    public Wallet() {

    }

    public Wallet(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }
}
