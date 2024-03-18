package edu.miu.cs.cs544.service.contract;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SessionPayload implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private LocalDate date;

    private LocalTime time;

}
