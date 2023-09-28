# cap-eventmesh-demo
Demo showcases the way how to integrate CAP Cloud Native App with EventMesh Message Broker

# Requirements
## Demo App Requirements
* Create CAP application
* cds add samples
* Add csd entity OutboundDeliveryEvent
  * It contains custom field status, with values
  * Sent to PKMS
  * Shipment Confirmed
  * Metadata
  https://sl5.leverx.local:44300/sap/opu/odata/sap/API_OUTBOUND_DELIVERY_SRV;v=0002/$metadata
  <EntityType Name="A_OutbDeliveryHeaderType"
* Expose OData v4 service for CRUD operations for OutboundDeliveryEvent - DeliveryService
* Subscribe to EventMesh queue, in listener create new OutboundDeliveryEvent
* Use destination to S4/Hana to get entity fields by id and save to Hana Cloud
* Create bounded action ‘confirmShipement’ for DeliveryService/OutboundDeliveryEvent(‘id’)/‘confirmShipement’
* Action changes the status to Shipment Confirmed
  * This action can be triggered from EventMesh webhook or manually from Postman



# Prerequisites
* Java 17
* Node.js 18 or higher
  * brew install nvm
  * nvm install 18
  * npm add -g @sap/cds-dk


# Build CDS
1. cds deploy --to sqlite

# Build MTA archive
* mbt build


# Run locally
* cds watch


# Deploy
* cf login -a https://api.cf.eu10-004.hana.ondemand.com -u {email} -p {password}
* cf push

# CF Commands
* cf logs APP_NAME --recent
