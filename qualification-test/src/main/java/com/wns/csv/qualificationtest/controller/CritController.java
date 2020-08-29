package com.wns.csv.qualificationtest.controller;

import com.wns.csv.qualificationtest.dto.VehcatDto;
import com.wns.csv.qualificationtest.service.CritService;
import com.wns.csv.qualificationtest.util.CsvExporter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/crit")
public class CritController {

    @Resource
    private CritService critService;

    @Resource
    private CsvExporter csvExporter;

    @GetMapping("/convert-to-csv")
    public ResponseEntity<?> exportToCsv() {

        try {
            List<VehcatDto> finalCrtExt = critService.finalCrtExt();
            if (!CollectionUtils.isEmpty(finalCrtExt)) {
                ByteArrayInputStream in = csvExporter.covertDataToCsv(finalCrtExt);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Disposition", "attachment; filename=FINAL_CRT_EXT.xslx");
                return new ResponseEntity<>(new InputStreamResource(in), headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No data found to export!", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to Export Excel sheet!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
