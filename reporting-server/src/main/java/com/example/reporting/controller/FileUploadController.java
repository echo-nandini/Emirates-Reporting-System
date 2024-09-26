package com.example.reporting.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.reporting.model.TicketKPI;
import com.example.reporting.service.FileUploadService;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/uploadFile")
    public ResponseEntity<Integer> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            int noOfTickets = fileUploadService.parseAndSaveExcel(file);
            return ResponseEntity.ok(noOfTickets);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
