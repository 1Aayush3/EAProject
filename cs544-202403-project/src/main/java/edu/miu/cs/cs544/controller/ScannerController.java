package edu.miu.cs.cs544.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.domain.Scanner;
import edu.miu.cs.cs544.service.contract.ScannerPayload;

@RestController
@RequestMapping("/scanners")
public class ScannerController extends BaseReadWriteController<ScannerPayload, Scanner, Integer> {

}
