package lib.common;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class XLManipulation {
	private Sheet worksheet = null;
	private Workbook workbook = null;

	public XLManipulation(String ExcelSheetPath) throws BiffException, IOException {
		workbook = Workbook.getWorkbook(new File(ExcelSheetPath));
	}

	public XLManipulation() throws BiffException, IOException {

	}

	// Get the total number of rows in the Worksheet1
	public int getRowCount(String sheetName) {
		worksheet = workbook.getSheet(sheetName);
		int number = worksheet.getRows() + 1;
		return number;
	}

	public Sheet getWorksheet() {
		return worksheet;
	}

	public void setWorksheet(Sheet worksheet) {
		this.worksheet = worksheet;
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}

	// Get the row number when you know the Column value
	public int getRowNumber(String sheetName, String valueInColumn) {
		worksheet = workbook.getSheet(sheetName);
		int totalRows = worksheet.getRows();
		for (int rowNumber = 0; rowNumber < totalRows; rowNumber++)
			if (worksheet.getCell(0, rowNumber).getContents().equals(valueInColumn))
				return rowNumber;
		return -1;

	}

	// Returns the Cell value by taking row and Column values as argument
	public String getCellValue(String sheetName, int column, int row) {
		worksheet = workbook.getSheet(sheetName);
		return worksheet.getCell(column, row).getContents();
	}

	public String getValueFor(String sheetName, String elementName) {
		worksheet = workbook.getSheet(sheetName);
		return worksheet.getCell(1, getRowNumber(sheetName, elementName)).getContents().toString();
	}
}