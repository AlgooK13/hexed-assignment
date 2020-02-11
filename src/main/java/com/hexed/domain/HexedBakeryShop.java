package com.hexed.domain;

import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import com.hexed.model.OrderReceipt;
import com.hexed.model.Product;

public class HexedBakeryShop {

	private static final Logger logger = Logger.getLogger(HexedBakeryShop.class.getName());
	boolean flag = true;

	public void takeOrder(String order) {
		flag = false;
		if (!(order.equalsIgnoreCase("0") || order.equalsIgnoreCase("exit"))) {
			String[] orderInput = order.split(" ");
			if (orderInput.length > 1) {
				Product product = new Product(orderInput[0], Integer.parseInt(orderInput[1]));
				Map<Integer, Integer> orderData = OrderProcessor.calculateOrder(product);
				OrderReceipt ordReceipt = OrderProcessor.genrateOrderBill(product, orderData);
				logger.info(ordReceipt.toString());
				logger.info("-------------------Order Completed--------------------------");
			} else {
				logger.info("Provide the details in given Order.");
		    	logger.info("Please enter the order ProductCode Count ex VS5 10. \n To Exit type 0 or Exit or exit.");

			}

		} else {
			logger.info("Bakery Closed by User.");
			System.exit(0);
		}
		flag = true;
	}

	public void start() {
		while (flag) {
			Scanner in = new Scanner(System.in);
			String shopping = in.nextLine();
			takeOrder(shopping);
		}
	}
}
