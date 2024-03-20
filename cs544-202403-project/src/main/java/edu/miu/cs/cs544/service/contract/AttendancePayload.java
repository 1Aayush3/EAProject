package edu.miu.cs.cs544.service.contract;

import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Session;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
@Data
public class AttendancePayload implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer attendanceId;


    private LocalDate date;


    private LocalTime time;


    private Member member;


    private Session session;
}
