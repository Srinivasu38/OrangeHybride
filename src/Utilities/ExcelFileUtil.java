package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {

	Workbook wb;
	//constructor
	public  ExcelFileUtil(String excelpath) throws Throwable
	{
		FileInputStream fi= new FileInputStream(excelpath);
		wb=WorkbookFactory.create(fi);

	}
	//count no of rows 
	public int rowcount(String sheetname)
	{
		return	wb.getSheet(sheetname).getLastRowNum();
	}

	//colum count from rows
	public int colcount(String sheetname) throws Throwable
	{
		return wb.getSheet(sheetname).getRow(0).getLastCellNum();
	}

	//get cell data
	public String getCellData(String sheetname,int row,int column) throws Throwable
	{
		String data=" ";
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			//convert interger type into string
			data=String.valueOf(celldata);

		}
		else
		{
			data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	//write into status colum
	public void setCellData(String sheetname, int row,int column,String status, String writeexcel) throws Throwable
	{
		//get sheet from wb
		Sheet ws= wb.getSheet(sheetname);

		//get row from sjheet
		Row rownum=ws.getRow(row);

		//create cell
		Cell cell=rownum.createCell(column);

		//write status in cell
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("pass"))
		{
			CellStyle style =wb.createCellStyle();
			Font font=wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		
		else if (status.equalsIgnoreCase("Blocked"))
		{
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}

		else if(status.equalsIgnoreCase("Blocked"))
		{
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}

		FileOutputStream fo=new FileOutputStream(writeexcel);
		wb.write(fo);
	}
}

