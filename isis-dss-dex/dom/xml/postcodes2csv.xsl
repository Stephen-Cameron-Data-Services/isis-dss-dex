<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<xsl:for-each select="xml/ul/li">
			<xsl:variable name="postcode" select="a">
			</xsl:variable>
			<xsl:for-each select="ul/li">
				<xsl:value-of select="." />,<xsl:value-of select="$postcode" />
			</xsl:for-each>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>