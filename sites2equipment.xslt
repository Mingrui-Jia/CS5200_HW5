<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
	<html>
	<head>
	</head>
	<body>
		<ol>
		<xsl:for-each select="siteList/site/tower/equipment">
					<xsl:choose>
				<xsl:when test="@price &gt; 3">
					<li>
						<xsl:value-of select="@name"/>
						<xsl:text>
						    </xsl:text>
						Site/Tower: 
						<!-- 这里蒙对了居然！../表示上两层，即site -->
						<xsl:value-of select="../../@name"/>
						<xsl:text>, </xsl:text>
						<xsl:value-of select="../@name"/>
					</li>
				</xsl:when>
				<xsl:otherwise>
					<li>
						<xsl:value-of select="@name"/>
						<xsl:text>
						    </xsl:text>
						Site/Tower: 
						<xsl:value-of select="../@name"/>
						<xsl:text>, </xsl:text>
						<xsl:value-of select="../@name"/>
					</li>
				</xsl:otherwise>
			</xsl:choose>
<!-- 					<li>
						<xsl:value-of select="../@id"/>
						<xsl:text>
						</xsl:text>
						<xsl:value-of select="../@name"/>
					</li> -->
		</xsl:for-each>
		</ol>
	</body>
	</html>
	</xsl:template>
</xsl:stylesheet>