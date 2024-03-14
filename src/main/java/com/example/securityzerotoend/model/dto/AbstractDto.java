package com.example.securityzerotoend.model.dto;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AbstractDto {
    private String id;

    private Long version;

    private Date insertTimeStamp;

    private Date lastUpdateTimeStamp;
}
