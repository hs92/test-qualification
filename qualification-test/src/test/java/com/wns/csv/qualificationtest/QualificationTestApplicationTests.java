package com.wns.csv.qualificationtest;

import com.wns.csv.qualificationtest.controller.CritController;
import com.wns.csv.qualificationtest.dto.VehcatDto;
import com.wns.csv.qualificationtest.model.Crit;
import com.wns.csv.qualificationtest.repository.CritDao;
import com.wns.csv.qualificationtest.service.CritService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class QualificationTestApplicationTests {

	@MockBean
	private CritDao critDao;

	@Resource
	private CritService critService;

	@Resource
	private CritController critController;

	@Test
	void exportToCsv_1() {

		when(critDao.findAll()).thenReturn(null);

		ResponseEntity<?> responseEntity = critController.exportToCsv();
		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
		assertEquals("No data found to export!", responseEntity.getBody());
	}

	@Test
	void exportToCsv_2() {

		List<Crit> critEntity = Arrays.asList(new Crit(1L,"07F620","GU","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","N","N","Y","N","N","N","N","N","N","Y","N","N",null,"N"),
				new Crit(2L,"08F621","GU","UNCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","Y","N","N","Y","N","N","N","N","N","N","N","N","N",null),
				new Crit(3L,"09F622","CI","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","N","Y","N","N","N","Y","N","N","N","N","N","N","N",null),
				new Crit(4L,"02F623","CI","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","N","N","N","N","N","N","N","Y","N","N","N","N","N",null),
				new Crit(5L,"01F624","BK","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","Y","N","N","N","N","N","N","N","N","N","N","Y",null,null),
				new Crit(6L,"05F625","BK","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","N","Y","N","N","N","N","N","N","N","Y","N","N",null,"N"),
				new Crit(7L,"!09F622","CI","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","N","Y","N","N","N","Y","N","N","N","N","N","N","N",null));

		when(critDao.findAll()).thenReturn(critEntity);

		ResponseEntity<?> responseEntity = critController.exportToCsv();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(responseEntity.getHeaders().get("Content-Disposition").get(0), "attachment; filename=FINAL_CRT_EXT.xslx");
	}

	@Test
	void exportToCsv_3() {

		List<Crit> critEntity = Arrays.asList(new Crit(5L,"01F624","BK","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","Y","N","N","N","N","N","N","N","N","N","N","Y",null,null),
				new Crit(6L,"05F625","BK","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","N","Y","N","N","N","N","N","N","N","Y","N","N",null,"N"));

		when(critDao.findAll()).thenReturn(critEntity);

		List<VehcatDto> vehcatData = critService.getVehcatData();

		List<VehcatDto> ciList = vehcatData.stream().filter(dto -> "CI".equals(dto.getVehcat())).collect(Collectors.toList());
		List<VehcatDto> guList = vehcatData.stream().filter(dto -> "GU".equals(dto.getVehcat())).collect(Collectors.toList());
		List<VehcatDto> bkList = vehcatData.stream().filter(dto -> "BK".equals(dto.getVehcat())).collect(Collectors.toList());

		long gioCount = critEntity.stream().filter(dto -> dto.getGioRule() != null && dto.getGioacpt() != null).count();
		long ampCount = critEntity.stream().filter(dto -> dto.getAmpacpt() != null && dto.getAmprule() != null).count();
		long aamCount = critEntity.stream().filter(dto -> dto.getAamacpt() != null && dto.getAamrule() != null).count();
		long apiaCount = critEntity.stream().filter(dto -> dto.getApiacpt() != null && dto.getApiRule() != null).count();
		long suncorpCount = critEntity.stream().filter(dto -> dto.getSunRule() != null && dto.getSunacpt() != null).count();
		long shannonsCount = critEntity.stream().filter(dto -> dto.getShnRule() != null && dto.getShnacpt() != null).count();
		long imrCount = critEntity.stream().filter(dto -> dto.getImraCpt() != null && dto.getImrRule() != null).count();

		long expectedSize = gioCount + ampCount + aamCount + apiaCount + suncorpCount + shannonsCount + imrCount;

		// checking if no other vehicle cat is present in result
		assertEquals(0, ciList.size());
		assertEquals(0, guList.size());

		// checked for count of companies
		assertEquals(gioCount, bkList.stream().filter(b -> "GIO".equals(b.getCompany())).count());
		assertEquals(ampCount, bkList.stream().filter(b -> "AMP".equals(b.getCompany())).count());
		assertEquals(aamCount, bkList.stream().filter(b -> "AAMI".equals(b.getCompany())).count());
		assertEquals(apiaCount, bkList.stream().filter(b -> "APIA".equals(b.getCompany())).count());
		assertEquals(suncorpCount, bkList.stream().filter(b -> "SUNCORP".equals(b.getCompany())).count());
		assertEquals(shannonsCount, bkList.stream().filter(b -> "SHANNONS".equals(b.getCompany())).count());
		assertEquals(imrCount, bkList.stream().filter(b -> "IMR".equals(b.getCompany())).count());

		// total size
		assertEquals(expectedSize, vehcatData.size());
	}

	@Test
	void exportToCsv_4() {

		List<Crit> critEntity = Arrays.asList(new Crit(3L,"09F622","CI","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","N","Y","N","N","N","Y","N","N","N","N","N","N","N",null),
				new Crit(4L,"02F623","CI","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","N","N","N","N","N","N","N","Y","N","N","N","N","N",null),
				new Crit(7L,"!09F622","CI","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","N","Y","N","N","N","Y","N","N","N","N","N","N","N",null));

		when(critDao.findAll()).thenReturn(critEntity);

		List<VehcatDto> vehcatData = critService.getVehcatData();

		List<VehcatDto> guList = vehcatData.stream().filter(dto -> "GU".equals(dto.getVehcat())).collect(Collectors.toList());
		List<VehcatDto> ciList = vehcatData.stream().filter(dto -> ("CI".equals(dto.getVehcat()) && dto.getNvic().startsWith("!"))).collect(Collectors.toList());
		List<VehcatDto> bkList = vehcatData.stream().filter(dto -> "BK".equals(dto.getVehcat())).collect(Collectors.toList());

		long giociCount = critEntity.stream().filter(dto -> dto.getGiociRule() != null && dto.getGioacpt() != null && !dto.getNvic().startsWith("!")).count();

		assertEquals(0, guList.size());
		assertEquals(0, ciList.size());
		assertEquals(0, bkList.size());
		assertEquals(giociCount, vehcatData.stream().filter(b -> "GIOCI".equals(b.getCompany())).count());
	}

	@Test
	void exportToCsv_5() {

		List<Crit> critEntity = Arrays.asList(new Crit(1L,"07F620","GU","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","N","N","Y","N","N","N","N","N","N","Y","N","N",null,"N"),
				new Crit(2L,"08F621","GU","UNCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","Y","N","N","Y","N","N","N","N","N","N","N","N","N",null));

		when(critDao.findAll()).thenReturn(critEntity);

		List<VehcatDto> vehcatData = critService.getVehcatData();

		List<VehcatDto> ciList = vehcatData.stream().filter(dto -> "CI".equals(dto.getVehcat())).collect(Collectors.toList());
		List<VehcatDto> bkList = vehcatData.stream().filter(dto -> "BK".equals(dto.getVehcat())).collect(Collectors.toList());
		List<VehcatDto> guList = vehcatData.stream().filter(dto -> "GU".equals(dto.getVehcat())).collect(Collectors.toList());

		long aamCount = critEntity.stream().filter(dto -> dto.getAamacpt() != null && dto.getAamrule() != null).count();
		long apiaCount = critEntity.stream().filter(dto -> dto.getApiacpt() != null && dto.getApiRule() != null).count();
		long suncorpCount = critEntity.stream().filter(dto -> dto.getSunRule() != null && dto.getSunacpt() != null).count();
		long vo3Count = critEntity.stream().filter(dto -> dto.getVo3rule() != null && dto.getVo3acpt() != null).count();
		long vo5Count = critEntity.stream().filter(dto -> dto.getVo5acpt() != null && dto.getV05rule() != null).count();
		long gioCount = critEntity.stream().filter(dto -> dto.getGioRule() != null && dto.getGioacpt() != null).count();
		long essentialsCount = critEntity.stream().filter(dto -> dto.getEssRule() != null && dto.getEssacpt() != null).count();
		long bingleCount = critEntity.stream().filter(dto -> dto.getBingleRule() != null && dto.getBingleacpt() != null).count();
		long giociCount = critEntity.stream().filter(dto -> dto.getGiociRule() != null && dto.getGioacpt() != null).count();
		long jciCount = critEntity.stream().filter(dto -> dto.getJciRule() != null && dto.getJciacpt() != null).count();
		long shannonsCount = critEntity.stream().filter(dto -> dto.getShnRule() != null && dto.getShnacpt() != null).count();
		long ampCount = critEntity.stream().filter(dto -> dto.getAmpacpt() != null && dto.getAmprule() != null).count();

		assertEquals(0, ciList.size());
		assertEquals(0, bkList.size());

		// checking for individual companies
		assertEquals(gioCount, guList.stream().filter(b -> "GIO".equals(b.getCompany())).count());
		assertEquals(ampCount, guList.stream().filter(b -> "AMP".equals(b.getCompany())).count());
		assertEquals(aamCount, guList.stream().filter(b -> "AAMI".equals(b.getCompany())).count());
		assertEquals(apiaCount, guList.stream().filter(b -> "APIA".equals(b.getCompany())).count());
		assertEquals(suncorpCount, guList.stream().filter(b -> "SUNCORP".equals(b.getCompany())).count());
		assertEquals(shannonsCount, guList.stream().filter(b -> "SHANNONS".equals(b.getCompany())).count());
		assertEquals(vo3Count, guList.stream().filter(b -> "VERO3".equals(b.getCompany())).count());
		assertEquals(vo5Count, guList.stream().filter(b -> "VERO5".equals(b.getCompany())).count());
		assertEquals(essentialsCount, guList.stream().filter(b -> "ESSENTIALS".equals(b.getCompany())).count());
		assertEquals(bingleCount, guList.stream().filter(b -> "BINGLE".equals(b.getCompany())).count());
		assertEquals(jciCount, guList.stream().filter(b -> "JCI".equals(b.getCompany())).count());
		assertEquals(giociCount, guList.stream().filter(b -> "GIOCI".equals(b.getCompany())).count());
	}

	@Test
	void exportToCsv_6() {

		List<Crit> critEntity = null;

		when(critDao.findAll()).thenReturn(critEntity);

		List<VehcatDto> vehcatData = critService.getVehcatData();

		List<VehcatDto> ciList = vehcatData.stream().filter(dto -> "CI".equals(dto.getVehcat())).collect(Collectors.toList());
		List<VehcatDto> bkList = vehcatData.stream().filter(dto -> "BK".equals(dto.getVehcat())).collect(Collectors.toList());
		List<VehcatDto> guList = vehcatData.stream().filter(dto -> "GU".equals(dto.getVehcat())).collect(Collectors.toList());

		assertEquals(0, vehcatData.size());
		assertEquals(0, ciList.size());
		assertEquals(0, bkList.size());
		assertEquals(0, guList.size());
	}
}
