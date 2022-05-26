package com.kmginfotech.Gbli.endorsement;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.kmginfotech.Gbli.conditionalData.NumericStateCode;
import com.kmginfotech.Gbli.conditionalData.ReadPremiumAndLimitData;

public class Duxpis {

	InputStream inputFile;
	String fileName;
	NumericStateCode objNumericStateCode = new NumericStateCode();
	ReadPremiumAndLimitData readPremiumData = new ReadPremiumAndLimitData();

	public void readDataForDuxpis() {

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

				NodeList cdgllList = xmlDocument.getElementsByTagName("CDGL_Class_Rating");

				if (cdgllList.getLength() != 0) {

					for (int cdgltemp = 0; cdgltemp < cdgllList.getLength(); cdgltemp++) {

						Node glplNode = cdgllList.item(cdgltemp);

						if (glplNode.getNodeType() == Node.ELEMENT_NODE) {

							Element cdglElement = (Element) glplNode;

							NodeList productlList = cdglElement.getElementsByTagName("Product");

							String[][] productCode = new String[productlList.getLength()][6];

							if (productlList.getLength() != 0) {

								for (int cLtemp = 0; cLtemp < productlList.getLength(); cLtemp++) {

									int i = 0;

									Node clNode = productlList.item(cLtemp);

									if (clNode.getNodeType() == Node.ELEMENT_NODE) {

										Element eElement = (Element) clNode;

										productCode[cLtemp][i] = eElement.getElementsByTagName("Product_Code").item(0)
												.getTextContent();

										if ((productCode[cLtemp][i].equalsIgnoreCase("PREM"))
												|| (productCode[cLtemp][i].equalsIgnoreCase("PRDCO"))) {

											System.out.println();

											String contractNum = xPath.compile("//Contract/Contract_Num")
													.evaluate(xmlDocument);

											System.out.print("5 " + "IS " + contractNum);

											System.out.print(" " + xPath.compile("//Contract/Eff_Date")
													.evaluate(xmlDocument).replace("-", "").substring(0, 8));

											String stateCode = objNumericStateCode.getStateCode(
													xPath.compile("//Contract/Contract_State").evaluate(xmlDocument));

											System.out.print(" "
													+ xPath.compile("//Endorsement/Endorse_Eff_Date").evaluate(xmlDocument)
															.replace("-", "").substring(0, 8)
													+ " "
													+ fileName.substring(fileName.indexOf("_") + 1,
															fileName.indexOf("_") + 4)
													+ " EN " + stateCode + "  " + "002" + "   ");

											System.out.print(
													String.format("%3s", cdglElement.getElementsByTagName("Loc_Num")
															.item(0).getTextContent()).replace(' ', '0') + " ");

											String subLocNum = (cdglElement.getElementsByTagName("Sub_Loc_Num")
													.getLength() == 0) ? "001"
															: cdglElement.getElementsByTagName("Sub_Loc_Num").item(0)
																	.getTextContent();

											System.out.print(String.format("%3s", subLocNum).replace(' ', '0') + " ");

											System.out
													.print(String
															.format("%6s",
																	cdglElement.getElementsByTagName("Class_Code")
																			.item(0).getTextContent())
															.replace(' ', '0') + " ");

											String polType = xPath.compile("//Contract/Line_Code")
													.evaluate(xmlDocument);

											if (polType.contains("Gen"))
												System.out.print("10");
											else
												System.out.print("34");

											System.out.print("   "
													+ String.format("%8s", cdglElement.getElementsByTagName("Exposure")
															.item(0).getTextContent()).replace(' ', '0')
													+ " ");

											String premium = eElement.getElementsByTagName("Net_Change_Amount").item(0)
													.getTextContent();

											System.out.print(readPremiumData.premiumDataWithoutXpath(premium));

											System.out.print("   " + eElement.getElementsByTagName("Fully_Earned_Ind")
													.item(0).getTextContent());

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
