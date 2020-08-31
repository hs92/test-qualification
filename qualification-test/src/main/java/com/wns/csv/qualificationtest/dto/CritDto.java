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
}
