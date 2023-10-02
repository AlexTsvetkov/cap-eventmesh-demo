using {com.sap.hanesbrand as db} from '../db/data-model';

// @path: '/odata/v4/OutboundDeliveryService'
service OutboundDeliveryService {
    
   entity OutboundDelivery as select from db.OutboundDelivery actions {
            action confirmShipment(documentId: String);
   }

}

annotate OutboundDeliveryService.OutboundDelivery with @Capabilities : { 
   FilterRestrictions : {
       $Type : 'Capabilities.FilterRestrictionsType',
       FilterExpressionRestrictions: [
         {
            Property: sendToWMSDate,
            AllowedExpressions: 'SingleValue'
         }
       ]
   },
 };


