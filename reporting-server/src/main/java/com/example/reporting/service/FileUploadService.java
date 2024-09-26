package com.example.reporting.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.reporting.enums.Priority;
import com.example.reporting.enums.Resolution;
import com.example.reporting.enums.TechnicalPriority;
import com.example.reporting.enums.TicketType;
import com.example.reporting.model.TicketKPI;
import com.example.reporting.repository.TicketKPIRepository;
import com.example.reporting.utils.DateParser;

@Service
public class FileUploadService {

    @Autowired
    private TicketKPIRepository ticketKPIRepository;

    public Integer parseAndSaveExcel(MultipartFile file) throws IOException {
        List<TicketKPI> ticketKPIList = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();

            for (int i = 2; i < rowCount - 1; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }

                TicketKPI ticketKPI = new TicketKPI();
                ticketKPI.setIssueType(getIssueType(getCellValue(row.getCell(0))));

                ticketKPI.setKey(getCellValue(row.getCell(1)));
                ticketKPI.setReporter(getCellValue(row.getCell(2)));
                ticketKPI.setAssignee(getCellValue(row.getCell(3)));
                ticketKPI.setPriority(getPriority(getCellValue(row.getCell(4))));
                ticketKPI.setStatus(getCellValue(row.getCell(5)));
                ticketKPI.setResolution(getResolution(getCellValue(row.getCell(6))));

                Cell createdCell = row.getCell(7);
                LocalDateTime createdDate = DateParser.getLocalDateTimeFromCell(createdCell);
                if (createdDate != null) {
                    ticketKPI.setCreated(createdDate);
                }

                Cell updatedCell = row.getCell(8);
                LocalDateTime updatedDate = DateParser.getLocalDateTimeFromCell(updatedCell);

                if (updatedDate != null) {
                    ticketKPI.setUpdated(updatedDate);
                }

                ticketKPI.setChangePriority(getTechnicalPriority(getCellValue(row.getCell(9))));
                ticketKPI.setComponents(getCellValue(row.getCell(10)));
                ticketKPI.setFaultPriority(getCellValue(row.getCell(11)));
                ticketKPI.setIssuePriority(getTechnicalPriority(getCellValue(row.getCell(12))));
                ticketKPI.setAppName(getCellValue(row.getCell(13)));
                ticketKPI.setDefectPriority(getTechnicalPriority(getCellValue(row.getCell(14))));
                ticketKPI.setServicePriority(getTechnicalPriority(getCellValue(row.getCell(15))));

                ticketKPIList.add(ticketKPI);
            }
        }
        ticketKPIRepository.deleteAll();
        ticketKPIRepository.saveAll(ticketKPIList);

        return ticketKPIList.size();
    }

    private String getCellValue(Cell cell) {
        return cell != null ? cell.toString() : null;
    }

    private TicketType getIssueType(String cellValue) {

        if (cellValue == null || cellValue.trim().isEmpty()) {
            return null;
        }

        TicketType issueType = null;
        try {
            String normalizedValue = cellValue.toUpperCase().replace(" ", "_");
            issueType = TicketType.valueOf(normalizedValue);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid TicketType: " + cellValue);
        }

        return issueType;
    }

    private TechnicalPriority getTechnicalPriority(String cellValue) {

        if (cellValue == null || cellValue.trim().isEmpty()) {
            return null;
        }

        TechnicalPriority technicalPriority = null;
        try {
            String normalizedValue = cellValue.toUpperCase().replace(" ", "_");
            technicalPriority = TechnicalPriority.valueOf(normalizedValue);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid priority: " + cellValue);
        }

        return technicalPriority;
    }

    private Priority getPriority(String cellValue) {

        if (cellValue == null || cellValue.trim().isEmpty()) {
            return null;
        }
        return Priority.valueOf(cellValue.trim().toUpperCase());
    }

    private Resolution getResolution(String cellValue) {

        if (cellValue == null || cellValue.trim().isEmpty()) {
            return null;
        }
        return Resolution.valueOf(cellValue.trim().toUpperCase());
    }
}
