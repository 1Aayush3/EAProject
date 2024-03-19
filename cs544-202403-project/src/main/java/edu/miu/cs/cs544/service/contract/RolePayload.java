package edu.miu.cs.cs544.service.contract;

import edu.miu.cs.cs544.domain.Member;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class RolePayload implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer roleId;

    private String roleType;

    List<Member> members;

}
