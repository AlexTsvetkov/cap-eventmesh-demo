package com.sap.hanesbrand.dao.impl;

import cds.gen.outbounddeliveryservice.OutboundDelivery;
import cds.gen.outbounddeliveryservice.OutboundDelivery_;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.Upsert;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.ql.cqn.CqnUpdate;
import com.sap.cds.ql.cqn.CqnUpsert;
import com.sap.cds.services.persistence.PersistenceService;
import com.sap.hanesbrand.dao.OutboundDeliveryDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OutBoundDeliveryDaoImpl implements OutboundDeliveryDao {

    private final PersistenceService persistenceService;
    private static final String PKMS = "1";
    private static final String PKMS_DESCRIPTION = "Sent to WMS";
    private final String SOURCE_TYPE = OutboundDelivery_.CDS_NAME;

    @Override
    public OutboundDelivery selectOutboundDeliveryById(String id) {
        CqnSelect selectQuery = Select.from(SOURCE_TYPE)
                .where(i -> i.get(OutboundDelivery.DELIVERY_DOCUMENT).eq(id));
        return persistenceService.run(selectQuery).single(OutboundDelivery.class);

    }

    @Override
    public void saveOutboundDelivery(OutboundDelivery outboundDeliveryEvent) {
        log.info("OutBoundDeliveryDaoImpl: saveOutboundDelivery {} ", outboundDeliveryEvent);
        outboundDeliveryEvent.setStatus(PKMS);
        outboundDeliveryEvent.setSendToWMSDate(LocalDate.now());
        outboundDeliveryEvent.setCriticality("1");
        outboundDeliveryEvent.setStatusDescription(PKMS_DESCRIPTION);
        CqnUpsert upsert = Upsert.into(SOURCE_TYPE).entry(outboundDeliveryEvent);
        persistenceService.run(upsert);

    }

    @Override
    public void updateConfirmShipmentStatus(String deliveryId, String status, String statusDescription) {
        log.info("OutBoundDeliveryDaoImpl: updateStatus for {}", deliveryId);
        OutboundDelivery outboundDelivery = OutboundDelivery.create();
        outboundDelivery.setStatus(status);
        outboundDelivery.setStatusDescription(statusDescription);
        outboundDelivery.setShipmentConfirmedDate(LocalDate.now());
        CqnUpdate update = Update.entity(SOURCE_TYPE).data(outboundDelivery).byId(deliveryId);
        persistenceService.run(update);
    }
}
