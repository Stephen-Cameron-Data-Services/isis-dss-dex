<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:tns="http://scds.com.au/chats/fixture/jaxb/generated">
	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="/">
		<tns:suburbs>
			<xsl:for-each select="xml/ul/li">
				<xsl:variable name="postcode" select="normalize-space(a)">
				</xsl:variable>
				<xsl:for-each select="ul/li">
					<tns:suburb>
						<tns:name><xsl:value-of select="." /></tns:name>
						<tns:postcode><xsl:value-of select="$postcode" /></tns:postcode>
					</tns:suburb>
				</xsl:for-each>
			</xsl:for-each>
		</tns:suburbs>
	</xsl:template>
</xsl:stylesheet>