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

public class GenerateJSP implements ProcessCatapult {

    private static final String FILE_NAME = "sampleFile/catapult.xlsx";
    public String templateReplace="";
    @Override
	public void readFile() {
        try {
        	List<readInput> inputList = new ArrayList();
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            try (Workbook workbook = new XSSFWorkbook(excelFile)) {
				org.apache.poi.ss.usermodel.Sheet datatypeSheet = workbook.getSheetAt(0);
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
					        break;
					        case 'B':
					        	System.out.print(currentCell.getStringCellValue());
					        	ri.setInputType(currentCell.getStringCellValue());				        	
						    break;
					        case 'C':
					        	System.out.print(currentCell.getBooleanCellValue());
					        	ri.setCanEdit(currentCell.getBooleanCellValue());
						    break;
					        case 'D':
					        	System.out.print(currentCell.getStringCellValue());
					        	ri.setLabel(currentCell.getStringCellValue());
						    break;
					        case 'E':
					        	System.out.print(currentCell.getBooleanCellValue());
					        	ri.setMandatory(currentCell.getBooleanCellValue());
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
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}


	@Override
	public void writeFile() {
        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            myWriter.write(templateReplace);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
	}

	
	private String basicDeclare(readInput ri)
	{
		String jspwtr="";
		String addFeat="";
		if(!ri.label.isEmpty())
		{
			addFeat += ".label(\""+ ri.label +"\")\n";
		}
		if(ri.mandatory)
		{
			addFeat += ".mandatory()\n";
		}
		if(ri.canEdit) {
			addFeat +=".canEdit(true,true)\n";
		}
				
		switch(ri.InputType){
			case "radio":
				jspwtr = "<%=Html.out((hf) -> { hf.radio(\"status\",0,yesNoList)"+addFeat+";})%>\n";
			break;
			case "textarea":
				jspwtr = "<%=Html.out((hf) -> { hf.textarea(\"NEWTEXTAREAID\", \"Description\")"+addFeat+";})%>\n";
			break;		
		}
		return jspwtr;
	}
	
	
	}