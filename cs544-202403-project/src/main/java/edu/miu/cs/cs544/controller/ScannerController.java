package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.integration.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.domain.Scanner;
import edu.miu.cs.cs544.service.contract.ScannerPayload;

@RestController
@RequestMapping("/scanners")
public class ScannerController extends BaseReadWriteController<ScannerPayload, Scanner, Integer> {

    @Autowired
    EmailService emailService;

    @GetMapping("/email")
    public String emailMe(){
        emailService.sendEmail("example.com", "HI", "Hello"  );
        return "Pranjal";
    }

}
