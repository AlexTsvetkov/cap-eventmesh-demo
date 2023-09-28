package com.sap.hanesbrand.client.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConfirmShipmentDto {

    @JsonProperty("deliveryDocument")
    String deliveryDocument;
}
