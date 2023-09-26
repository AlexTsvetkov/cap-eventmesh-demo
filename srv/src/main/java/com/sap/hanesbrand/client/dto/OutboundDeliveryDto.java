package com.sap.hanesbrand.client.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        private String billingDocumentDate;

        @JsonProperty("BillOfLading")
        private String billOfLading;

        @JsonProperty("CompleteDeliveryIsDefined")
        private boolean completeDeliveryIsDefined;

        @JsonProperty("ConfirmationTime")
        private String confirmationTime;

        @JsonProperty("CreatedByUser")
        private String createdByUser;

        @JsonProperty("CreationDate")
        private String creationDate;

        @JsonProperty("CreationTime")
        private String creationTime;

        @JsonProperty("CustomerGroup")
        private String customerGroup;

        @JsonProperty("DeliveryBlockReason")
        private String deliveryBlockReason;

        @JsonProperty("DeliveryDate")
        private String deliveryDate;

        @JsonProperty("DeliveryDocumentBySupplier")
        private String deliveryDocumentBySupplier;

        @JsonProperty("DeliveryDocumentType")
        private String deliveryDocumentType;

        @JsonProperty("DeliveryIsInPlant")
        private boolean deliveryIsInPlant;

        @JsonProperty("DeliveryPriority")
        private String deliveryPriority;

        @JsonProperty("DeliveryTime")
        private String deliveryTime;

        @JsonProperty("DeliveryVersion")
        private String deliveryVersion;

        @JsonProperty("DepreciationPercentage")
        private double depreciationPercentage;

        @JsonProperty("DistrStatusByDecentralizedWrhs")
        private String distrStatusByDecentralizedWrhs;
    }


}
