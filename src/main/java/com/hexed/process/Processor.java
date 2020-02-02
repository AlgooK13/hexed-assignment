package com.hexed.process;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.hexed.model.OrderReceipt;
import com.hexed.model.Products;

public class Processor {

	private static final Logger logger = Logger.getLogger(Processor.class.getName());

	public static OrderReceipt calPriceBreakdownCount(Products products) {
		OrderReceipt ordReceipt = null;
		if (products != null) {
			ordReceipt = new OrderReceipt();
			Products productData = null;
			List<String> lstPacks = new ArrayList<String>();
			List<Products> lstProduct = new ArrayList<Products>();
			lstProduct.add(new Products("VS5", 3, 6.99f));
			lstProduct.add(new Products("VS5", 5, 8.99f));
			int[] packs = { 3, 5 };
			int totleCount = products.getQuantity();
			float totalAmount = 0f;
			for (int i = packs.length-1; i >=0; i--) {
				
				while (totleCount >= packs[i] ) {
					System.out.println("1");
					productData = lstProduct.get(i);
					if (totleCount >= packs[i]) {
						totleCount = totleCount - packs[i];
						totalAmount = totalAmount + (productData.getPrice());
						lstPacks.add(String.valueOf(productData.getQuantity()));
					}
				}
			}
			if (productData!=null)
				ordReceipt.setCode(productData.getCode());
			ordReceipt.setTotalAmount(totalAmount);
			ordReceipt.setPacks(lstPacks);
			if (totleCount > 0) {
				logger.info("Invald Product " + products.getCode() + " Count " + products.getQuantity() + " Products");
			}
		}
		logger.info("Invald Product" + ordReceipt.toString());
		return ordReceipt;

	}

}
