package edu.miu.cs.cs544.domain;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    @Column(name = "Description")
    private String description;

    @Column(name = "AccountType", nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @ManyToOne
    @JoinColumn(name = "MemberId")
    private Member member;

    @Column(name = "Balance")
    private double balance;

    @Column(name = "Status")
    private Boolean status;

    @Column(name = "DefaultBalance")
    private Integer defaultBalance;

    public Account() {
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
        this.defaultBalance = accountType.getDefaultBalance();
    }


}
