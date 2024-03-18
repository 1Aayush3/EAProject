package edu.miu.cs.cs544.service.contract;

import edu.miu.cs.cs544.domain.AccountType;

import java.io.Serializable;

public class AccountPayload implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer accountId;

    private String description;

    private AccountType accountType;
}
