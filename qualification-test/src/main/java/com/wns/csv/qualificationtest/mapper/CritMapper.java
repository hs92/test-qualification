package com.wns.csv.qualificationtest.mapper;

import com.wns.csv.qualificationtest.dto.CritDto;
import com.wns.csv.qualificationtest.model.Crit;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CritMapper {

    public static List<CritDto> toDtoList(List<Crit> critEntityList) {

        List<CritDto> critDtoList = new ArrayList<>(0);
        if (!CollectionUtils.isEmpty(critEntityList)) {
            critDtoList = critEntityList.stream().map(critEntity -> new CritDto(critEntity.getId(), critEntity.getNvic(), critEntity.getVehcat(), null, null, null, null,
                    critEntity.getAmpacpt(), critEntity.getAamacpt(), critEntity.getApiacpt(), critEntity.getGioacpt(), critEntity.getSunacpt(), critEntity.getShnacpt(),
                    critEntity.getGiociacpt(), critEntity.getJciacpt(), critEntity.getBingleacpt(), critEntity.getEssacpt(), critEntity.getVo3acpt(), critEntity.getVo5acpt(),
                    critEntity.getAamrule(), critEntity.getAmprule(), critEntity.getApiRule(), critEntity.getGioRule(),critEntity.getSunRule(), critEntity.getShnRule(),
                    critEntity.getEssRule(), critEntity.getVo3rule(),critEntity.getV05rule(), critEntity.getJciRule(), critEntity.getGiociRule(), critEntity.getBingleRule(),
                    critEntity.getImraCpt() , critEntity.getImrRule())).collect(Collectors.toList());
        }
        return critDtoList;
    }
}
