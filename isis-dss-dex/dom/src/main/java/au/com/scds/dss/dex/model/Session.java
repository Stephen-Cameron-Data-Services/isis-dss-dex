package au.com.scds.dss.dex.model;

public class Session {
 /*   <!--Mandatory. -->
	<SessionId>SSJoeBlog</SessionId> 
	<!--Mandatory. -->
	<CaseId>CAJoeBlog</CaseId> 
	<!--Mandatory and should be valid datetime -->
	<SessionDate>2014-07-30</SessionDate> 
	<!--Mandatory. Values allowed intergers -->
	<ServiceTypeId>5</ServiceTypeId> 
	<!--Mandatory. Values allowed integers between 0 - 999 -->
	<TotalNumberOfUnidentifiedClients>0</TotalNumberOfUnidentifiedClients> 
	<!-- If provided. Values allowed decimals -->
	<FeesCharged>1.00</FeesCharged> 
	<!--Not mandatory. --> 
	<!--If element is present in the XML, then a value must be provided. -->
	<MoneyBusinessCommunityEducationWorkshopCode>WRK01</MoneyBusinessCommunityEducationWorkshopCode> 
	<!--Not mandatory. --> 
	<!--If element is present in the XML, then a value of true or false in lower case must be provided -->
	<InterpreterPresent>true</InterpreterPresent>
	<SessionClients>
		<SessionClient>
			<ClientId>CLJoeBlog</ClientId>
			<ParticipationCode>CLIENT</ParticipationCode> 
			<!--Not mandatory. --> 
			<!--If element is present in the XML, then atleast 1 Referral must be provided. -->
			<ClientReferralOutWithPurpose>
				<Referral> 
				<!--Mandatory. -->
					<TypeCode>EXTERNAL</TypeCode> 
					<!--Mandatory. -->
					<PurposeCodes> 
					<!-- 1 or more Purpose must be provided. -->
						<PurposeCode>HOUSING</PurposeCode>
						<PurposeCode>PHYSICAL</PurposeCode>
					</PurposeCodes>
				</Referral>
				<Referral> 
				<!--Mandatory. -->
					<TypeCode>INTERNAL</TypeCode> 
					<!--Mandatory. -->
					<PurposeCodes> 
					<!-- 1 or more Purpose must be provided. -->
						<PurposeCode>PERSONAL</PurposeCode>
						<PurposeCode>PHYSICAL</PurposeCode>
					</PurposeCodes>
				</Referral>
			</ClientReferralOutWithPurpose>
		</SessionClient>
	</SessionClients> 
	<!--Not mandatory. --> 
	<!--If element is present in the XML, Values allowed Int -->
	<TimeMinutes>10</TimeMinutes> 
	<!--Not mandatory. --> <!--If element is present in the XML, Values allowed Int -->
	<TotalCost>10</TotalCost> 
	<!--Not mandatory. --> <!--If element is present in the XML, Values allowed Int -->
	<Quantity>10</Quantity> 
	<!--Not mandatory. --> <!--If element is present in the XML, then atleast 1 ExtraItem must be provided. -->
	<ExtraItems> 
		<!-- 1 or more ExtraItem must be provided. -->
		<ExtraItem>
			BATHROOM
			<ExtraItem>
	</ExtraItems>
	*/
}
