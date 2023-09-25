package com.sap.hanesbrand.client.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryDocumentEvent {

    @JsonProperty("DeliveryDocument")
    private String DeliveryDocument;

}
