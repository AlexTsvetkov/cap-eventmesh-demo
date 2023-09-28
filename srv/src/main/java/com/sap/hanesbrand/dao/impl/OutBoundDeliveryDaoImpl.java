package com.sap.hanesbrand.dao.impl;

import cds.gen.documentdeliveryservice.OutboundDeliveryEvent;
import cds.gen.documentdeliveryservice.OutboundDeliveryEvent_;
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

@Slf4j
@Repository
@RequiredArgsConstructor
public class OutBoundDeliveryDaoImpl implements OutboundDeliveryDao {

    private final PersistenceService persistenceService;
    private static final String PKMS = "PKMS";
    private final String SOURCE_TYPE = OutboundDeliveryEvent_.CDS_NAME;

    @Override
    public OutboundDeliveryEvent selectOutboundDeliveryById(String id) {
        CqnSelect selectQuery = Select.from(SOURCE_TYPE)
                .where(i -> i.get(OutboundDeliveryEvent.DELIVERY_DOCUMENT).eq(id));
        return persistenceService.run(selectQuery).single(OutboundDeliveryEvent.class);

    }

    @Override
    public void saveOutboundDelivery(OutboundDeliveryEvent outboundDeliveryEvent) {
        log.info("OutBoundDeliveryDaoImpl: saveOutboundDelivery {} ", outboundDeliveryEvent);
        outboundDeliveryEvent.setStatus(PKMS);
        CqnUpsert upsert = Upsert.into(SOURCE_TYPE).entry(outboundDeliveryEvent);
        persistenceService.run(upsert);

    }

    public void updateStatus(String deliveryId, String status) {
        log.info("OutBoundDeliveryDaoImpl: updateStatus for {}", deliveryId);
        OutboundDeliveryEvent outboundDeliveryEvent = OutboundDeliveryEvent.create();
        outboundDeliveryEvent.setStatus(status);
        CqnUpdate update = Update.entity(SOURCE_TYPE).data(outboundDeliveryEvent).byId(deliveryId);
        persistenceService.run(update);
    }
}
