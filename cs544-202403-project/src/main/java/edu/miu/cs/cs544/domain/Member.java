package edu.miu.cs.cs544.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
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

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "member_roles",
			joinColumns = @JoinColumn(name = "memberId"),
			inverseJoinColumns = @JoinColumn(name = "roleId"))
	private Set<Role> roleTypes = new HashSet<Role>();

	@ManyToMany
	@JoinTable(name = "Attendance", joinColumns = @JoinColumn(name = "memberId"),inverseJoinColumns = @JoinColumn(name = "sessionId"))
	List<Session> sessions = new ArrayList<>();

}
