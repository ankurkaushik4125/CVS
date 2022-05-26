import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class UniqueProductCodeGLLineWideCode {

	static FileInputStream inputFile;
	static String fileName;

	static String description;

	static HashMap<String, String> hashMap = new HashMap<String, String>();

	public static void main(String[] args)
			throws ParserConfigurationException, XPathExpressionException, SAXException, IOException {

		Set<String> unqProductCode = new HashSet<String>();

		File f = new File("./PolicyXMLs/NB"); // current directory

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

				NodeList contractLineList = xmlDocument.getElementsByTagName("Contract_Line");

				if (contractLineList.getLength() != 0) {

					for (int contractLineListTemp = 0; contractLineListTemp < contractLineList
							.getLength(); contractLineListTemp++) {

						Node contractLineListNode = contractLineList.item(contractLineListTemp);

						if (contractLineListNode.getNodeType() == Node.ELEMENT_NODE) {

							Element contractLineListElement = (Element) contractLineListNode;

							String linecode = contractLineListElement.getElementsByTagName("Line_Code").item(0)
									.getTextContent();

							if (linecode.equalsIgnoreCase("GenLiablty")) {

								NodeList lineWideProductList = contractLineListElement
										.getElementsByTagName("Line_Wide_Product");

								if (lineWideProductList.getLength() != 0) {

									for (int lineWideProductListTemp = 0; lineWideProductListTemp < contractLineList
											.getLength(); lineWideProductListTemp++) {

										Node lineWideProductListNode = lineWideProductList
												.item(lineWideProductListTemp);

										if (lineWideProductListNode.getNodeType() == Node.ELEMENT_NODE) {

											Element lineWideProductListElement = (Element) lineWideProductListNode;

											NodeList productList = lineWideProductListElement
													.getElementsByTagName("Product");

											if (productList.getLength() != 0) {

												for (int productListTemp = 0; productListTemp < productList
														.getLength(); productListTemp++) {

													Node productListNode = productList.item(productListTemp);

													if (productListNode.getNodeType() == Node.ELEMENT_NODE) {

														Element pElement = (Element) productListNode;

														String productCode = (pElement
																.getElementsByTagName("Product_Code").getLength() == 0)
																		? "N?A"
																		: (pElement.getElementsByTagName("Product_Code")
																				.item(0).getTextContent());

														
														if( (productCode.equalsIgnoreCase("APMP")))
														{
														System.out.print(xPath.compile("//Contract/Contract_Num")
																.evaluate(xmlDocument) + " @ ");

														System.out.print(productCode + " @ ");

														NamedNodeMap desc = pElement
																.getElementsByTagName("Product_Code").item(0)
																.getAttributes();

														for (int a = 0; a < desc.getLength(); a++) {
															Node theAttribute = desc.item(a);
															System.out.println(theAttribute.getNodeValue());
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
					}
				}
			}

			catch (Exception e) {

			}
		}

	}
}
