namespace cap.eventmesh.demo;

entity OutboundDelivery {
  key DeliveryDocument: String(10);
  actualDeliveryRoute: String(6);
  shippinglocationtimezone: String(6);
  receivinglocationtimezone: String(6);
  billingDocumentDate: Date;
  billOfLading: String(35);
  completeDeliveryIsDefined: Boolean;
  confirmationTime: Time;
  createdByUser: String(12);
  creationDate: Date;
  creationTime: Time;
  customerGroup: String(2);
  deliveryBlockReason: String(2);
  deliveryDate: Date;
  deliveryDocumentBySupplier: String(35);
  deliveryDocumentType: String(4);
  deliveryIsInPlant: Boolean;
  deliveryPriority: String(2);
  deliveryTime: Time;
  deliveryVersion: String(4);
  depreciationPercentage: Decimal(5, 2);
  distrStatusByDecentralizedWrhs: String(1);
  status: String(2);
  statusDescription: String(20);
  sendToWMSDate: Date;
  shipmentConfirmedDate: Date;
  criticality_code: Integer;
}

entity Status {
  KEY ID: Integer;
  status: String; 
}