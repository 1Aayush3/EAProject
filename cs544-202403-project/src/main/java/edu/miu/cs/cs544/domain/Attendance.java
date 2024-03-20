package edu.miu.cs.cs544.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Attendance implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendanceId")
    private Integer attendanceId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessionId")
    private Session session;
    public Integer getAttendanceId() {
        return attendanceId;
    }
}

