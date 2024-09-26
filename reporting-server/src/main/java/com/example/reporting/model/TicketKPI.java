package com.example.reporting.model;

import com.example.reporting.enums.TicketType;

import java.time.LocalDateTime;

import org.hibernate.type.descriptor.jdbc.TimeJdbcType;

import com.example.reporting.enums.Priority;
import com.example.reporting.enums.Resolution;
import com.example.reporting.enums.TechnicalPriority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "ticket_kpi")
public class TicketKPI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TicketType issueType;
    private String key;
    private String reporter;
    private String assignee;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    private String status;
    @Enumerated(EnumType.STRING)
    private Resolution resolution;
    private LocalDateTime created;
    private LocalDateTime updated;

    @Column(name = "change_priority")
    @Enumerated(EnumType.STRING)
    private TechnicalPriority changePriority;

    private String components;

    @Column(name = "fault_priority")
    private String faultPriority;

    @Column(name = "issue_priority")
    @Enumerated(EnumType.STRING)
    private TechnicalPriority issuePriority;

    @Column(name = "app_name")
    private String appName;

    @Column(name = "defect_priority")
    @Enumerated(EnumType.STRING)
    private TechnicalPriority defectPriority;

    @Column(name = "service_priority")
    @Enumerated(EnumType.STRING)
    private TechnicalPriority servicePriority;
}
