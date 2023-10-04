using { cap.eventmesh.demo as schema } from '../../db/data-model';


annotate schema.OutboundDelivery with {
    DeliveryDocument @Common : { 
        Label : 'Delivery document',
     }
};
annotate schema.OutboundDelivery with {
    sendToWMSDate @Common : { 
        Label : 'Sent to WMS date',
     }
};
annotate schema.OutboundDelivery with {
    shipmentConfirmedDate @Common : { 
        Label : 'Shipment confirmation date',
     }
};
