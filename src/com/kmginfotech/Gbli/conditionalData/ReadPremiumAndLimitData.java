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

public class ReadPremiumAndLimitData {

	double premium;
	String premiumAmount;
	FileInputStream file;
	DocumentBuilderFactory builderFactory;
	DocumentBuilder builder;
	Document xmlDocument;
	XPath xPath;

	public String premium(String fileName, String xpathPremium)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		file = new FileInputStream(new File(fileName));

		builderFactory = DocumentBuilderFactory.newInstance();

		builder = builderFactory.newDocumentBuilder();

		xmlDocument = builder.parse(file);

		xPath = XPathFactory.newInstance().newXPath();

		premium = Double.parseDouble(xPath.compile(xpathPremium).evaluate(xmlDocument));

		long wPrem = (long) (premium) * 100;

		if (wPrem > 0) {

			premiumAmount = String.format("%11s", wPrem).replace(' ', '0') + "+";

		} else if (wPrem < 0) {

			premiumAmount = String.format("%11s", String.valueOf(wPrem).replace("-", "")).replace(' ', '0') + "-";

		}

		else {

			premiumAmount = String.format("%12s", wPrem).replace(' ', '0');

		}

		return premiumAmount;

	}

	public String readLimit(String fileName, String xpathPremium) throws ParserConfigurationException, SAXException,
			IOException, NumberFormatException, XPathExpressionException {

		file = new FileInputStream(new File(fileName));

		builderFactory = DocumentBuilderFactory.newInstance();

		builder = builderFactory.newDocumentBuilder();

		xmlDocument = builder.parse(file);

		xPath = XPathFactory.newInstance().newXPath();

		return premiumAmount = String.format("%9s", xPath.compile(xpathPremium).evaluate(xmlDocument)).replace(' ',
				'0');

	}

	public String premiumDataWithoutXpath(String premInXml) {

		premium = Double.parseDouble(premInXml);

		long wPrem = (long) (premium) * 100;

		if (wPrem > 0) {

			premiumAmount = String.format("%11s", wPrem).replace(' ', '0') + "+";

		} else if (wPrem < 0) {

			premiumAmount = String.format("%11s", String.valueOf(wPrem).replace("-", "")).replace(' ', '0') + "-";

		}

		else {

			premiumAmount = String.format("%12s", wPrem).replace(' ', '0');

		}

		return premiumAmount;

	}

}
