package com.kmginfotech.Gbli.Validation;

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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CoinsPct {

	static FileInputStream inputFile;
	static String fileName;

	public static void main(String[] args)
			throws ParserConfigurationException, XPathExpressionException, SAXException, IOException {

		File f = new File("./PolicyXMLs/NB"); // current directory

		File[] files = f.listFiles();

		for (File file : files) {

			fileName = file.getPath();

			inputFile = new FileInputStream(new File(fileName));

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document xmlDocument = dBuilder.parse(file);

			XPath xPath = XPathFactory.newInstance().newXPath();

			xmlDocument.getDocumentElement().normalize();

			NodeList contractLocationList = xmlDocument.getElementsByTagName("CDPC_Class_Rating");

			if (contractLocationList.getLength() != 0) {

				for (int contractLocationTemp = 0; contractLocationTemp < contractLocationList
						.getLength(); contractLocationTemp++) {

					Node contractLocationNode = contractLocationList.item(contractLocationTemp);

					if (contractLocationNode.getNodeType() == Node.ELEMENT_NODE) {

						Element contractLocationElement = (Element) contractLocationNode;

						NodeList subLocationList = contractLocationElement.getElementsByTagName("Product");

						if (subLocationList.getLength() != 0) {

							for (int subLocationListTemp = 0; subLocationListTemp < subLocationList
									.getLength(); subLocationListTemp++) {

								Node clNode = subLocationList.item(subLocationListTemp);

								if (clNode.getNodeType() == Node.ELEMENT_NODE) {

									Element subLocationListElement = (Element) clNode;

									System.out.print(xPath.compile("//Contract/Contract_Num").evaluate(xmlDocument));
									
									String coinsPct = (subLocationListElement.getElementsByTagName("Product_Code")
											.getLength() == 0) ? "Coins_Pct Not Available"
													: (contractLocationElement.getElementsByTagName("Product_Code").item(0)
															.getTextContent());

									System.out.println(" " +coinsPct);

								}
							}
						}
					}
				}
			}

		}
	}
}