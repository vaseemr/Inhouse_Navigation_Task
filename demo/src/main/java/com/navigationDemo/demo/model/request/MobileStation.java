package com.navigationDemo.demo.model.request;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class MobileStation {
    @Id
    @Type(type="uuid-char")
    private UUID mobileId;
    private Float lastKnownX;
    private Float lastKnownY;
}
