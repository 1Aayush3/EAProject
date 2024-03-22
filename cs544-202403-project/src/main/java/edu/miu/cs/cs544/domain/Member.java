package edu.miu.cs.cs544.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member implements Serializable {
	
	@Serial
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

	@ManyToMany
	@JoinTable(name = "member_roles",
			joinColumns = @JoinColumn(name = "memberId"),
			inverseJoinColumns = @JoinColumn(name = "roleId"))
	@JsonManagedReference
	private Set<Role> roles = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "member")
	private List<Attendance> attendanceList;

	public void setBarcode(Integer barCode) {
      this.barCode = barCode;
	}

}
