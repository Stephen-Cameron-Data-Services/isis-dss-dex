<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<xsl:apply-templates select="DEXFileUpload/Cases/Case" />
		<xsl:apply-templates select="DEXFileUpload/Sessions/Session" />

	</xsl:template>
	<xsl:template match="Case">
		<xsl:if test="string-length( ./CaseId) &gt; 30">
			Case with CaseId
			<xsl:value-of select="./CaseId" />
			too long!
		</xsl:if>
		<xsl:for-each select="CaseClients/CaseClient/ClientId">
			<xsl:if test="not(/DEXFileUpload/Clients/Client/ClientId = .)">
				CaseClient with ClientId
				<xsl:value-of select="." />
				Not found!
			</xsl:if>
		</xsl:for-each>
	</xsl:template>
	<xsl:template match="Session">
		<xsl:if test="not(/DEXFileUpload/Cases/Case/CaseId = ./CaseId)">
			Session with CaseId
			<xsl:value-of select="./CaseId" />
			Not found!
		</xsl:if>
		<xsl:for-each select="SessionClients/SessionClient/ClientId">
			<xsl:if test="not(/DEXFileUpload/Clients/Client/ClientId = .)">
				SessionClient with ClientId
				<xsl:value-of select="." />
				Not found!
			</xsl:if>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>