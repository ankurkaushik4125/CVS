package com.kmginfotech.Gbli.newbusiness;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
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

	PrintWriter out = null;

	public void readDataForDuxpie() {

		File f = new File("./PolicyXMLs/NB"); // current directory

		File[] files = f.listFiles();

		for (File file : files) {

			try {

				out = new PrintWriter(new FileOutputStream(new File("C:/Users/puneet.joshi/Desktop/DUXPIE.txt"), true));

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

					out.println("5" + "IE" + contractNum + "" + effDate + "" + effDate + "" + ""
							+ fileName.substring(fileName.indexOf("_") + 1, fileName.indexOf("_") + 4) + "IS"
							+ (endFormId.get(i)).toString() + "\n");

				}

			} catch (FileNotFoundException e) {

			} catch (Exception e) {

			}

			finally {
				if (out != null) {
					out.close();
				}
			}

		}

	}

}
