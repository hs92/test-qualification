package com.wns.csv.qualificationtest.service;

import com.wns.csv.qualificationtest.model.Crit;
import com.wns.csv.qualificationtest.repository.CritDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CritService {

    @Resource
    private CritDao critDao;

    public String getCsv() {
        List<Crit> all = critDao.findAll();
        return "Demo String";
    }
}
