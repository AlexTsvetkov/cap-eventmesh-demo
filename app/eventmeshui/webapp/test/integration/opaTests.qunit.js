sap.ui.require(
    [
        'sap/fe/test/JourneyRunner',
        'eventmeshui/test/integration/FirstJourney',
		'eventmeshui/test/integration/pages/OutboundDeliveryList',
		'eventmeshui/test/integration/pages/OutboundDeliveryObjectPage'
    ],
    function(JourneyRunner, opaJourney, OutboundDeliveryList, OutboundDeliveryObjectPage) {
        'use strict';
        var JourneyRunner = new JourneyRunner({
            // start index.html in web folder
            launchUrl: sap.ui.require.toUrl('eventmeshui') + '/index.html'
        });

       
        JourneyRunner.run(
            {
                pages: { 
					onTheOutboundDeliveryList: OutboundDeliveryList,
					onTheOutboundDeliveryObjectPage: OutboundDeliveryObjectPage
                }
            },
            opaJourney.run
        );
    }
);