package com.navigationDemo.demo.service;

import com.navigationDemo.demo.model.request.BaseStation;
import com.navigationDemo.demo.model.request.MobileStation;
import com.navigationDemo.demo.model.request.Reports;
import com.navigationDemo.demo.model.request.StationsReport;
import com.navigationDemo.demo.model.response.MobileStationResponse;
import com.navigationDemo.demo.repository.BaseStationRepository;
import com.navigationDemo.demo.repository.MobileStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class StationsService implements Stations{

    @Autowired
    BaseStationRepository baseStationRepository;

    @Autowired
    MobileStationRepository mobileStationRepository;

    public StationsService(BaseStationRepository baseStationRepository, MobileStationRepository mobileStationRepository) {
        this.baseStationRepository = baseStationRepository;
        this.mobileStationRepository = mobileStationRepository;

        //adding sample data in the DB during instantiation.
        BaseStation base = new BaseStation( UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"), "BS1",4F,4F,500F);
        baseStationRepository.save(base);
        MobileStation mobile = new MobileStation( UUID.fromString("addc5566-de9b-11ed-b5ea-0242ac120002"), 2F, 0F);
        mobileStationRepository.save(mobile);
    }

    @Override
    public String addstationsReport(StationsReport stationsReport) {


        try{
            Reports[] reports = stationsReport.getReports();


            //1.Fetching reporting baseID's co-ordinates
            BaseStation base = baseStationRepository.findById(stationsReport.base_station_id).orElseThrow(() -> new NoSuchElementException("Value not present in DB"));
            float x = base.getX();
            float y = base.getY();

            //2.Iterating through the Received report and calculate the Mobile station co-ordinates
            for (Reports data : reports) {
                double angle = Math.atan2(y, x); // Find the angle between the point and the origin
                double lastKnownX = (x + data.distance) * Math.cos(angle); // Calculate the new x-coordinate
                double lastKnownY = (y + data.distance) * Math.sin(angle); // Calculate the new y-coordinate

                //3. Updating the Mobile station Co-ordinates in DB
                MobileStation mobile = new MobileStation(data.mobile_station_id, (float) lastKnownX, (float) lastKnownY);
                mobileStationRepository.save(mobile);
            }
        }
        catch(Exception e)
        {
            if(e instanceof NoSuchElementException)
            {
                throw new NoSuchElementException(e.getMessage());

            }
            else  throw new RuntimeException( e);
        }


        return  "Data created successfully";
    }


    @Override
    public MobileStationResponse fetchMobileStations(UUID msId) {
        try {

            //1.Fetching Mobile station Data from DB
            MobileStation mobile = mobileStationRepository.findById(msId).orElseThrow(() -> new NoSuchElementException("Mobile Station Not Found"));

            System.out.println("Mobile station found at x - "+mobile.getLastKnownX() + "y - "+mobile.getLastKnownY());

            //Happy scenario building the Response body
           MobileStationResponse findMSResponse =new MobileStationResponse(mobile,null,null,"No error");

            return findMSResponse;


        }
        catch(Exception e)
        {
            //Error Scenario 1: If Mobile station does not exist in DB NoSuchElementException is thrown from the get() methods
            if(e instanceof NoSuchElementException)
            {
                //Builing error Response body
                MobileStation  mobile = new MobileStation(msId,null,null);

                MobileStationResponse findMSErrorResponse = new MobileStationResponse(mobile,null,404,e.getMessage());
                return findMSErrorResponse;

            }
            //Error Scenario 2: catching generic exceptions.
          else  throw new RuntimeException( e);
        }


    }

}
