package com.kmginfotech.Gbli.conditionalData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ReadLegalEntity {

	String legalEntityInTheXml;
	String legalEntity;

	public String readLegalEntity(String fileName)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		FileInputStream file = new FileInputStream(new File(fileName));

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = builderFactory.newDocumentBuilder();

		Document xmlDocument = builder.parse(file);

		XPath xPath = XPathFactory.newInstance().newXPath();

		legalEntityInTheXml = xPath.compile("//Consumer/Legal_Entity").evaluate(xmlDocument);

		if (legalEntityInTheXml.equalsIgnoreCase("LC") || legalEntityInTheXml.equalsIgnoreCase("LP"))

			legalEntity = "05";

		else if (legalEntityInTheXml.equalsIgnoreCase("CP") || legalEntityInTheXml.equalsIgnoreCase("OR"))

			legalEntity = "03";

		else if (legalEntityInTheXml.equalsIgnoreCase("IN"))

			legalEntity = "01";

		else if (legalEntityInTheXml.equalsIgnoreCase("OT"))

			legalEntity = "99";

		else

			legalEntity = "77";

		return legalEntity;
	}

}
