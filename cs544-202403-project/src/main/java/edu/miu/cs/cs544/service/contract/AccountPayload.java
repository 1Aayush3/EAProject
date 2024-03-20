package edu.miu.cs.cs544.service.contract;

import edu.miu.cs.cs544.domain.AccountType;
import edu.miu.cs.cs544.domain.Member;
import lombok.Data;

import java.io.Serializable;

@Data
public class AccountPayload implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer accountId;

    private String description;

    private AccountType accountType;

    private Member member;

    private Double balance;

    private Boolean status;
}
