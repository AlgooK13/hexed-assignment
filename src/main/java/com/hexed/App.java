package com.hexed;

import java.util.logging.Logger;

import com.hexed.model.OrderReceipt;
import com.hexed.model.Products;
import com.hexed.process.Processor;


/**
 * Hexed Bakery Assignment
 *
 */
public class App 
{
	private static final Logger logger = Logger.getLogger(App.class.getName());
	
    public static void main( String[] args )
    {
    	logger.info("Welcome to Bakery Shop");
    	Products products =  new Products("VS5", 13);
     	OrderReceipt ordReceipt = Processor.calPriceBreakdownCount(products);
     	ordReceipt.toString();
    	
    }
}
