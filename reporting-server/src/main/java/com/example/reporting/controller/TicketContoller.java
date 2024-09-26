package com.example.reporting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reporting.model.TicketKPI;
import com.example.reporting.service.TicketService;

@RestController
@RequestMapping("/api")
public class TicketContoller {

    @Autowired
    TicketService ticketService;

    @GetMapping("/getAllTickets")
    public ResponseEntity<List<TicketKPI>> getTicketDetails() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }
}
