namespace com.sap.hanesbrand;

using {managed, extensible} from '@sap/cds/common';

entity OutboundDelivery {
  key DeliveryDocument: String(10) @title : 'Delivery document';
  actualDeliveryRoute: String(6) @title : 'Delivery route';
  shippinglocationtimezone: String(6);
  receivinglocationtimezone: String(6);
  billingDocumentDate: String(25) @title : 'Billing document date'; // Increased length to 14
  billOfLading: String(35);
  completeDeliveryIsDefined: Boolean @title : 'Complete delivery';
  confirmationTime: String(20) @title : 'Conformation time';
  createdByUser: String(12) @title : 'Created by';
  creationDate: String(30) @title : 'Creation date'; // Increased length to 16
  creationTime: String(20);
  customerGroup: String(2) @title : 'Customer group';
  deliveryBlockReason: String(2) @title : 'Block reason';
  deliveryDate: String(25) @title : 'Delivery date'; // Increased length to 16
  deliveryDocumentBySupplier: String(35) @title : 'Delivery document by supplier';
  deliveryDocumentType: String(4) @title : 'Delivery document type';
  deliveryIsInPlant: Boolean @title : 'Is plant';
  deliveryPriority: String(2);
  deliveryTime: String(20);
  deliveryVersion: String(4);
  depreciationPercentage: Decimal(5, 2);
  distrStatusByDecentralizedWrhs: String(1);
  status: String(2) @title : 'Status';
  statusDescription: String(20) @title : 'Status description';
}