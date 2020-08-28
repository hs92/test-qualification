package com.wns.csv.qualificationtest.controller;

import com.wns.csv.qualificationtest.service.CritService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/crit")
public class CritController {

    @Resource
    private CritService critService;

    @GetMapping("/convert-to-csv")
    public ResponseEntity<?> getCsv() {

        return new ResponseEntity<>(critService.getCsv(), HttpStatus.OK);
    }
}
