using {com.sap.hanesbrand as db} from '../db/data-model';

service DocumentDeliveryService {

   entity OutboundDeliveryEvent as select from db.OutboundDeliveryEvent actions {
            action confirmShipment(documentId: String);
   }

}