package edu.miu.cs.cs544.controller;

import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.domain.Location;
import edu.miu.cs.cs544.service.contract.LocationPayload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
public class LocationController extends BaseReadWriteController<LocationPayload, Location, Integer> {
}
