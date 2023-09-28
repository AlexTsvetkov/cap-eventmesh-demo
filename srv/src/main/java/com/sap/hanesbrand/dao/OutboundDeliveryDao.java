package com.sap.hanesbrand.dao;

import cds.gen.documentdeliveryservice.OutboundDeliveryEvent;

public interface OutboundDeliveryDao {

    OutboundDeliveryEvent selectOutboundDeliveryById(String id);

    void saveOutboundDelivery(OutboundDeliveryEvent outboundDeliveryEvent);

    void updateStatus(String deliveryId, String status);

}
