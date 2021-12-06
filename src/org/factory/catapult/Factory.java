package org.factory.catapult;

import org.utils.catapult.*;

public class Factory {
	
	   //use getShape method to get object of type shape 
	   public ProcessCatapult getTemplate(String shapeType){
	      if(shapeType == null){
	         return null;
	      }		
	      if(shapeType.equalsIgnoreCase("JSP")){
	         return new GenerateJSP();
	      }
	     
	      
	      return null;
	   }
	}