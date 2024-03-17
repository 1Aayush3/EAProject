package edu.miu.cs.cs544.service.contract;

import edu.miu.cs.cs544.domain.Session;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SchedulePayLoad implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    @OneToMany
    @JoinColumn(name = "schedule_id")
    private List<Session> session;
}
