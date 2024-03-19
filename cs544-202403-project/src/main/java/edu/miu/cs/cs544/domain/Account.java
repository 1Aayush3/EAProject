package edu.miu.cs.cs544.domain;

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

    @Column(name = "AccountType", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;


}
