namespace com.sap.hanesbrand;

using {managed, extensible} from '@sap/cds/common';

entity OutboundDelivery {
  key DeliveryDocument: String(10) @title : 'Delivery document';
  actualDeliveryRoute: String(6) @title : 'Delivery route';
  shippinglocationtimezone: String(6);
  receivinglocationtimezone: String(6);
  billingDocumentDate: Date;
  billOfLading: String(35);
  completeDeliveryIsDefined: Boolean @title : 'Complete delivery';
  confirmationTime: Time @title : 'Conformation time';
  createdByUser: String(12) @title : 'Created by';
  creationDate: Date @title : 'Creation date';
  creationTime: Time;
  customerGroup: String(2) @title : 'Customer group';
  deliveryBlockReason: String(2) @title : 'Block reason';
  deliveryDate: Date @title : 'Delivery date';
  deliveryDocumentBySupplier: String(35) @title : 'Delivery document by supplier';
  deliveryDocumentType: String(4) @title : 'Delivery document type';
  deliveryIsInPlant: Boolean @title : 'Is plant';
  deliveryPriority: String(2);
  deliveryTime: Time;
  deliveryVersion: String(4);
  depreciationPercentage: Decimal(5, 2);
  distrStatusByDecentralizedWrhs: String(1);
  status: String(2) @title : 'Status';
  statusDescription: String(20) @title : 'Status description';
  sendToWMSDate: Date;
  shipmentConfirmedDate: Date;
  criticality: String;
}