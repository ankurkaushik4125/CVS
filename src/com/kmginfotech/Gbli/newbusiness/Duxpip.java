package com.kmginfotech.Gbli.newbusiness;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;

import com.kmginfotech.Gbli.conditionalData.ReadPremiumAndLimitData;

public class Duxpip {

	FileInputStream inputFile;
	String fileName;

	ReadPremiumAndLimitData readLimitData = new ReadPremiumAndLimitData();

	public void readDataForDuxpip() {

		File f = new File("./PolicyXMLs/NB"); // current directory

		File[] files = f.listFiles();

		for (File file : files) {

			try {

				fileName = file.getPath();

				inputFile = new FileInputStream(new File(fileName));

				DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

				DocumentBuilder builder = builderFactory.newDocumentBuilder();

				Document xmlDocument = builder.parse(file);

				XPath xPath = XPathFactory.newInstance().newXPath();

				String contractNum = xPath.compile("//Contract/Contract_Num").evaluate(xmlDocument);

				System.out.print("5 " + "IP " + contractNum);

				System.out.print(" "
						+ xPath.compile("//Contract/Eff_Date").evaluate(xmlDocument).replace("-", "").substring(0, 8));

				System.out.print(" "
						+ xPath.compile("//Contract/Eff_Date").evaluate(xmlDocument).replace("-", "").substring(0, 8)
						+ " " + fileName.substring(fileName.indexOf("_") + 1, fileName.indexOf("_") + 4) + " IS"
						+ " 002 ");

				System.out.print(" " + readLimitData.readLimit(fileName,
						"//Contract_Line[Line_Code='GenLiablty']/Line_Wide_Product/Product[Product_Code='GENAG']/Limit_1"));

				System.out.print(" " + readLimitData.readLimit(fileName,
						"//Contract_Line[Line_Code='GenLiablty']/Line_Wide_Product/Product[Product_Code='PRDCO']/Limit_1"));

				System.out.print(" " + readLimitData.readLimit(fileName,
						"//Contract_Line[Line_Code='GenLiablty']/Line_Wide_Product/Product[Product_Code='PIADV']/Limit_1"));

				System.out.print(" " + readLimitData.readLimit(fileName,
						"//Contract_Line[Line_Code='GenLiablty']/Line_Wide_Product/Product[Product_Code='EAOCC']/Limit_1"));

				System.out.print(" " + readLimitData.readLimit(fileName,
						"//Contract_Line[Line_Code='GenLiablty']/Line_Wide_Product/Product[Product_Code='FIRDM']/Limit_1"));

				System.out.print(" " + readLimitData.readLimit(fileName,
						"//Contract_Line[Line_Code='GenLiablty']/Line_Wide_Product/Product[Product_Code='MED']/Limit_1")
						+ "00");

				System.out.println("\t\t\t");

			} catch (FileNotFoundException fe) {

			} catch (Exception e) {

			}

		}

	}

}
