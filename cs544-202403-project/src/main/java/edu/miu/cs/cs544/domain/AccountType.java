package edu.miu.cs.cs544.domain;

import lombok.Getter;

@Getter
public enum AccountType {
    DINING(100),
    ATTENDANCE(200),
    LIBRARY(300),
    GYM(500);

    private final int defaultBalance;

    AccountType(int defaultBalance) {
        this.defaultBalance = defaultBalance;
    }

}