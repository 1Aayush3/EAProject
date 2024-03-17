package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String location;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;



}
