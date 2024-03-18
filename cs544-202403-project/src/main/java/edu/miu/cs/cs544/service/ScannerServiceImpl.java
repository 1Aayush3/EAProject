package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Scanner;
import edu.miu.cs.cs544.service.contract.ScannerPayload;
import org.springframework.stereotype.Service;

@Service
public class ScannerServiceImpl extends BaseReadWriteServiceImpl<ScannerPayload, Scanner, Integer> implements ScannerService {
}
