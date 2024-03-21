package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;

import edu.miu.cs.cs544.domain.Attendance;

import edu.miu.cs.cs544.service.contract.AttendancePayload;
import edu.miu.cs.cs544.service.contract.RecordDto;

import java.util.List;

public interface AttendanceService extends BaseReadWriteService<AttendancePayload, Attendance, Integer> {
    List<Attendance> getAllRecordsOfScanner(Integer scannerCode);

    Attendance getRecordOfScanner(Integer scannerCode, Integer recordId);

    Attendance createRecord(Integer barcode, Integer scannerCode);

    Attendance editRecord(Integer scannerCode, Integer recordId, RecordDto recordDto);

    String deleteRecord(Integer scannerCode, Integer recordId);
}
