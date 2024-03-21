package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.*;
import edu.miu.cs.cs544.repository.*;
import edu.miu.cs.cs544.service.AttendanceService;
import edu.miu.cs.cs544.service.contract.AttendanceDTO;
import edu.miu.cs.cs544.service.contract.AttendancePayload;
import edu.miu.cs.cs544.service.contract.RecordDto;
import edu.miu.cs.cs544.service.mapper.AttendancePayloadToAttendanceMapper;
import edu.miu.cs.cs544.service.mapper.AttendanceToAttendancePayloadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.service.contract.ScannerPayload;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/scanners")
public class ScannerController extends BaseReadWriteController<ScannerPayload, Scanner, Integer> {

    @Autowired
    AttendanceService attendanceService;

    @GetMapping("/{scannerCode}/records")
    public ResponseEntity<?> getScannerRecords(@PathVariable Integer scannerCode) {
        List<Attendance> attendanceList = attendanceService.getAllRecordsOfScanner(scannerCode);

        if (attendanceList != null && !attendanceList.isEmpty()) {
            return ResponseEntity.ok(attendanceList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{scannerCode}/records/{recordId}")
    public ResponseEntity<?> getScannerRecord(@PathVariable Integer scannerCode, @PathVariable Integer recordId) {
        Attendance attendance  = attendanceService.getRecordOfScanner(scannerCode, recordId);

        if(attendance != null){
            return ResponseEntity.ok(attendance);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{scannerCode}/records")
    public ResponseEntity<Attendance> createScannerRecord(@PathVariable Integer scannerCode, @RequestBody AttendanceDTO attendanceDTO) {
        Integer barCode = attendanceDTO.getBarCode();

        Attendance attendance = attendanceService.createRecord(barCode,scannerCode);

        if(attendance == null){
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<Attendance>(attendance, HttpStatus.OK);
    }

    @PutMapping("/{scannerCode}/records/{recordId}")
    public ResponseEntity<Attendance> updateScannerRecord(@PathVariable Integer scannerCode,
                                                      @PathVariable Integer recordId,
                                                      @RequestBody RecordDto recordDto) {

        Attendance attendance = attendanceService.editRecord(scannerCode,recordId,recordDto);
        if(attendance == null){
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<Attendance>(attendance, HttpStatus.OK);
    }

    @DeleteMapping("/{scannerCode}/records/{recordId}")
    public ResponseEntity<String> deleteScannerRecord(@PathVariable Integer scannerCode, @PathVariable Integer recordId) {

        String message = attendanceService.deleteRecord(scannerCode, recordId);
        if (message == null){
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class EventNotFoundException extends RuntimeException {
        public EventNotFoundException(String message) {
            super(message);
        }
    }
}
