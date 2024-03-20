package edu.miu.cs.cs544.service.contract;

import java.io.Serializable;

public class AttendanceDTO implements Serializable {
    private Integer barCode;

    public Integer getBarCode() {
        return barCode;
    }
}
