package com.hexed;

import java.util.logging.Logger;

import com.hexed.domain.HexedBakeryShop;


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
    	logger.info("Please enter the order ProductCode Count ex VS5 10. \n To Exit type 0 or Exit or exit.");
    	HexedBakeryShop shop = new HexedBakeryShop();
    	shop.start();
    	
    }
}
