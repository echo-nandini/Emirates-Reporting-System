package com.example.reporting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reporting.model.TicketKPI;

public interface TicketKPIRepository extends JpaRepository<TicketKPI, Long> {
}
