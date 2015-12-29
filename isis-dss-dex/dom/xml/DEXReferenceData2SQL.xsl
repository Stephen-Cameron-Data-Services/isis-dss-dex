<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="text"/>

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="ReferenceDataItems">
		<xsl:for-each select="Item">
			<xsl:text>INSERT INTO </xsl:text>
			<xsl:value-of select="../@CodeType" />
			<xsl:text> (codeValue, codeDescription, orderNumber) VALUES (</xsl:text>
			<xsl:text>'</xsl:text>
			<xsl:value-of select="CodeValue" />
			<xsl:text>','</xsl:text>
			<xsl:value-of select="CodeDescription" />
			<xsl:text>',</xsl:text>
			<xsl:value-of select="OrderNumber" />
			<xsl:text>);
			</xsl:text>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="AssessmentReferenceDataItems">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="AssessmentReferenceDataItem">
		<xsl:text>INSERT INTO Assessment (scoreType, applicableFor) VALUES (</xsl:text>
		<xsl:text>'</xsl:text>
		<xsl:value-of select="ScoreType" />
		<xsl:text>','</xsl:text>
		<xsl:value-of select="ApplicableFor" />
		<xsl:text>');
	    </xsl:text>
		<xsl:for-each select="Domains/Domain">
			<xsl:call-template name="Domain">
				<xsl:with-param name="score-type">
					<xsl:value-of select="../../ScoreType" />
				</xsl:with-param>
			</xsl:call-template>
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="Domain">
		<xsl:param name="score-type" />
		<xsl:text>INSERT INTO AssessmentDomain (assessment, domainCode, description) VALUES (</xsl:text>
		<xsl:text>'</xsl:text>
		<xsl:value-of select="$score-type" />
		<xsl:text>','</xsl:text>
		<xsl:value-of select="DomainCode" />
		<xsl:text>','</xsl:text>
		<xsl:value-of select="DomainDescription" />
		<xsl:text>');
	    </xsl:text>
		<xsl:for-each select="Scores/Score">
			<xsl:call-template name="Score">
				<xsl:with-param name="domain">
					<xsl:value-of select="../../DomainCode" />
				</xsl:with-param>
			</xsl:call-template>
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="Score">
		<xsl:param name="domain" />
		<xsl:text>INSERT INTO AssessmentDomainScore (domain, code, description) VALUES (</xsl:text>
		<xsl:text>'</xsl:text>
		<xsl:value-of select="$domain" />
		<xsl:text>','</xsl:text>
		<xsl:value-of select="ScoreCode" />
		<xsl:text>','</xsl:text>
		<xsl:value-of select="Description" />
		<xsl:text>');
	    </xsl:text>
	</xsl:template>

</xsl:stylesheet>