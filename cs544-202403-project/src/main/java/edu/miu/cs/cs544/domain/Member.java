package edu.miu.cs.cs544.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Entity
public class Member implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "memberId")
	private Integer memberId;
	
	@Column(name = "fName", nullable = false)
	private String fname;

	@Column(name = "lName", nullable = false)
	private String lname;

	@Email
	@Column(name="email", nullable = false, unique = true)
	private String email;

	@Column(name="barCode", nullable = false, unique = true)
	private Integer barCode;
}
