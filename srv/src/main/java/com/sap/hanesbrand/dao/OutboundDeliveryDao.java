package com.sap.hanesbrand.dao;

import cds.gen.outbounddeliveryservice.OutboundDelivery;

public interface OutboundDeliveryDao {

    OutboundDelivery selectOutboundDeliveryById(String id);

    void saveOutboundDelivery(OutboundDelivery outboundDelivery);

    void updateStatus(String deliveryId, String status, String statusDescription);

}
