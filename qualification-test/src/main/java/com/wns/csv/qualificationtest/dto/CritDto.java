package com.wns.csv.qualificationtest.dto;

import java.io.Serializable;

public class CritDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String nvic;

    private String vehcat;

    private String effectiveDate;

    private String changeTimestamp;

    private String effectiveEndDate;

    private String endDateTimestamp;

    private String ampacpt;

    private String aamacpt;

    private String apiacpt;

    private String gioacpt;

    private String sunacpt;

    private String shnacpt;

    private String giociacpt;

    private String jciacpt;

    private String bingleacpt;

    private String essacpt;

    private String vo3acpt;

    private String vo5acpt;

    private String aamrule;

    private String amprule;

    private String apiRule;

    private String gioRule;

    private String sunRule;

    private String shnRule;

    private String essRule;

    private String vo3rule;

    private String v05rule;

    private String jciRule;

    private String giociRule;

    private String bingleRule;

    private String imraCpt;

    private String imrRule;

    public CritDto(long id, String nvic, String vehcat, String effectiveDate, String changeTimestamp, String effectiveEndDate, String endDateTimestamp, String ampacpt, String aamacpt, String apiacpt, String gioacpt, String sunacpt, String shnacpt, String giociacpt, String jciacpt, String bingleacpt, String essacpt, String vo3acpt, String vo5acpt, String aamrule, String amprule, String apiRule, String gioRule, String sunRule, String shnRule, String essRule, String vo3rule, String v05rule, String jciRule, String giociRule, String bingleRule, String imraCpt, String imrRule) {
        this.id = id;
        this.nvic = nvic;
        this.vehcat = vehcat;
        this.effectiveDate = effectiveDate;
        this.changeTimestamp = changeTimestamp;
        this.effectiveEndDate = effectiveEndDate;
        this.endDateTimestamp = endDateTimestamp;
        this.ampacpt = ampacpt;
        this.aamacpt = aamacpt;
        this.apiacpt = apiacpt;
        this.gioacpt = gioacpt;
        this.sunacpt = sunacpt;
        this.shnacpt = shnacpt;
        this.giociacpt = giociacpt;
        this.jciacpt = jciacpt;
        this.bingleacpt = bingleacpt;
        this.essacpt = essacpt;
        this.vo3acpt = vo3acpt;
        this.vo5acpt = vo5acpt;
        this.aamrule = aamrule;
        this.amprule = amprule;
        this.apiRule = apiRule;
        this.gioRule = gioRule;
        this.sunRule = sunRule;
        this.shnRule = shnRule;
        this.essRule = essRule;
        this.vo3rule = vo3rule;
        this.v05rule = v05rule;
        this.jciRule = jciRule;
        this.giociRule = giociRule;
        this.bingleRule = bingleRule;
        this.imraCpt = imraCpt;
        this.imrRule = imrRule;
    }

    public CritDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNvic() {
        return nvic;
    }

    public void setNvic(String nvic) {
        this.nvic = nvic;
    }

    public String getVehcat() {
        return vehcat;
    }

    public void setVehcat(String vehcat) {
        this.vehcat = vehcat;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getChangeTimestamp() {
        return changeTimestamp;
    }

    public void setChangeTimestamp(String changeTimestamp) {
        this.changeTimestamp = changeTimestamp;
    }

    public String getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public void setEffectiveEndDate(String effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    public String getEndDateTimestamp() {
        return endDateTimestamp;
    }

    public void setEndDateTimestamp(String endDateTimestamp) {
        this.endDateTimestamp = endDateTimestamp;
    }

    public String getAmpacpt() {
        return ampacpt;
    }

    public void setAmpacpt(String ampacpt) {
        this.ampacpt = ampacpt;
    }

    public String getAamacpt() {
        return aamacpt;
    }

    public void setAamacpt(String aamacpt) {
        this.aamacpt = aamacpt;
    }

    public String getApiacpt() {
        return apiacpt;
    }

    public void setApiacpt(String apiacpt) {
        this.apiacpt = apiacpt;
    }

    public String getGioacpt() {
        return gioacpt;
    }

    public void setGioacpt(String gioacpt) {
        this.gioacpt = gioacpt;
    }

    public String getSunacpt() {
        return sunacpt;
    }

    public void setSunacpt(String sunacpt) {
        this.sunacpt = sunacpt;
    }

    public String getShnacpt() {
        return shnacpt;
    }

    public void setShnacpt(String shnacpt) {
        this.shnacpt = shnacpt;
    }

    public String getGiociacpt() {
        return giociacpt;
    }

    public void setGiociacpt(String giociacpt) {
        this.giociacpt = giociacpt;
    }

    public String getJciacpt() {
        return jciacpt;
    }

    public void setJciacpt(String jciacpt) {
        this.jciacpt = jciacpt;
    }

    public String getBingleacpt() {
        return bingleacpt;
    }

    public void setBingleacpt(String bingleacpt) {
        this.bingleacpt = bingleacpt;
    }

    public String getEssacpt() {
        return essacpt;
    }

    public void setEssacpt(String essacpt) {
        this.essacpt = essacpt;
    }

    public String getVo3acpt() {
        return vo3acpt;
    }

    public void setVo3acpt(String vo3acpt) {
        this.vo3acpt = vo3acpt;
    }

    public String getVo5acpt() {
        return vo5acpt;
    }

    public void setVo5acpt(String vo5acpt) {
        this.vo5acpt = vo5acpt;
    }

    public String getAamrule() {
        return aamrule;
    }

    public void setAamrule(String aamrule) {
        this.aamrule = aamrule;
    }

    public String getAmprule() {
        return amprule;
    }

    public void setAmprule(String amprule) {
        this.amprule = amprule;
    }

    public String getApiRule() {
        return apiRule;
    }

    public void setApiRule(String apiRule) {
        this.apiRule = apiRule;
    }

    public String getGioRule() {
        return gioRule;
    }

    public void setGioRule(String gioRule) {
        this.gioRule = gioRule;
    }

    public String getSunRule() {
        return sunRule;
    }

    public void setSunRule(String sunRule) {
        this.sunRule = sunRule;
    }

    public String getShnRule() {
        return shnRule;
    }

    public void setShnRule(String shnRule) {
        this.shnRule = shnRule;
    }

    public String getEssRule() {
        return essRule;
    }

    public void setEssRule(String essRule) {
        this.essRule = essRule;
    }

    public String getVo3rule() {
        return vo3rule;
    }

    public void setVo3rule(String vo3rule) {
        this.vo3rule = vo3rule;
    }

    public String getV05rule() {
        return v05rule;
    }

    public void setV05rule(String v05rule) {
        this.v05rule = v05rule;
    }

    public String getJciRule() {
        return jciRule;
    }

    public void setJciRule(String jciRule) {
        this.jciRule = jciRule;
    }

    public String getGiociRule() {
        return giociRule;
    }

    public void setGiociRule(String giociRule) {
        this.giociRule = giociRule;
    }

    public String getBingleRule() {
        return bingleRule;
    }

    public void setBingleRule(String bingleRule) {
        this.bingleRule = bingleRule;
    }

    public String getImraCpt() {
        return imraCpt;
    }

    public void setImraCpt(String imraCpt) {
        this.imraCpt = imraCpt;
    }

    public String getImrRule() {
        return imrRule;
    }

    public void setImrRule(String imrRule) {
        this.imrRule = imrRule;
    }
}
