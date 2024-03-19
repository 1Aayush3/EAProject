package edu.miu.cs.cs544.service.contract;

import edu.miu.cs.cs544.domain.Location;
import lombok.Data;
import java.io.Serializable;

@Data
public class ScannerPayload implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer scannerCode;

    private Location location;

}
