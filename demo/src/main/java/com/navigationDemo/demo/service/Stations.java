package com.navigationDemo.demo.service;

import com.navigationDemo.demo.model.request.StationsReport;
import com.navigationDemo.demo.model.response.MobileStationResponse;

import java.util.UUID;

public interface Stations {
    String addstationsReport(StationsReport stationsReport) ;

    MobileStationResponse fetchMobileStations(UUID msId) ;
}
