package com.wns.csv.qualificationtest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehcatDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String addMod;

    private String nvic;

    private String vehcat;

    private String effectiveDate;

    private String changeTimestamp;

    private String effectiveEndDate;

    private String endDateTimestamp;

    private String company;

    private String acceptCrit;

    private String internetJep;
}
