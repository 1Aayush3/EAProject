package edu.miu.cs.cs544.controller;


import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Scanner;
import edu.miu.cs.cs544.service.contract.ScannerPayload;
import edu.miu.cs.cs544.service.mapper.EventToEventPayloadMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.miu.cs.cs544.service.contract.EventPayload;

import edu.miu.cs.cs544.service.mapper.EventPayloadToEventMapper;

import edu.miu.cs.cs544.service.EventService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/events")
public class EventController extends BaseReadWriteController<EventPayload, Event, Integer> {


}