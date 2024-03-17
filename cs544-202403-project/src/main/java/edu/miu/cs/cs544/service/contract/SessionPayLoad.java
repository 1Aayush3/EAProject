package edu.miu.cs.cs544.service.contract;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
@Data
public class SessionPayLoad implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
}
