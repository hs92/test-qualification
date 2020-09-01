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

		assertEquals(0, ciList.size());
		assertEquals(0, guList.size());
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

		assertEquals(0, guList.size());
		assertEquals(0, ciList.size());
		assertEquals(0, bkList.size());
	}

	@Test
	void exportToCsv_5() {

		List<Crit> critEntity = Arrays.asList(new Crit(1L,"07F620","GU","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","N","N","Y","N","N","N","N","N","N","Y","N","N",null,"N"),
				new Crit(2L,"08F621","GU","UNCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","UNCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","ACCEPTABLE","Y","N","N","Y","N","N","N","N","N","N","N","N","N",null));

		when(critDao.findAll()).thenReturn(critEntity);

		List<VehcatDto> vehcatData = critService.getVehcatData();

		List<VehcatDto> ciList = vehcatData.stream().filter(dto -> "CI".equals(dto.getVehcat())).collect(Collectors.toList());
		List<VehcatDto> bkList = vehcatData.stream().filter(dto -> "BK".equals(dto.getVehcat())).collect(Collectors.toList());

		assertEquals(0, ciList.size());
		assertEquals(0, bkList.size());
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
