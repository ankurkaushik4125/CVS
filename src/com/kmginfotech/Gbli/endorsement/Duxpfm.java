package com.kmginfotech.Gbli.endorsement;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Duxpfm {

	ArrayList<String> address;
	FileInputStream inputFile;
	String fileName;

	public void readCmpMortgagee() {

		File f = new File("./PolicyXMLs/END"); // current directory

		File[] files = f.listFiles();

		for (File file : files) {

			int mgNumber = 1;
			try {

				fileName = file.getPath();

				inputFile = new FileInputStream(new File(fileName));

				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document xmlDocument = dBuilder.parse(file);

				XPath xPath = XPathFactory.newInstance().newXPath();

				xmlDocument.getDocumentElement().normalize();

				NodeList cdgllList = xmlDocument.getElementsByTagName("CDPC_Class_Rating");

				if (cdgllList.getLength() != 0) {

					for (int cdgltemp = 0; cdgltemp < cdgllList.getLength(); cdgltemp++) {

						Node glplNode = cdgllList.item(cdgltemp);

						if (glplNode.getNodeType() == Node.ELEMENT_NODE) {

							Element cdglElement = (Element) glplNode;

							NodeList interestlList = cdglElement.getElementsByTagName("Interest");

							String[][] natureOfInterest = new String[interestlList.getLength()][6];

							if (interestlList.getLength() != 0) {

								for (int cLtemp = 0; cLtemp < interestlList.getLength(); cLtemp++) {

									int i = 0;

									Node clNode = interestlList.item(cLtemp);

									if (clNode.getNodeType() == Node.ELEMENT_NODE) {

										Element eElement = (Element) clNode;

										natureOfInterest[cLtemp][i] = eElement
												.getElementsByTagName("Nature_Of_Interest").item(0).getTextContent();

										if ((natureOfInterest[cLtemp][i].equalsIgnoreCase("MG"))
												|| (natureOfInterest[cLtemp][i].equalsIgnoreCase("LP"))) {

											System.out.println();

											String contractNum = xPath.compile("//Contract/Contract_Num")
													.evaluate(xmlDocument);

											System.out.print("5 " + "MT " + contractNum);

											System.out.print(" " + xPath.compile("//Contract/Eff_Date")
													.evaluate(xmlDocument).replace("-", "").substring(0, 8));

											System.out.print(" "
													+ xPath.compile("//Endorsement/Endorse_Eff_Date")
															.evaluate(xmlDocument).replace("-", "").substring(0, 8)
													+ " " + fileName.substring(fileName.indexOf("_") + 1,
															fileName.indexOf("_") + 4)
													+ " EN " + "001 ");

											System.out.print(
													String.format("%3s", cdglElement.getElementsByTagName("Loc_Num")
															.item(0).getTextContent()).replace(' ', '0') + " ");

											System.out
													.print(String
															.format("%3s",
																	cdglElement.getElementsByTagName("Sub_Loc_Num")
																			.item(0).getTextContent())
															.replace(' ', '0') + " ");

											System.out.print(String.format("%3s", mgNumber).replace(' ', '0') + " ");

											mgNumber++;

											i++;

											natureOfInterest[cLtemp][i] = eElement.getElementsByTagName("Entity_Name")
													.item(0).getTextContent();

											System.out.print(String.format("%-50s", natureOfInterest[cLtemp][i])
													.substring(0, 30));

											NodeList aList = eElement.getElementsByTagName("Address");

											for (int aTemp = 0; aTemp < aList.getLength(); aTemp++) {

												Node alNode = aList.item(aTemp);

												if (alNode.getNodeType() == Node.ELEMENT_NODE) {

													Element aElement = (Element) alNode;

													if (aElement.getElementsByTagName("Dflt_Address_Ind").item(0)
															.getTextContent().equalsIgnoreCase("Y")) {

														System.out.print(" \t \t");

														System.out.print(String
																.format("%-30s",
																		aElement.getElementsByTagName("Address_Line_1")
																				.item(0).getTextContent())
																.substring(0, 30));

														System.out.print("\t \t");

														String addressLine2 = (aElement
																.getElementsByTagName("Address_Line_2")
																.getLength() == 0)
																		? ""
																		: aElement
																				.getElementsByTagName("Address_Line_2")
																				.item(0).getTextContent();

														System.out.print(
																String.format("%-30s", addressLine2).substring(0, 30)
																		+ "  ");

														System.out
																.print(String
																		.format("%-20s",
																				aElement.getElementsByTagName("City")
																						.item(0).getTextContent())
																		+ " ");

														System.out
																.print(String
																		.format("%2s",
																				aElement.getElementsByTagName("State")
																						.item(0).getTextContent())
																		+ " ");

														String zip = (aElement.getElementsByTagName("Zip")
																.getLength() == 0) ? ""
																		: aElement.getElementsByTagName("Zip").item(0)
																				.getTextContent();

														if (zip.length() != 0) {
															System.out.print(String.format("%5s",
																	zip.substring(zip.length() - 5)));
														}

														else {
															System.out.print("     ");
														}

														if (natureOfInterest[cLtemp][i].equalsIgnoreCase("MG"))
															System.out.print(" M");
														else
															System.out.print(" L");

													}
												}

											}

										}

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
