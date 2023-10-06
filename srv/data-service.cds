using {cap.eventmesh.demo as db} from '../db/data-model';

// @path: '/odata/v4/OutboundDeliveryService'
service OutboundDeliveryService {
   entity Status           as projection on db.Status;

    event outboundMessage : {
       type: String;
       data: String;
     }

   entity OutboundDelivery as select from db.OutboundDelivery actions {
                                 action confirmShipment(documentId : String);
                              }

}