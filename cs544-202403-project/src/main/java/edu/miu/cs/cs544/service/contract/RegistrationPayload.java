package edu.miu.cs.cs544.service.contract;

import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Member;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegistrationPayload implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Event event;

    private Member member;
}



