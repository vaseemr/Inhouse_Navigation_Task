package com.navigationDemo.demo.model.request;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Reports {

    public UUID mobile_station_id;
    public Float distance;
    public Timestamp timestamp;
}