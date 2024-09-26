package com.example.reporting.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public class DateParser {

    public static LocalDateTime getLocalDateTimeFromCell(Cell cell) {
        if (DateUtil.isCellDateFormatted(cell)) {
            Date date = cell.getDateCellValue();
            return convertToLocalDateTime(date);
        }
        return null;
    }

    public static LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
