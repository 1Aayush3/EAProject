package edu.miu.cs.cs544.integration.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailMessage implements Serializable {
    private String to;
    private String subject;
    private String body;
}
