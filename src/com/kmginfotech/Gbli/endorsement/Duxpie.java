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

import com.kmginfotech.Gbli.conditionalData.ReadEndFormNo;

public class Duxpie {

	FileInputStream inputFile;
	String fileName;
	ReadEndFormNo eadEndFormNo = new ReadEndFormNo();

	ArrayList<String> endFormId = new ArrayList<String>();

	public void readDataForDuxpie() {

		File f = new File("./PolicyXMLs/END"); // current directory

		File[] files = f.listFiles();

		for (File file : files) {

			try {

				fileName = file.getPath();

				inputFile = new FileInputStream(new File(fileName));

				DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

				DocumentBuilder builder = builderFactory.newDocumentBuilder();

				Document xmlDocument = builder.parse(file);

				XPath xPath = XPathFactory.newInstance().newXPath();

				endFormId = eadEndFormNo.readEndForm(fileName);

				for (int i = 0; i < endFormId.size(); i++) {

					String contractNum = xPath.compile("//Contract/Contract_Num").evaluate(xmlDocument);

					String effDate = xPath.compile("//Contract/Eff_Date").evaluate(xmlDocument).replace("-", "")
							.substring(0, 8);

					System.out.print("5" + " IE " + contractNum + " " + effDate + " "
							+ xPath.compile("//Endorsement/Endorse_Eff_Date").evaluate(xmlDocument).replace("-", "")
									.substring(0, 8)
							+ " " + " " + fileName.substring(fileName.indexOf("_") + 1, fileName.indexOf("_") + 4)
							+ " EN " + (endFormId.get(i)).toString());

					System.out.println("\t\t\t");

				}

			} catch (FileNotFoundException fe) {

			} catch (Exception e) {

			}

		}
	}

}
