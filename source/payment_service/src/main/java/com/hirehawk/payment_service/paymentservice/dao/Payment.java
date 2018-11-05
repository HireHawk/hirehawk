package com.hirehawk.payment_service.paymentservice.dao;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Payment extends AbstractEntity {

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "payment_id")
    private Set<Wallet> wallets;

    public Payment() {
    }

    public Payment(Long userId, Set<Wallet> wallets) {
        this.userId = userId;
        this.wallets = wallets;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(Set<Wallet> wallets) {
        this.wallets = wallets;
    }
}
