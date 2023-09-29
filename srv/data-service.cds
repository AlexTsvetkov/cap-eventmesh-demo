using {com.sap.hanesbrand as db} from '../db/data-model';

service OutboundDeliveryService {

   entity OutboundDelivery as select from db.OutboundDelivery actions {
            action confirmShipment(documentId: String);
   }

}

annotate OutboundDeliveryService.OutboundDelivery with @(
    UI.SelectionFields : [
        status,
        actualDeliveryRoute
    ],
);