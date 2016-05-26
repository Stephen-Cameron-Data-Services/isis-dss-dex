<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="text" />
	<xsl:template match="/">
		<xsl:apply-templates select="DEXFileUpload/Cases/Case" />
		<xsl:apply-templates select="DEXFileUpload/Sessions/Session" />
		<xsl:call-template name="ClientSessionTotals" />
		<xsl:call-template name="CaseSessionTotals" />
	</xsl:template>

	<xsl:template match="Case">
		<xsl:if test="string-length( ./CaseId) &gt; 30">
			<xsl:text>Case with CaseId</xsl:text>
			<xsl:value-of select="./CaseId" />
			<xsl:text>too long!
			</xsl:text>
		</xsl:if>
		<xsl:for-each select="CaseClients/CaseClient/ClientId">
			<xsl:if test="not(/DEXFileUpload/Clients/Client/ClientId = .)">
				<xsl:text>CaseClient with ClientId</xsl:text>
				<xsl:value-of select="." />
				<xsl:text>Not found!
				</xsl:text>
			</xsl:if>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="Session">
		<xsl:if test="not(/DEXFileUpload/Cases/Case/CaseId = ./CaseId)">
			<xsl:text>Session with CaseId</xsl:text>
			<xsl:value-of select="./CaseId" />
			<xsl:text>Not found!
			</xsl:text>
		</xsl:if>
		<xsl:for-each select="SessionClients/SessionClient/ClientId">
			<xsl:if test="not(/DEXFileUpload/Clients/Client/ClientId = .)">
				<xsl:text>SessionClient with ClientId</xsl:text>
				<xsl:value-of select="." />
				<xsl:text>Not found!
				</xsl:text>
			</xsl:if>
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="ClientSessionTotals">
		<xsl:text>Client Sessions Total Time (Minutes)
</xsl:text>
		<xsl:for-each select="DEXFileUpload/Clients/Client/ClientId">
			<xsl:variable name="clientId">
				<xsl:value-of select="." />
			</xsl:variable>
			<xsl:value-of select="$clientId" />
			<xsl:text>,</xsl:text>
			<xsl:value-of
				select="sum(/DEXFileUpload/Sessions/Session[SessionClients/SessionClient/ClientId=$clientId]/TimeMinutes)" />
			<xsl:text>
</xsl:text>
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="CaseSessionTotals">
		<xsl:text>Case Sessions Total Time (Minutes)
</xsl:text>
		<xsl:for-each select="DEXFileUpload/Cases/Case/CaseId">
			<xsl:variable name="caseId">
				<xsl:value-of select="." />
			</xsl:variable>
			<xsl:value-of select="$caseId" />
			<xsl:text>,</xsl:text>
			<xsl:value-of
				select="sum(/DEXFileUpload/Sessions/Session[CaseId=$caseId]/TimeMinutes)" />
			<xsl:text>
</xsl:text>
		</xsl:for-each>
	</xsl:template>

</xsl:stylesheet>