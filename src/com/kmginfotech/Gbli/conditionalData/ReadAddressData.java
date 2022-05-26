package com.kmginfotech.Gbli.conditionalData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ReadAddressData {

	public ArrayList<String> readAddress(String fileName, String parentNode)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		String[][] addressData = new String[2][6];

		ArrayList<String> addressDataWithdfltAddressIndY = new ArrayList<String>();

		String dfltAddressInd = null;

		FileInputStream file = new FileInputStream(new File(fileName));

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = builderFactory.newDocumentBuilder();

		Document xmlDocument = builder.parse(file);

		XPath xPath = XPathFactory.newInstance().newXPath();

		addressData[0][0] = xPath.compile("//" + parentNode + "/Addresses/Address[1]/Address_Line_1")
				.evaluate(xmlDocument);

		addressData[0][1] = xPath.compile("//" + parentNode + "/Addresses/Address[1]/City").evaluate(xmlDocument);

		addressData[0][2] = xPath.compile("//" + parentNode + "/Addresses/Address[1]/State").evaluate(xmlDocument);

		addressData[0][3] = xPath.compile("//" + parentNode + "/Addresses/Address[1]/Zip").evaluate(xmlDocument);

		dfltAddressInd = xPath.compile("//" + parentNode + "/Addresses/Address[1]/Dflt_Address_Ind")
				.evaluate(xmlDocument);

		addressData[0][4] = (dfltAddressInd.equalsIgnoreCase("")) ? "N" : dfltAddressInd;

		addressData[1][0] = xPath.compile("//" + parentNode + "/Addresses/Address[2]/Address_Line_1")
				.evaluate(xmlDocument);

		addressData[1][1] = xPath.compile("//" + parentNode + "/Addresses/Address[2]/City").evaluate(xmlDocument);

		addressData[1][2] = xPath.compile("//" + parentNode + "/Addresses/Address[2]/State").evaluate(xmlDocument);

		addressData[1][3] = xPath.compile("//" + parentNode + "/Addresses/Address[2]/Zip").evaluate(xmlDocument);

		addressData[1][4] = xPath.compile("//" + parentNode + "/Addresses/Address[2]/Dflt_Address_Ind")
				.evaluate(xmlDocument);

		for (int i = 0; i < 2; i++) {

			for (int j = 4; j >= 0; j--) {

				if (addressData[i][j].equalsIgnoreCase("Y")) {

					addressDataWithdfltAddressIndY.add(addressData[i][--j]);

					addressDataWithdfltAddressIndY.add(addressData[i][--j]);

					addressDataWithdfltAddressIndY.add(addressData[i][--j]);

					addressDataWithdfltAddressIndY.add(addressData[i][--j]);

					break;

				}

			}

		}

		return addressDataWithdfltAddressIndY;

	}

}
