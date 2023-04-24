package com.navigationDemo.demo.repository;

import com.navigationDemo.demo.model.request.BaseStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface BaseStationRepository extends JpaRepository<BaseStation, UUID> {


}
