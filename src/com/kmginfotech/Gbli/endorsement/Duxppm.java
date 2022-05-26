package com.kmginfotech.Gbli.endorsement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;

import com.kmginfotech.Gbli.conditionalData.ReadAddressData;
import com.kmginfotech.Gbli.conditionalData.ReadLegalEntity;
import com.kmginfotech.Gbli.conditionalData.ReadPremiumAndLimitData;

public class Duxppm {

	FileInputStream inputFile;
	String fileName;
	ArrayList<String> address;

	ReadAddressData readAddressDataObj = new ReadAddressData();
	ReadLegalEntity readLegalEntityObj = new ReadLegalEntity();
	ReadPremiumAndLimitData readPremiumData = new ReadPremiumAndLimitData();

	public void readDataForDuxppm() {

		File f = new File("./PolicyXMLs/END"); // current directory

		File[] files = f.listFiles();

		for (File file : files) {

			try {

				fileName = file.getPath();

				inputFile = new FileInputStream(new File(fileName));

				DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

				DocumentBuilder builder = builderFactory.newDocumentBuilder();

				Document xmlDocument = builder.parse(inputFile);

				XPath xPath = XPathFactory.newInstance().newXPath();

				String contractNum = xPath.compile("//Contract/Contract_Num").evaluate(xmlDocument);

				System.out.print("5" + " PM " + contractNum);

				System.out.print(" "
						+ xPath.compile("//Contract/Eff_Date").evaluate(xmlDocument).replace("-", "").substring(0, 8));

				System.out.print(" "
						+ xPath.compile("//Endorsement/Endorse_Eff_Date").evaluate(xmlDocument).replace("-", "")
								.substring(0, 8)
						+ " " + fileName.substring(fileName.indexOf("_") + 1, fileName.indexOf("_") + 4) + " EN" + " "
						+ " " + " ");

				String product = xPath.compile("//Contract/Line_Code").evaluate(xmlDocument);

				if (product.equalsIgnoreCase("CommPkg")) {
					System.out.print("CMP");
				}

				else if (product.equalsIgnoreCase("GenLiablty")) {
					System.out.print("GLP");
				}

				System.out.print(" "
						+ xPath.compile("//Contract/Exp_Date").evaluate(xmlDocument).replace("-", "").substring(0, 8)
						+ " O" + " " + " 0");

				System.out.print((contractNum.contains("CVS") ? " 01417 " : " 01416 "));

				String term = (xPath.compile("//Contract/Term").evaluate(xmlDocument));

				System.out.print(String.format("%2s", term).replace(' ', ' ') + " ");

				address = readAddressDataObj.readAddress(fileName, "Consumer");

				System.out.print(String.format("%-35s", address.get(3)).substring(0, 30) + " ");

				System.out.print(String.format("%-35s", xPath.compile("//Consumer/Entity_Name").evaluate(xmlDocument))
						.substring(0, 30) + " ");

				System.out.print(String.format("%-30s", address.get(2)).substring(0, 20) + "  ");

				System.out.print(String.format("%-4s", address.get(1)).substring(0, 2) + "  ");

				String zip = address.get(0);

				System.out.print(zip.substring(zip.length() - 5) + " ");

				System.out.print(String.format("%-35s", xPath.compile("//Consumer/Entity_Name").evaluate(xmlDocument))
						.substring(0, 30));

				System.out.print("  0056 " + readLegalEntityObj.readLegalEntity(fileName) + "  ");

				System.out.print(readPremiumData.premium(fileName, "//Contract/Written_Prem") + "  ");

				System.out.print(readPremiumData.premium(fileName, "//Contract/Min_Earned_Amount") + "  P  ");

				System.out.print(xPath.compile("//Contract/Audit_Ind").evaluate(xmlDocument) + " 01");

				System.out.println("\t\t\t");

			} catch (FileNotFoundException fe) {

			} catch (Exception e) {

			}

		}
	}

}
