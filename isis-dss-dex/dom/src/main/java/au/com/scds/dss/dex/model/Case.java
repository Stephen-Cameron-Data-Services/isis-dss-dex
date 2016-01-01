package au.com.scds.dss.dex.model;

public class Case {
	/*<Case> 
	<!--Mandatory. -->
		<CaseId>CAJoeBlog</CaseId>
		 <!--Mandatory. Values allowed integers -->
		<OutletActivityId>12</OutletActivityId>
		 <!--Mandatory. Values allowed integers between 0 - 999 -->
		<TotalNumberOfUnidentifiedClients>0</TotalNumberOfUnidentifiedClients> 
		<!--Not mandatory. --> <!--If element is present in the XML, then a value must be provided. -->
		<ParentingAgreementOutcomeCode>FULL</ParentingAgreementOutcomeCode> 
		<!--Not mandatory. --> <!--If element is present in the XML, then at least 1 Client must be provided. -->
		<CaseClients>
			<CaseClient> 
			<!--Mandatory. -->
				<ClientId>CLJoeBlog</ClientId> 
				<!--Not mandatory. --> <!--If element is present in the XML, then a value must be provided. -->
				<ReferralSourceCode>CENTRELINK</ReferralSourceCode> 
				<!--Not mandatory. --> <!--If element is present in the XML, then atleast 1 ReasonForAssistance must be provided. -->
				<ReasonsForAssistance>
					<ReasonForAssistance>
					 <!--Mandatory. -->
						<ReasonForAssistanceCode>FAMILY</ReasonForAssistanceCode>
						 <!--Mandatory. Values allowed true or false in lower case -->
						<IsPrimary>true</IsPrimary>
					</ReasonForAssistance>
					<ReasonForAssistance>
					 <!--Mandatory. -->
						<ReasonForAssistanceCode>MATERIAL</ReasonForAssistanceCode> 
						<!--Mandatory. Values allowed true or false in lower case -->
						<IsPrimary>false</IsPrimary>
					</ReasonForAssistance>
				</ReasonsForAssistance>
				 <!--Not mandatory. --> <!--If element is present in the XML, then a value must be provided. -->
				<ExitReasonCode>MOVED</ExitReasonCode>
			</CaseClient>
		</CaseClients> 
		<!--Not mandatory. --> <!--If element is present in the XML, then data must be provided. -->
		<ParentingAgreementOutcome> 
		<!--Mandatory. -->
			<ParentingAgreementOutcomeCode>FULL</ParentingAgreementOutcomeCode> 
			<!-- Mandatory -->
			<DateOfParentingAgreement>
				2014-02-24</ DateOfParentingAgreement>
				 <!--Mandatory. -->
				<DidLegalPractitionerAssistWithFormalisingAgreement>true </DidLegalPractitionerAssistWithFormalisingAgreement>
				</ ParentingAgreementOutcome>
				 <!--Not mandatory. --> <!--If element is present in the XML, then data must be provided. -->
				<Section60I> 
				<!--Mandatory. -->
					<Section60ITypeCode>GENUINE</Section60ITypeCode> 
					<!--Mandatory. -->
					<DateIssued>2014-02-24</ DateIssued>
				</Section60I>
	</Case>*/
}