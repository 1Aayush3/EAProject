package edu.miu.cs.cs544;

import edu.miu.cs.cs544.domain.AccountType;
import edu.miu.cs.cs544.domain.RoleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DefaultAccountConfig {
    public static final HashMap<RoleType, List<AccountType>> value = new HashMap<>() {{
        put(RoleType.Student, new ArrayList<>() {{
            add(AccountType.DINING);
            add(AccountType.ATTENDANCE);
            add(AccountType.GYM);
        }});
        put(RoleType.Faculty, new ArrayList<>() {{
            add(AccountType.DINING);
            add(AccountType.ATTENDANCE);
        }});
        put(RoleType.Staff, new ArrayList<>() {{
            add(AccountType.DINING);
        }});
    }};
}
