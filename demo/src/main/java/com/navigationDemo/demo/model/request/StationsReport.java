package com.navigationDemo.demo.model.request;



import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class StationsReport {
    public UUID base_station_id;
    public  Reports reports[];
}
