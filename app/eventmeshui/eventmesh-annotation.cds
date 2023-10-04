using {OutboundDeliveryService} from '../../srv/data-service';

annotate OutboundDeliveryService.OutboundDelivery with {
    statusDescription @(Common : {
        ValueListWithFixedValues: true,
        Label : 'Status',
        ValueList : {
            $Type : 'Common.ValueListType',
            CollectionPath : 'Status',
            SearchSupported: true,
            Parameters: [
                {
                    $Type : 'Common.ValueListParameterOut',
                    LocalDataProperty : statusDescription,
                    ValueListProperty : 'status',
                },
            ]
        },
    })
};

annotate OutboundDeliveryService.OutboundDelivery with {
    criticality_code @UI : {
        Hidden: true
    };
    criticality_code @Core : { 
        Computed: true
     }
};

annotate OutboundDeliveryService.OutboundDelivery with @Capabilities: {
    FilterRestrictions: {
        $Type                       : 'Capabilities.FilterRestrictionsType',
        FilterExpressionRestrictions: [{
            Property          : statusDescription,
            AllowedExpressions: 'MultiValue'
        }]
    },
    DeleteRestrictions: {Deletable: false},
};

annotate OutboundDeliveryService.OutboundDelivery with @UI: {
    LineItem       : {
        $value            : [
            {
                $Type            : 'UI.DataField',
                Label            : 'Delivery document',
                Value            : DeliveryDocument,
                ![@UI.Importance]: #High,
            },
            {
                $Type            : 'UI.DataField',
                Label            : 'Status',
                Value            : statusDescription,
                ![@UI.Importance]: #High,
            },
            {
                $Type            : 'UI.DataField',
                Label            : 'Creating date',
                Value            : creationDate,
                ![@UI.Importance]: #High,
            },
            {
                $Type            : 'UI.DataField',
                Label            : 'Delivery date',
                Value            : deliveryDate,
                ![@UI.Importance]: #High,
            },
            {
                $Type            : 'UI.DataField',
                Label            : 'Sent to WMS date',
                Value            : sendToWMSDate,
                ![@UI.Importance]: #High,
            },
            {
                $Type            : 'UI.DataField',
                Label            : 'Shipment confirmed date',
                Value            : shipmentConfirmedDate,
                ![@UI.Importance]: #High,
            },
            {
                $Type            : 'UI.DataField',
                Label            : 'Shipment confirmed date',
                Value            : shipmentConfirmedDate,
                ![@UI.Importance]: #High,
            },
            {
                $Type            : 'UI.DataField',
                Label            : 'Status code',
                Value            : status,
                ![@UI.Importance]: #Low,
                ![@UI.Hidden]: true
            }
        ],
        ![@UI.Criticality]: criticality_code
    },

    HeaderInfo     : {
        $Type         : 'UI.HeaderInfoType',
        TypeName      : 'Outbound Delivery',
        TypeNamePlural: 'Outbound Deliveries',
    },

    SelectionFields: [
        DeliveryDocument,
        statusDescription,
        sendToWMSDate,
        shipmentConfirmedDate
    ],
};

annotate OutboundDeliveryService.OutboundDelivery with {
    DeliveryDocument @Common : { 
        Label : 'Delivery document',
     }
};
annotate OutboundDeliveryService.OutboundDelivery with {
    sendToWMSDate @Common : { 
        Label : 'Sent to WMS date',
     }
};
annotate OutboundDeliveryService.OutboundDelivery with {
    shipmentConfirmedDate @Common : { 
        Label : 'Shipment confirmation date',
     }
};

