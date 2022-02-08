package org.main.catapult;

import org.factory.catapult.Factory;
import org.utils.catapult.ProcessCatapult;

public class Main {
    public static void main(String[] args) {
    	System.out.println("Working Directory = " + System.getProperty("user.dir"));
    	  
        Factory catapultFactory = new Factory();

        //get an object of JSP template and call its read and draw method.
        ProcessCatapult cata1 = catapultFactory.getTemplate("SQL");

        //call read file method of JSP template
        cata1.readFile();
        //call write file method of JSP template
        cata1.writeFile();
    }
}
