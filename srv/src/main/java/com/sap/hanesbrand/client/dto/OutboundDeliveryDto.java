package com.sap.hanesbrand.client.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sap.hanesbrand.util.CustomDeserializer;
import com.sap.hanesbrand.util.DurationToLocalTimeDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class OutboundDeliveryDto {

    @JsonProperty("d")
    private Document document;

    @Data
    public static class Document {

        @JsonProperty("DeliveryDocument")
        private String deliveryDocument;

        @JsonProperty("ActualDeliveryRoute")
        private String actualDeliveryRoute;

        @JsonProperty("Shippinglocationtimezone")
        private String shippinglocationtimezone;

        @JsonProperty("Receivinglocationtimezone")
        private String receivinglocationtimezone;

        @JsonProperty("BillingDocumentDate")
        @JsonDeserialize(using = CustomDeserializer.class)
        private LocalDateTime billingDocumentDate;

        @JsonProperty("BillOfLading")
        private String billOfLading;

        @JsonProperty("CompleteDeliveryIsDefined")
        private boolean completeDeliveryIsDefined;

        @JsonProperty("ConfirmationTime")
        @JsonDeserialize(using = DurationToLocalTimeDeserializer.class)
        private LocalTime confirmationTime;

        @JsonProperty("CreatedByUser")
        private String createdByUser;

        @JsonProperty("CreationDate")
        @JsonDeserialize(using = CustomDeserializer.class)
        private LocalDateTime creationDate;

        @JsonProperty("CreationTime")
        @JsonDeserialize(using = DurationToLocalTimeDeserializer.class)
        private LocalTime creationTime;

        @JsonProperty("CustomerGroup")
        private String customerGroup;

        @JsonProperty("DeliveryBlockReason")
        private String deliveryBlockReason;

        @JsonProperty("DeliveryDate")
        @JsonDeserialize(using = CustomDeserializer.class)
        private LocalDateTime deliveryDate;

        @JsonProperty("DeliveryDocumentBySupplier")
        private String deliveryDocumentBySupplier;

        @JsonProperty("DeliveryDocumentType")
        private String deliveryDocumentType;

        @JsonProperty("DeliveryIsInPlant")
        private boolean deliveryIsInPlant;

        @JsonProperty("DeliveryPriority")
        private String deliveryPriority;

        @JsonProperty("DeliveryTime")
        @JsonDeserialize(using = DurationToLocalTimeDeserializer.class)
        private LocalTime deliveryTime;

        @JsonProperty("DeliveryVersion")
        private String deliveryVersion;

        @JsonProperty("DepreciationPercentage")
        private double depreciationPercentage;

        @JsonProperty("DistrStatusByDecentralizedWrhs")
        private String distrStatusByDecentralizedWrhs;
    }


}
