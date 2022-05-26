package com.kmginfotech.Gbli.conditionalData;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadEndFormNo {

	public ArrayList<String> readEndForm(String fileName)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		ArrayList<String> endFormId = new ArrayList<String>();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true); // never forget this!
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(fileName);

		// Create XPath

		XPathFactory xpathfactory = XPathFactory.newInstance();
		XPath xpath = xpathfactory.newXPath();

		XPathExpression formId = xpath.compile("//Contract_Form/Form_ID/text()");

	//	XPathExpression articleStatus = xpath.compile("//Contract_Form/Article_Status/text()");

		Object formIdResult = formId.evaluate(doc, XPathConstants.NODESET);

	//	Object articleStatusResult = articleStatus.evaluate(doc, XPathConstants.NODESET);

		NodeList nodes = (NodeList) formIdResult;

	//	NodeList formIdNodes = (NodeList) articleStatusResult;

		for (int i = 0; i < nodes.getLength(); i++) {

		//	if (!formIdNodes.item(i).getNodeValue().equalsIgnoreCase("N")) {

				String id = nodes.item(i).getNodeValue();

				endFormId.add(String.format("%35s", id).replace(' ', '0'));

//				
//				System.out.print(formIdNodes.item(i).getNodeValue());
//
//				System.out.println(nodes.item(i).getNodeValue());

		//	}
		}
		return endFormId;

	}

}
