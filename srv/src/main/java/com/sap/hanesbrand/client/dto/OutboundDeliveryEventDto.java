package com.sap.hanesbrand.client.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class OutboundDeliveryEventDto {

    @JsonProperty("type")
    String type;

    @JsonProperty("source")
    String source;

    @JsonProperty("id")
    String createdID;

    @JsonProperty("time")
    String createdTime;

    @JsonProperty("data")
    Map<String, String> data;

}
