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
}
