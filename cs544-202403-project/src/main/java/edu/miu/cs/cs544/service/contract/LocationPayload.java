package edu.miu.cs.cs544.service.contract;

import lombok.Data;

import java.io.Serializable;

@Data
public class LocationPayload implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String description;

    private String type;
}
