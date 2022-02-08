package org.utils.catapult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GenerateSQL implements ProcessCatapult {

    private static final String FILE_NAME = "sampleFile/catapult.xlsx";
    public String templateReplace="";
	@Override
	public void readFile() {
        try {
        List<readInput> inputList = new ArrayList();
        FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
        
        try (Workbook workbook = new XSSFWorkbook(excelFile)) {
			org.apache.poi.ss.usermodel.Sheet datatypeSheet = workbook.getSheetAt(2);
			String sheetName = workbook.getSheetName(2);
			templateReplace = "CREATE TABLE "+ sheetName +" (\n "+ sheetName + "ID int,";
			
			Iterator<Row> iterator = datatypeSheet.iterator();
			
			while (iterator.hasNext()) {

			    Row currentRow = iterator.next();
			    Iterator<Cell> cellIterator = currentRow.iterator();
			     	
			    System.out.println(currentRow.getRowNum());
			    
			    if(currentRow.getRowNum() != 0) 
			    {
			        readInput ri = new readInput();
				    while (cellIterator.hasNext()) { 					    				    	
				        Cell currentCell = cellIterator.next();
				        String celladdr = currentCell.getAddress().toString();
				        char cellddr = celladdr.charAt(0);
				        switch(cellddr) {
				        case 'A':
				        	System.out.print(currentCell.getStringCellValue());
				        	ri.setColumnType(currentCell.getStringCellValue());
				        break;
				        case 'B':
				        	System.out.print(currentCell.getStringCellValue());
				        	ri.setDescription(currentCell.getStringCellValue());				        	
					    break;
				        }
				    }	
			        inputList.add(ri);
			    }
			    System.out.println();
			}
		}
        for (final readInput ir : inputList) {
     	   System.out.println("get input type "+ir.getInputType());
     	   templateReplace += basicDeclare(ir);
        }
        templateReplace = templateReplace.substring(0,templateReplace.length()-1);
        templateReplace += "\n);";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void writeFile() {
        try {
            FileWriter myWriter = new FileWriter("createTable.txt");
            myWriter.write(templateReplace);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
	}
	
	private String basicDeclare(readInput ri){
		String colCmd="";
		String[] colSplit = ri.getColumnType().split("#");
		String[] nameSplit = colSplit[1].split("=");
		
		switch(colSplit[0]) {
		  case "L":
		  case "Y":
		  case "Rd":
		  case "Q":
		    colCmd = "\n "+ nameSplit[0] + " Int,"; 
			break;
		  case "T":
		  case "Ch":
		  case "Ta":
		  case "F":
			colCmd = "\n "+ nameSplit[0] + " varchar(255),";
			break;
		  case "D":
			colCmd = "\n "+ nameSplit[0] + " datetime,";
			break;
		  case "R":
			colCmd = "\n "+ nameSplit[0] + " money,";
			break;
		  case "H":
			colCmd = "\n "+ nameSplit[0] + " varchar(100),";
			break;
		}
		return colCmd;
	}

}
