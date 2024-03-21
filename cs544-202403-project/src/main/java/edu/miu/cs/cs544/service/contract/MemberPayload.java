package edu.miu.cs.cs544.service.contract;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import edu.miu.cs.cs544.domain.Attendance;
import edu.miu.cs.cs544.domain.Role;
import edu.miu.cs.cs544.domain.Session;
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
	private Set<Role> roles;
//	private List<Attendance> attendanceList;

}
