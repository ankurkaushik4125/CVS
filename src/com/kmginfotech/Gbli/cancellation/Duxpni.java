package com.kmginfotech.Gbli.cancellation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;

import com.kmginfotech.Gbli.conditionalData.AddingTwoMonthsInEndDate;
import com.kmginfotech.Gbli.conditionalData.ReadEndFormNo;
import com.kmginfotech.Gbli.conditionalData.ReadPremiumAndLimitData;

public class Duxpni {

	FileInputStream inputFile;
	String fileName;
	ReadEndFormNo eadEndFormNo = new ReadEndFormNo();
	ReadPremiumAndLimitData readPremiumData = new ReadPremiumAndLimitData();

	ArrayList<String> endFormId = new ArrayList<String>();

	AddingTwoMonthsInEndDate objAddingTwoMonthsInEndDate = new AddingTwoMonthsInEndDate();

	public void readDataForDuxpni() {

		File f = new File("./PolicyXMLs/CAN"); // current directory

		File[] files = f.listFiles();

		for (File file : files) {

			try {

				fileName = file.getPath();

				inputFile = new FileInputStream(new File(fileName));

				DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

				DocumentBuilder builder = builderFactory.newDocumentBuilder();

				Document xmlDocument = builder.parse(file);

				XPath xPath = XPathFactory.newInstance().newXPath();

				String product = xPath.compile("//Contract/Line_Code").evaluate(xmlDocument);

				if (product.equalsIgnoreCase("CommPkg")) {

					String contractNum = xPath.compile("//Contract/Contract_Num").evaluate(xmlDocument);

					System.out.print("5 " + "NI " + contractNum);

					System.out.print(" " + xPath.compile("//Contract/Eff_Date").evaluate(xmlDocument).replace("-", "")
							.substring(0, 8));

					System.out.print(" "
							+ xPath.compile("//Cancel/Cancel_Date").evaluate(xmlDocument).replace("-", "").substring(0,
									8)
							+ " " + fileName.substring(fileName.indexOf("_") + 1, fileName.indexOf("_") + 4) + " CA ");

					System.out.print(readPremiumData.premium(fileName, "//Contract/Written_Prem") + " ");

					String insDueDate = xPath.compile("//Contract/Eff_Date").evaluate(xmlDocument).substring(0, 10);

					System.out.print(objAddingTwoMonthsInEndDate.addTwoMonthInAPassedDate(insDueDate).replace("-", ""));

					System.out.println("\t\t\t");

				}
			}

			catch (FileNotFoundException fe) {

			} catch (Exception e) {

			}

		}
	}

}
