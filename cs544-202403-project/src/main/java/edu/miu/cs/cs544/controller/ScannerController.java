package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.Attendance;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.repository.ScannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.domain.Scanner;
import edu.miu.cs.cs544.service.contract.ScannerPayload;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/scanners")
public class ScannerController extends BaseReadWriteController<ScannerPayload, Scanner, Integer> {

    @Autowired
    ScannerRepository scannerRepository;

    @GetMapping("/{scannerCode}/records")
    public ResponseEntity<?> getScannerRecords(@PathVariable Integer scannerCode) {

        Optional<Scanner> scannerOptional = scannerRepository.findById(scannerCode);

        Scanner scanner = scannerOptional.orElse(null);
        if (scanner != null) {
            Event event = scanner.getEvent();
            List<Session> sessionList = event.getSessionList();

            // Using streams to retrieve attendance from sessions
            List<Attendance> attendanceList = sessionList.stream()
                    .flatMap(session -> session.getAttendanceList().stream())
                    .collect(Collectors.toList());

            return new ResponseEntity<>(attendanceList, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No Records", HttpStatus.NOT_FOUND);
        }
    }


//    @PostMapping("/{scannerCode}/records")
//    public String createScannerRecord(@PathVariable Integer scannerCode, @RequestBody RecordPayload recordPayload) {
//        // Logic to create a new record for the scannerCode using the provided payload
//        return "Creating record for scanner with code: " + scannerCode;
//    }
//
//    // PUT Endpoint to update an existing record for a scanner
//    @PutMapping("/{scannerCode}/records/{recordId}")
//    public String updateScannerRecord(@PathVariable Integer scannerCode, @PathVariable Long recordId, @RequestBody RecordPayload recordPayload) {
//        // Logic to update the record with recordId for the scannerCode using the provided payload
//        return "Updating record with ID: " + recordId + " for scanner with code: " + scannerCode;
//    }
//
//    // DELETE Endpoint to delete a record for a scanner
//    @DeleteMapping("/{scannerCode}/records/{recordId}")
//    public String deleteScannerRecord(@PathVariable Integer scannerCode, @PathVariable Long recordId) {
//        // Logic to delete the record with recordId for the scannerCode
//        return "Deleting record with ID: " + recordId + " for scanner with code: " + scannerCode;
//    }
}
