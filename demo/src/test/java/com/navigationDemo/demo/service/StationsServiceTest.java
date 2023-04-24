package com.navigationDemo.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navigationDemo.demo.model.request.BaseStation;
import com.navigationDemo.demo.model.request.MobileStation;
import com.navigationDemo.demo.model.request.StationsReport;
import com.navigationDemo.demo.model.response.MobileStationResponse;
import com.navigationDemo.demo.repository.BaseStationRepository;
import com.navigationDemo.demo.repository.MobileStationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class StationsServiceTest {

    @Autowired
    StationsService stationsService;


    @MockBean
    private BaseStationRepository baseStationRepository;
    @Mock
    private MobileStationRepository mobileStationRepository;
    @Mock
    private BaseStation baseStation;
    @Mock
    private MobileStation mobileStation;
    private ObjectMapper mapper = new ObjectMapper();



    @Test
    void addstationsReport() throws IOException {

        InputStream input = new FileInputStream("src/test/java/com/navigationDemo/demo/data/samplereport.json");

        StationsReport sampleBody = mapper.readValue(input, StationsReport.class);

       BaseStation baseStation = new BaseStation(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"), "base1", 4F, 4F, 500F);

       when(baseStationRepository.findById(sampleBody.base_station_id)).thenReturn(Optional.of(baseStation));

       String actual = stationsService.addstationsReport(sampleBody);
        assertEquals("Data created successfully",actual);
    }

    @Test
    void fetchMobileStationsSuccess() {

        UUID msId = UUID.fromString("addc5566-de9b-11ed-b5ea-0242ac120002");
        MobileStation mobileStation = new MobileStation( UUID.fromString("addc5566-de9b-11ed-b5ea-0242ac120002"), 2F, 0F);

        when(mobileStationRepository.findById(msId)).thenReturn(Optional.of(mobileStation));

        MobileStationResponse actual = stationsService.fetchMobileStations(msId);

        MobileStationResponse expected =new MobileStationResponse(mobileStation,null,null,"No error");

        assertEquals(expected,actual);

    }

    @Test
    void fetchMobileStationsFail() {

        UUID msId = UUID.randomUUID();
        //
        MobileStation  mobile = new MobileStation(msId,null,null);

        when(mobileStationRepository.findById(msId)).thenReturn(Optional.of(mobile));

        MobileStationResponse actual = stationsService.fetchMobileStations(msId);

        MobileStationResponse expected = new MobileStationResponse(mobile,null,404,"Mobile Station Not Found");

        assertEquals(expected,actual);

    }
}