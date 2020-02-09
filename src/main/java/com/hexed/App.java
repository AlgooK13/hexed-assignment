package com.hexed;

import java.util.Map;
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
    	Products products =  new Products("MB11", 17);
    	Map<Integer, Integer> orderData = Processor.calPriceBreakdownCount(products);
    	OrderReceipt ordReceipt = Processor.genrateOrderBill(products,orderData);
     	logger.info(ordReceipt.toString());
    	
    }
}
