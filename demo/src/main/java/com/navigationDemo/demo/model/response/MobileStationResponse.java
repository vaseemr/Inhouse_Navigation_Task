package com.navigationDemo.demo.model.response;

import com.navigationDemo.demo.model.request.MobileStation;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MobileStationResponse {

    private MobileStation mobileStation;
    private Float error_radius;
    private Integer error_code;
    private String error_description;

}
