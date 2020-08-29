package com.wns.csv.qualificationtest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MIGRATE_TEST")
public class Crit {

    @Id
    private long id;

    @Column(name = "NVIC")
    private String nvic;

    @Column(name = "VEHCAT")
    private String vehcat;

    @Column(name = "AMPACPT")
    private String ampacpt;

    @Column(name = "AAMACPT")
    private String aamacpt;

    @Column(name = "APIACPT")
    private String apiacpt;

    @Column(name = "GIOACPT")
    private String gioacpt;

    @Column(name = "SUNACPT")
    private String sunacpt;

    @Column(name = "SHNACPT")
    private String shnacpt;

    @Column(name = "GIOCIACPT")
    private String giociacpt;

    @Column(name = "JCIACPT")
    private String jciacpt;

    @Column(name = "BINGLEACPT")
    private String bingleacpt;

    @Column(name = "ESSACPT")
    private String essacpt;

    @Column(name = "V03ACPT")
    private String vo3acpt;

    @Column(name = "V05ACPT")
    private String vo5acpt;

    @Column(name = "AAMRULE")
    private String aamrule;

    @Column(name = "AMPRULE")
    private String amprule;

    @Column(name = "APIRULE")
    private String apiRule;

    @Column(name = "GIORULE")
    private String gioRule;

    @Column(name = "SUNRULE")
    private String sunRule;

    @Column(name = "SHNRULE")
    private String shnRule;

    @Column(name = "ESSRULE")
    private String essRule;

    @Column(name = "V03RULE")
    private String vo3rule;

    @Column(name = "V05RULE")
    private String v05rule;

    @Column(name = "JCIRULE")
    private String jciRule;

    @Column(name = "GIOCIRULE")
    private String giociRule;

    @Column(name = "BINGLERULE")
    private String bingleRule;

    @Column(name = "IMRACPT")
    private String imraCpt;

    @Column(name = "IMRRULE")
    private String imrRule;

    public long getId() {
        return id;
    }

    public String getNvic() {
        return nvic;
    }

    public String getVehcat() {
        return vehcat;
    }

    public String getAmpacpt() {
        return ampacpt;
    }

    public String getAamacpt() {
        return aamacpt;
    }

    public String getApiacpt() {
        return apiacpt;
    }

    public String getGioacpt() {
        return gioacpt;
    }

    public String getSunacpt() {
        return sunacpt;
    }

    public String getShnacpt() {
        return shnacpt;
    }

    public String getGiociacpt() {
        return giociacpt;
    }

    public String getJciacpt() {
        return jciacpt;
    }

    public String getBingleacpt() {
        return bingleacpt;
    }

    public String getEssacpt() {
        return essacpt;
    }

    public String getVo3acpt() {
        return vo3acpt;
    }

    public String getVo5acpt() {
        return vo5acpt;
    }

    public String getAamrule() {
        return aamrule;
    }

    public String getAmprule() {
        return amprule;
    }

    public String getApiRule() {
        return apiRule;
    }

    public String getGioRule() {
        return gioRule;
    }

    public String getSunRule() {
        return sunRule;
    }

    public String getShnRule() {
        return shnRule;
    }

    public String getEssRule() {
        return essRule;
    }

    public String getVo3rule() {
        return vo3rule;
    }

    public String getV05rule() {
        return v05rule;
    }

    public String getJciRule() {
        return jciRule;
    }

    public String getGiociRule() {
        return giociRule;
    }

    public String getBingleRule() {
        return bingleRule;
    }

    public String getImraCpt() {
        return imraCpt;
    }

    public String getImrRule() {
        return imrRule;
    }
}
