package com.kmginfotech.Gbli.endorsement;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.kmginfotech.Gbli.conditionalData.NumericStateCode;

public class Duxplm {

	String contractNum;
	FileInputStream inputFile;
	String fileName;

	NumericStateCode objNumericStateCode = new NumericStateCode();

	public void readDataForDuxplm() {

		File f = new File("./PolicyXMLs/END"); // current directory

		File[] files = f.listFiles();

		for (File file : files) {

			try {

				fileName = file.getPath();

				inputFile = new FileInputStream(new File(fileName));

				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document xmlDocument = dBuilder.parse(file);

				XPath xPath = XPathFactory.newInstance().newXPath();

				xmlDocument.getDocumentElement().normalize();

				NodeList contractLocationList = xmlDocument.getElementsByTagName("Contract_Location");

				if (contractLocationList.getLength() != 0) {

					for (int contractLocationTemp = 0; contractLocationTemp < contractLocationList
							.getLength(); contractLocationTemp++) {

						Node contractLocationNode = contractLocationList.item(contractLocationTemp);

						if (contractLocationNode.getNodeType() == Node.ELEMENT_NODE) {

							Element contractLocationElement = (Element) contractLocationNode;

							NodeList subLocationList = contractLocationElement.getElementsByTagName("Sub_Location");

							if (subLocationList.getLength() != 0) {

								for (int subLocationListTemp = 0; subLocationListTemp < subLocationList
										.getLength(); subLocationListTemp++) {

									Node clNode = subLocationList.item(subLocationListTemp);

									if (clNode.getNodeType() == Node.ELEMENT_NODE) {

										Element subLocationListElement = (Element) clNode;

										contractNum = xPath.compile("//Contract/Contract_Num").evaluate(xmlDocument);

										System.out.print("5 " + "LM " + contractNum);

										System.out.print(" " + xPath.compile("//Contract/Eff_Date")
												.evaluate(xmlDocument).replace("-", "").substring(0, 8));

										System.out.print(" "
												+ xPath.compile("//Endorsement/Endorse_Eff_Date").evaluate(xmlDocument)
														.replace("-", "").substring(0, 8)
												+ " " + fileName.substring(fileName.indexOf("_") + 1,
														fileName.indexOf("_") + 4)
												+ " EN ");

										String stateCode = objNumericStateCode.getStateCode(
												xPath.compile("//Contract/Contract_State").evaluate(xmlDocument));

										System.out.print(" " + stateCode + " 002 ");

										System.out
												.print(String
														.format("%3s",
																contractLocationElement.getElementsByTagName("Loc_Num")
																		.item(0).getTextContent())
														.replace(' ', '0') + " ");

										System.out.print(String
												.format("%3s", subLocationListElement
														.getElementsByTagName("Sub_Loc_Num").item(0).getTextContent())
												.replace(' ', '0') + " ");

										System.out.print(String.format("%-30s", contractLocationElement
												.getElementsByTagName("Address_Line_1").item(0).getTextContent())
												.substring(0, 30) + " ");

										String addressLine2 = (contractLocationElement
												.getElementsByTagName("Address_Line_2").getLength() == 0)
														? xPath.compile("//Consumer/Entity_Name").evaluate(xmlDocument)
														: contractLocationElement.getElementsByTagName("Address_Line_2")
																.item(0).getTextContent();

										System.out.print(
												String.format("%-30s", addressLine2 + " ").substring(0, 30) + "  ");

										System.out
												.print(String
														.format("%-20s", contractLocationElement
																.getElementsByTagName("City").item(0).getTextContent())
														+ "   ");

										System.out
												.print(String
														.format("%-2s", contractLocationElement
																.getElementsByTagName("State").item(0).getTextContent())
														+ "   ");

										String zip = (contractLocationElement.getElementsByTagName("Zip")
												.getLength() == 0) ? ""
														: contractLocationElement.getElementsByTagName("Zip").item(0)
																.getTextContent();

										if (zip.length() != 0) {
											System.out.print(String.format("%5s", zip.substring(zip.length() - 5)));
										}

										else {
											System.out.print("     ");
										}

										System.out.print(" "
												+ xPath.compile("//Contract/Operations_Desc").evaluate(xmlDocument));

										System.out.println("\t\t\t");

									}

								}

							}
						}
					}
				}
			} catch (Exception e) {
			}
		}
	}

}
