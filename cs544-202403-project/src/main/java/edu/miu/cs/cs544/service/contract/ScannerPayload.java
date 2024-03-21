package edu.miu.cs.cs544.service.contract;

import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.AccountType;
import edu.miu.cs.cs544.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScannerPayload implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer scannerCode;

    private LocationPayload location;

    private EventPayload event;

    private AccountType accountType;

}
