package com.example.reporting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reporting.model.TicketKPI;
import com.example.reporting.repository.TicketKPIRepository;

@Service
public class TicketService {

    @Autowired
    TicketKPIRepository ticketKPIRepository;

    public List<TicketKPI> getAllTickets() {
        return ticketKPIRepository.findAll();
    }

}
