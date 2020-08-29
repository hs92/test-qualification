package com.wns.csv.qualificationtest.dto;

import java.io.Serializable;

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

    public VehcatDto(String addMod, String nvic, String vehcat, String effectiveDate, String changeTimestamp, String effectiveEndDate, String endDateTimestamp, String company, String acceptCrit, String internetJep) {
        this.addMod = addMod;
        this.nvic = nvic;
        this.vehcat = vehcat;
        this.effectiveDate = effectiveDate;
        this.changeTimestamp = changeTimestamp;
        this.effectiveEndDate = effectiveEndDate;
        this.endDateTimestamp = endDateTimestamp;
        this.company = company;
        this.acceptCrit = acceptCrit;
        this.internetJep = internetJep;
    }

    public void setAddMod(String addMod) {
        this.addMod = addMod;
    }

    public String getNvic() {
        return nvic;
    }

    public String getVehcat() {
        return vehcat;
    }

    public String getCompany() {
        return company;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public String getChangeTimestamp() {
        return changeTimestamp;
    }

    public String getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public String getEndDateTimestamp() {
        return endDateTimestamp;
    }

    public String getAcceptCrit() {
        return acceptCrit;
    }

    public String getInternetJep() {
        return internetJep;
    }
}
