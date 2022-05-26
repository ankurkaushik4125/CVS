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

public class ConstructionType {

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

			NodeList cdglClassRating = xmlDocument.getElementsByTagName("CDPC_Underwriting");

			if (cdglClassRating.getLength() != 0) {

				for (int contractLocationTemp = 0; contractLocationTemp < cdglClassRating
						.getLength(); contractLocationTemp++) {

					Node contractLocationNode = cdglClassRating.item(contractLocationTemp);

					if (contractLocationNode.getNodeType() == Node.ELEMENT_NODE) {

						Element contractLocationElement = (Element) contractLocationNode;
						
						System.out.print(xPath.compile("//Contract/Contract_Num").evaluate(xmlDocument));
						
						
						String constructionType = (contractLocationElement.getElementsByTagName("Construction_Type").getLength()==0)?
								" Construction_Type Not Available ":(contractLocationElement.getElementsByTagName("Construction_Type").item(0).getTextContent());
												
						System.out.println(" " +constructionType);

					}
				}

			}
		}
	}

}
