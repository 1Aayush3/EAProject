package edu.miu.cs.cs544.service.contract;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SessionPayload implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

}
