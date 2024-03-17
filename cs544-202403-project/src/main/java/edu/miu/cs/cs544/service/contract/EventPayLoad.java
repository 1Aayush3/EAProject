package edu.miu.cs.cs544.service.contract;

import lombok.Data;

import java.io.Serializable;
@Data
public class EventPayLoad implements Serializable {

        private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private String location;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
}
