package edu.miu.cs.cs544.service.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordDto implements Serializable {
    private LocalDate date;
    private LocalTime time;
    private Integer sessionId;
    private Integer memberId;

}
