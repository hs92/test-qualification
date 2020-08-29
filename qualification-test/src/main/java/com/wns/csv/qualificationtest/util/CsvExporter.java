package com.wns.csv.qualificationtest.util;

import com.wns.csv.qualificationtest.dto.VehcatDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class CsvExporter {

    private final String[] headers = {"Add Mod", "Nvic", "Vehcat","Effective Date", "Change Timestamp", "Effective End date", "Company", "Accept Crit", "Internet Jep"};

    private void addHeaderRows(XSSFSheet xssfSheet, CellStyle headerCellStyle) {

        Row headerRow = xssfSheet.createRow(0);
        IntStream.range(0, headers.length).forEach(i -> {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerCellStyle);
        });
    }

    private void addDataRows(XSSFSheet xssfSheet, List<VehcatDto> vehcatDtos) {

        int rowNum = 0;
        for (VehcatDto vehcatDto : vehcatDtos) {
            Row dataRow = xssfSheet.createRow(++rowNum);
            dataRow.createCell(0).setCellValue(vehcatDto.getAddMod());
            dataRow.createCell(1).setCellValue(vehcatDto.getNvic());
            dataRow.createCell(2).setCellValue(vehcatDto.getVehcat());
            dataRow.createCell(3).setCellValue(vehcatDto.getEffectiveDate());
            dataRow.createCell(4).setCellValue(vehcatDto.getChangeTimestamp());
            dataRow.createCell(5).setCellValue(vehcatDto.getEffectiveEndDate());
            dataRow.createCell(6).setCellValue(vehcatDto.getCompany());
            dataRow.createCell(7).setCellValue(vehcatDto.getAcceptCrit());
            dataRow.createCell(8).setCellValue(vehcatDto.getInternetJep());
        }
    }

    public ByteArrayInputStream covertDataToCsv(List<VehcatDto> vehcatDtos) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet xssfSheet = xssfWorkbook.createSheet("Final CRT");

        Font headerFont = xssfWorkbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.BLUE.getIndex());
        CellStyle headerCellStyle = xssfWorkbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        addHeaderRows(xssfSheet, headerCellStyle);
        addDataRows(xssfSheet, vehcatDtos);

        xssfWorkbook.write(out);
        return new ByteArrayInputStream(out.toByteArray());
    }
}
