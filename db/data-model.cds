namespace com.sap.hanesbrand;

using {managed, extensible} from '@sap/cds/common';

entity OutboundDelivery {
  key DeliveryDocument: String(10);
  actualDeliveryRoute: String(6);
  shippinglocationtimezone: String(6);
  receivinglocationtimezone: String(6);
  billingDocumentDate: String(25); // Increased length to 14
  billOfLading: String(35);
  completeDeliveryIsDefined: Boolean;
  confirmationTime: String(20);
  createdByUser: String(12);
  creationDate: String(30); // Increased length to 16
  creationTime: String(20);
  customerGroup: String(2);
  deliveryBlockReason: String(2);
  deliveryDate: String(25); // Increased length to 16
  deliveryDocumentBySupplier: String(35);
  deliveryDocumentType: String(4);
  deliveryIsInPlant: Boolean;
  deliveryPriority: String(2);
  deliveryTime: String(20);
  deliveryVersion: String(4);
  depreciationPercentage: Decimal(5, 2);
  distrStatusByDecentralizedWrhs: String(1);
  status: String(2);
  statusDescription: String(20);
}