using { cap.eventmesh.demo as schema } from '../../db/data-model';

annotate schema.OutboundDelivery with {
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