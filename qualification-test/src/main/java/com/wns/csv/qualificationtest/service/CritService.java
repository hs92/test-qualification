package com.wns.csv.qualificationtest.service;

import com.wns.csv.qualificationtest.dto.CritDto;
import com.wns.csv.qualificationtest.dto.VehcatDto;
import com.wns.csv.qualificationtest.mapper.CritMapper;
import com.wns.csv.qualificationtest.model.Crit;
import com.wns.csv.qualificationtest.repository.CritDao;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CritService {

    private final String APPEND_DAY = "01";

    private final String APPEND_TIMESTAMP = "01000000";

    private final String effectiveEndDate = "99991231";

    private final String effectiveEndDateTimeStamp = "99991231000000";

    @Resource
    private CritDao critDao;

    public List<VehcatDto> getVehcatData() {

        List<Crit> critEntity = critDao.findAll();
        List<CritDto> dtoList = CritMapper.toDtoList(critEntity);
        setDateFields(dtoList);

        List<CritDto> guList = dtoList.stream().filter(dto -> "GU".equals(dto.getVehcat())).collect(Collectors.toList());
        List<VehcatDto> guVehcatList = populateGuVehcatData(guList);

        List<CritDto> ciList = dtoList.stream().filter(dto -> "CI".equals(dto.getVehcat())).collect(Collectors.toList());
        List<VehcatDto> ciVehcatList = populateCiVehcatData(ciList);

        List<CritDto> bkList = dtoList.stream().filter(dto -> "BK".equals(dto.getVehcat())).collect(Collectors.toList());
        List<VehcatDto> bkVehcatList = populateBkVehcatData(bkList);

        List<VehcatDto> finalCrtExt = new ArrayList<>(0);
        finalCrtExt.addAll(guVehcatList);
        finalCrtExt.addAll(ciVehcatList);
        finalCrtExt.addAll(bkVehcatList);
        setAddMod(finalCrtExt);

        finalCrtExt = finalCrtExt.stream().filter(vehcatDto -> !("CI".equals(vehcatDto.getVehcat())
                && vehcatDto.getNvic().startsWith("!"))).collect(Collectors.toList());
        finalCrtExt.sort(Comparator.comparing(VehcatDto::getVehcat)
                .thenComparing(VehcatDto::getCompany)
                .thenComparing(VehcatDto::getNvic));
        return finalCrtExt;
    }

    private void setAddMod(List<VehcatDto> finalCrtExt) {

        if (!CollectionUtils.isEmpty(finalCrtExt)) {
            finalCrtExt.forEach(vehcatDto -> vehcatDto.setAddMod("ADD"));
        }
    }

    private List<VehcatDto> populateBkVehcatData(List<CritDto> bkList) {

        List<VehcatDto> bkVehcatList = new ArrayList<>(0);
        if (!CollectionUtils.isEmpty(bkList)) {
            List<VehcatDto> bkAamiCompanyList = bkList.stream().filter(bk -> bk.getAamacpt() != null && bk.getAamrule() != null)
                    .map(bk -> new VehcatDto(null, bk.getNvic(), bk.getVehcat(), bk.getEffectiveDate(), bk.getChangeTimestamp(),
                            bk.getEffectiveEndDate(), bk.getEndDateTimestamp(), "AAMI", bk.getAamacpt(), bk.getAamrule())).collect(Collectors.toList());
            List<VehcatDto> bkApiaCompanyList = bkList.stream().filter(bk -> bk.getApiacpt() != null && bk.getApiRule() != null)
                    .map(bk -> new VehcatDto(null, bk.getNvic(), bk.getVehcat(), bk.getEffectiveDate(), bk.getChangeTimestamp(),
                            bk.getEffectiveEndDate(), bk.getEndDateTimestamp(), "APIA", bk.getApiacpt(), bk.getApiRule())).collect(Collectors.toList());
            List<VehcatDto> bkSunCorpCompanyList = bkList.stream().filter(bk -> bk.getSunacpt() != null && bk.getSunRule() != null)
                    .map(bk -> new VehcatDto(null, bk.getNvic(), bk.getVehcat(), bk.getEffectiveDate(), bk.getChangeTimestamp(),
                            bk.getEffectiveEndDate(), bk.getEndDateTimestamp(), "SUNCORP", bk.getSunacpt(), bk.getSunRule())).collect(Collectors.toList());
            List<VehcatDto> bkGioCompanyList = bkList.stream().filter(bk -> bk.getGioacpt() != null && bk.getGioRule() != null)
                    .map(bk -> new VehcatDto(null, bk.getNvic(), bk.getVehcat(), bk.getEffectiveDate(), bk.getChangeTimestamp(),
                            bk.getEffectiveEndDate(), bk.getEndDateTimestamp(), "GIO", bk.getGioacpt(), bk.getGioRule())).collect(Collectors.toList());
            List<VehcatDto> bkShnCompanyList = bkList.stream().filter(bk -> bk.getShnacpt() != null && bk.getShnRule() != null)
                    .map(bk -> new VehcatDto(null, bk.getNvic(), bk.getVehcat(), bk.getEffectiveDate(), bk.getChangeTimestamp(),
                            bk.getEffectiveEndDate(), bk.getEndDateTimestamp(), "SHANNONS", bk.getShnacpt(), bk.getShnRule())).collect(Collectors.toList());
            List<VehcatDto> bkAmpCompanyList = bkList.stream().filter(bk -> bk.getAmpacpt() != null && bk.getAmprule() != null)
                    .map(bk -> new VehcatDto(null, bk.getNvic(), bk.getVehcat(), bk.getEffectiveDate(), bk.getChangeTimestamp(),
                            bk.getEffectiveEndDate(), bk.getEndDateTimestamp(), "AMP", bk.getAmpacpt(), bk.getAmprule())).collect(Collectors.toList());
            List<VehcatDto> bkImrCompanyList = bkList.stream().filter(bk -> bk.getImraCpt() != null && bk.getImrRule() != null)
                    .map(bk -> new VehcatDto(null, bk.getNvic(), bk.getVehcat(), bk.getEffectiveDate(), bk.getChangeTimestamp(),
                            bk.getEffectiveEndDate(), bk.getEndDateTimestamp(), "IMR", bk.getImraCpt(), bk.getImrRule())).collect(Collectors.toList());
            bkVehcatList.addAll(bkAamiCompanyList);
            bkVehcatList.addAll(bkApiaCompanyList);
            bkVehcatList.addAll(bkSunCorpCompanyList);
            bkVehcatList.addAll(bkGioCompanyList);
            bkVehcatList.addAll(bkShnCompanyList);
            bkVehcatList.addAll(bkAmpCompanyList);
            bkVehcatList.addAll(bkImrCompanyList);
        }
        return bkVehcatList;
    }

    private List<VehcatDto> populateCiVehcatData(List<CritDto> ciList) {

        List<VehcatDto> ciVehcatList = new ArrayList<>(0);
        if (!CollectionUtils.isEmpty(ciList)) {
            List<VehcatDto> gioCiCompanyList = ciList.stream().filter(ci -> ci.getGiociacpt() != null && ci.getGiociRule() != null)
                    .map(ci -> new VehcatDto(null, ci.getNvic(), ci.getVehcat(), ci.getEffectiveDate(), ci.getChangeTimestamp(),
                            ci.getEffectiveEndDate(), ci.getEndDateTimestamp(), "GIOCI", ci.getGiociacpt(), ci.getGiociRule())).collect(Collectors.toList());
            ciVehcatList.addAll(gioCiCompanyList);
        }
        return ciVehcatList;
    }

    private List<VehcatDto> populateGuVehcatData(List<CritDto> guList) {

        List<VehcatDto> guVehcatList = new ArrayList<>(0);
        if (!CollectionUtils.isEmpty(guList)) {
            List<VehcatDto> guAamiCompanyList = guList.stream().filter(gu -> gu.getAamacpt() != null && gu.getAamrule() != null)
                    .map(gu -> new VehcatDto(null, gu.getNvic(), gu.getVehcat(), gu.getEffectiveDate(), gu.getChangeTimestamp(),
                            gu.getEffectiveEndDate(), gu.getEndDateTimestamp(), "AAMI", gu.getAamacpt(), gu.getAamrule())).collect(Collectors.toList());
            List<VehcatDto> guApiaCompanyList = guList.stream().filter(gu -> gu.getApiacpt() != null && gu.getApiRule() != null)
                    .map(gu -> new VehcatDto(null, gu.getNvic(), gu.getVehcat(), gu.getEffectiveDate(), gu.getChangeTimestamp(),
                            gu.getEffectiveEndDate(), gu.getEndDateTimestamp(), "APIA", gu.getApiacpt(), gu.getApiRule())).collect(Collectors.toList());
            List<VehcatDto> guSunCorpCompanyList = guList.stream().filter(gu -> gu.getSunacpt() != null && gu.getSunRule() != null)
                    .map(gu -> new VehcatDto(null, gu.getNvic(), gu.getVehcat(), gu.getEffectiveDate(), gu.getChangeTimestamp(),
                            gu.getEffectiveEndDate(), gu.getEndDateTimestamp(), "SUNCORP", gu.getSunacpt(), gu.getSunRule())).collect(Collectors.toList());
            List<VehcatDto> guVero3CompanyList = guList.stream().filter(gu -> gu.getVo3acpt() != null && gu.getVo3rule() != null)
                    .map(gu -> new VehcatDto(null, gu.getNvic(), gu.getVehcat(), gu.getEffectiveDate(), gu.getChangeTimestamp(),
                            gu.getEffectiveEndDate(), gu.getEndDateTimestamp(), "VERO3", gu.getVo3acpt(), gu.getVo3rule())).collect(Collectors.toList());
            List<VehcatDto> guVero5CompanyList = guList.stream().filter(gu -> gu.getVo5acpt() != null && gu.getV05rule() != null)
                    .map(gu -> new VehcatDto(null, gu.getNvic(), gu.getVehcat(), gu.getEffectiveDate(), gu.getChangeTimestamp(),
                            gu.getEffectiveEndDate(), gu.getEndDateTimestamp(), "VERO5", gu.getVo5acpt(), gu.getV05rule())).collect(Collectors.toList());
            List<VehcatDto> guGioCompanyList = guList.stream().filter(gu -> gu.getGioacpt() != null && gu.getGioRule() != null)
                    .map(gu -> new VehcatDto(null, gu.getNvic(), gu.getVehcat(), gu.getEffectiveDate(), gu.getChangeTimestamp(),
                            gu.getEffectiveEndDate(), gu.getEndDateTimestamp(), "GIO", gu.getGioacpt(), gu.getGioRule())).collect(Collectors.toList());
            List<VehcatDto> guEssCompanyList = guList.stream().filter(gu -> gu.getEssacpt() != null && gu.getEssRule() != null)
                    .map(gu -> new VehcatDto(null, gu.getNvic(), gu.getVehcat(), gu.getEffectiveDate(), gu.getChangeTimestamp(),
                            gu.getEffectiveEndDate(), gu.getEndDateTimestamp(), "ESSENTIALS", gu.getEssacpt(), gu.getEssRule())).collect(Collectors.toList());
            List<VehcatDto> guBingleCompanyList = guList.stream().filter(gu -> gu.getBingleacpt() != null && gu.getBingleRule() != null)
                    .map(gu -> new VehcatDto(null, gu.getNvic(), gu.getVehcat(), gu.getEffectiveDate(), gu.getChangeTimestamp(),
                            gu.getEffectiveEndDate(), gu.getEndDateTimestamp(), "BINGLE", gu.getBingleacpt(), gu.getBingleRule())).collect(Collectors.toList());
            List<VehcatDto> guGioCiCompanyList = guList.stream().filter(gu -> gu.getGiociacpt() != null && gu.getGiociRule() != null)
                    .map(gu -> new VehcatDto(null, gu.getNvic(), gu.getVehcat(), gu.getEffectiveDate(), gu.getChangeTimestamp(),
                            gu.getEffectiveEndDate(), gu.getEndDateTimestamp(), "GIOCI", gu.getGiociacpt(), gu.getGiociRule())).collect(Collectors.toList());
            List<VehcatDto> guJciCompanyList = guList.stream().filter(gu -> gu.getJciacpt() != null && gu.getJciRule() != null)
                    .map(gu -> new VehcatDto(null, gu.getNvic(), gu.getVehcat(), gu.getEffectiveDate(), gu.getChangeTimestamp(),
                            gu.getEffectiveEndDate(), gu.getEndDateTimestamp(), "JCI", gu.getJciacpt(), gu.getJciRule())).collect(Collectors.toList());
            List<VehcatDto> guShnCompanyList = guList.stream().filter(gu -> gu.getShnacpt() != null && gu.getShnRule() != null)
                    .map(gu -> new VehcatDto(null, gu.getNvic(), gu.getVehcat(), gu.getEffectiveDate(), gu.getChangeTimestamp(),
                            gu.getEffectiveEndDate(), gu.getEndDateTimestamp(), "SHANNONS", gu.getShnacpt(), gu.getShnRule())).collect(Collectors.toList());
            List<VehcatDto> guAmpCompanyList = guList.stream().filter(gu -> gu.getAmpacpt() != null && gu.getAmprule() != null)
                    .map(gu -> new VehcatDto(null, gu.getNvic(), gu.getVehcat(), gu.getEffectiveDate(), gu.getChangeTimestamp(),
                            gu.getEffectiveEndDate(), gu.getEndDateTimestamp(), "AMP", gu.getAmpacpt(), gu.getAmprule())).collect(Collectors.toList());

            guVehcatList.addAll(guAamiCompanyList);
            guVehcatList.addAll(guApiaCompanyList);
            guVehcatList.addAll(guSunCorpCompanyList);
            guVehcatList.addAll(guVero3CompanyList);
            guVehcatList.addAll(guVero5CompanyList);
            guVehcatList.addAll(guGioCompanyList);
            guVehcatList.addAll(guEssCompanyList);
            guVehcatList.addAll(guBingleCompanyList);
            guVehcatList.addAll(guGioCiCompanyList);
            guVehcatList.addAll(guJciCompanyList);
            guVehcatList.addAll(guShnCompanyList);
            guVehcatList.addAll(guAmpCompanyList);
        }

        return guVehcatList;
    }

    private void setDateFields(List<CritDto> dtoList) {

        DateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(Instant.now()));
        calendar.add(Calendar.YEAR, -2);
        Date previousDate = calendar.getTime();
        String result = dateFormat.format(previousDate);
        if (!CollectionUtils.isEmpty(dtoList)) {
            dtoList.forEach(dto-> {
                dto.setEffectiveDate(result + APPEND_DAY);
                dto.setChangeTimestamp(result + APPEND_TIMESTAMP);
                dto.setEffectiveEndDate(effectiveEndDate);
                dto.setEndDateTimestamp(effectiveEndDateTimeStamp);
            });
        }
    }
}
