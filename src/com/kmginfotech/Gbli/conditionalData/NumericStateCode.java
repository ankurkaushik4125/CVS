package com.kmginfotech.Gbli.conditionalData;

import java.util.HashMap;
import java.util.Map.Entry;

import com.kmginfotech.Gbli.utilities.ExcelUtility;

public class NumericStateCode {

	String stateCode;

	HashMap<String, String> stateCodeMap = new HashMap<String, String>();

	ExcelUtility exlUtil = new ExcelUtility("./Resources/StateCodes.xlsx");

	public String getStateCode(String state) {

		int totalRows = exlUtil.getRowCount("Data");

		for (int i = 0; i < totalRows; i++) {

			String stateAbbr = exlUtil.getData(i, 1, "Data");

			String stateCode = exlUtil.getData(i, 0, "Data");

			stateCodeMap.put(stateAbbr, stateCode);

		}

		for (Entry<String, String> x : stateCodeMap.entrySet()) {

			if (x.getKey().equalsIgnoreCase(state)) {
				stateCode = x.getValue();
			}

		}

		return stateCode;
	}

}
