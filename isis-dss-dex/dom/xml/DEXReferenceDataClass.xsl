<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="text" />

	<xsl:template match="/">
		public class ReferenceData{
		<xsl:for-each select="DEXReferenceData/ReferenceDataItems">
			public List&lt;<xsl:value-of select="@CodeType" />&gt; get<xsl:value-of select="@CodeType" />()){
				return container.allMatches(new QueryDefault(<xsl:value-of select="@CodeType" />.class, "all"));
			}
		</xsl:for-each>
		}
	</xsl:template>

	<!-- xsl:template match="/ | @* | node()">
		<xsl:apply-templates />
	</xsl:template-->

</xsl:stylesheet>