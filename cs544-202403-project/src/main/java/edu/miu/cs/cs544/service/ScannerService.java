package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs.cs544.domain.Scanner;
import edu.miu.cs.cs544.service.contract.ScannerPayload;

public interface ScannerService extends BaseReadWriteService<ScannerPayload, Scanner, Integer> {
}
