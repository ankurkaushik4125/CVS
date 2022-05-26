package com.kmginfotech.Gbli.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	File src;
	FileInputStream fis;
	XSSFWorkbook wb;
	DataFormatter formatter = new DataFormatter();
	XSSFSheet sheet;
	XSSFCell cell;
	XSSFRow row;

	public ExcelUtility(String path) {

		try {
			src = new File(path);

			// load file
			fis = new FileInputStream(src);

			// Load workbook
			wb = new XSSFWorkbook(fis);

		} catch (Exception e) {

		}
	}

	/**
	 * This method is used to read the data from the CELL of the EXCEL.
	 * 
	 * @param rowNum    The row from which the data is to be read.
	 * @param colNum    The column from which the data is to be read.
	 * @param sheetName The name of the sheet from which tha data is to be read.
	 * @return Return the fetched data from the cell of the passed sheet and
	 *         co-ordinate.
	 * 
	 */

	public String getData(int rowNum, int colNum, String sheetName) {

		cell = wb.getSheet(sheetName).getRow(rowNum).getCell(colNum);

		return formatter.formatCellValue(cell);

	}

	/**
	 * This method is used to get the total number of the data rows in the sheet.
	 * 
	 * @param sheetName The Sheet Name whose total number's of data rows is to get.
	 * @return Return the total number's of data data rows present in the passed
	 *         sheet.
	 * 
	 */
	public int getRowCount(String sheetName) {

		return wb.getSheet(sheetName).getLastRowNum();
	}

	/**
	 * This method is used to get the total number of the data columns in the sheet.
	 * 
	 * @param sheetName The Sheet Name whose total number's of data columns is to
	 *                  get.
	 * @return Return the total number's of data data columns present in the passed
	 *         sheet.
	 * 
	 */
	public int getcolumnCount(String sheetName) {

		return wb.getSheet(sheetName).getRow(0).getLastCellNum();
	}

	/**
	 * This method is used to write the data into the Excel.
	 * 
	 * @param sheetName The Sheet Name where the data is to be write.
	 * @param rowNum    The row number where the data is to be write.
	 * @param colNum    The column number where the data is to be write.
	 * @param value     The value which is to be write into the passed co-ordinate.
	 * 
	 */

	public void setData(String sheetName, int rowNum, int colNum, String value) {

		try {

			cell = wb.getSheet(sheetName).getRow(rowNum).createCell(colNum);

			cell.setCellValue(value);

			FileOutputStream fout = new FileOutputStream(src);

			wb.write(fout);

		} catch (IOException e) {

		}
	}

	/**
	 * 
	 * 
	 * @param sheetName
	 * @param scenarioName
	 * @return
	 * 
	 */
	public int isScenarioAvailable(String sheetName, String scenarioName) {

		int activerow = -1;
		for (int i = 0; i <= getRowCount(sheetName); i++) {

			String value = wb.getSheet(sheetName).getRow(i).getCell(0).getStringCellValue();
			if (scenarioName != null && value.equalsIgnoreCase(scenarioName)) {
				activerow = i;
				break;
			}
		}
		return activerow;

	}

	/**
	 * 
	 * 
	 * @param sheetName
	 * @param scenarioName
	 * @return
	 * 
	 */

	public int getLastactiveRow(String sheetName, String scenarioName) {
		int lastActiveRow = -1;
		for (int i = getRowCount(sheetName); i > 0; i--) {
			String val;
			val = wb.getSheet(sheetName).getRow(i).getCell(0).getStringCellValue();
			if (val.equalsIgnoreCase(scenarioName)) {
				lastActiveRow = i;
				break;
			}

		}

		return lastActiveRow;
	}

	/**
	 * This method is used to get the headings from the data column of the passed
	 * sheet of the excel.
	 * 
	 * @param sheetName The sheet name whose headings of the data column is to be
	 *                  read.
	 * @return Return a String array containing the headings present in the data
	 *         columns.
	 * 
	 */

	public String[] getcolumnHeadingInArray(String sheetName) {
		String[] colheading = new String[getcolumnCount(sheetName)];
		for (int i = 0; i < getcolumnCount(sheetName); i++) {
			colheading[i] = wb.getSheet(sheetName).getRow(0).getCell(i).getStringCellValue();
		}
		return colheading;
	}

}
