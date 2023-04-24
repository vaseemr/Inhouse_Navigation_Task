package com.navigationDemo.demo.controller;

import com.navigationDemo.demo.model.request.StationsReport;
import com.navigationDemo.demo.model.response.MobileStationResponse;
import com.navigationDemo.demo.service.Stations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "api")
@Slf4j
public class StationController {

    @Autowired Stations stations;
//    @Tag(name = "Stations")
//    @Operation(summary = "API to post mobile station " , description = "To Add the Mobile stations detected by the Base station" )
//    @ApiResponses(value= {
//            @ApiResponse(responseCode = "201", description = "Created"),
//            @ApiResponse(responseCode = "400", description = "Bad Request"),
//            @ApiResponse(responseCode = "404", description = "Not Found"),
//            @ApiResponse(responseCode = "500", description = "Server Error")
//    })
    @PostMapping(path = "/addMS")
    @ResponseStatus(HttpStatus.CREATED)
    public String addReports (
            @RequestBody StationsReport stationsReport
            )
    {

          return stations.addstationsReport(stationsReport);

    }


//        @Tag(name = "Stations")
//    @Operation(summary = "API to get mobile station " , description = "To fetch the Mobile stations by id" )
//    @ApiResponses(value= {
//            @ApiResponse(responseCode = "201", description = "Created"),
//            @ApiResponse(responseCode = "400", description = "Bad Request"),
//            @ApiResponse(responseCode = "404", description = "Not Found"),
//            @ApiResponse(responseCode = "500", description = "Server Error")
//    })
    @GetMapping(path = "/fetchMS/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public MobileStationResponse fetchMS (
//            @Parameter(name = "uuid", description = "Mobile Station ID", required = true) @PathVariable("uuid") String msId,
            @PathVariable("uuid") UUID msId
    )
    {

            return stations.fetchMobileStations(msId);
    }
}
