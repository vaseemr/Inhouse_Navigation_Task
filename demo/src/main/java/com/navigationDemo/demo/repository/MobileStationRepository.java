package com.navigationDemo.demo.repository;

import com.navigationDemo.demo.model.request.MobileStation;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MobileStationRepository extends CrudRepository<MobileStation, UUID> {
}
