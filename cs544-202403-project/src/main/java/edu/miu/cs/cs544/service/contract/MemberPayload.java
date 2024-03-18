package edu.miu.cs.cs544.service.contract;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class MemberPayload implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer memberId;
	private String fname;
	private String lname;
	private String email;
	private Integer barCode;

}
