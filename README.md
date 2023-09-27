# cap-eventmesh-demo
Demo integration of CAP and EventMesh

# Requirements

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
