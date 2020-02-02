package com.hexed.process;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.hexed.common.ProductStore;
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
			List<Products> lstProduct = ProductStore.getAvailblePacks(products.getCode());
			int[] packs = { 3, 5 };
			int totleCount = products.getQuantity();
			float totalAmount = 0f;
			for (int i = packs.length - 1; i >= 0; i--) {

				while (totleCount >= packs[i]) {
					productData = lstProduct.get(i);
					if (totleCount >= packs[i]) {
						totleCount = totleCount - packs[i];
						totalAmount = totalAmount + (productData.getPrice());
						lstPacks.add(String.valueOf(productData.getQuantity()));
					}
				}
			}
			if (productData != null)
				ordReceipt.setCode(productData.getCode());
			ordReceipt.setTotalAmount(totalAmount);
			ordReceipt.setPacks(lstPacks);
			if (totleCount > 0) {
				logger.info("Invald Product " + products.getCode() + " Count " + products.getQuantity() + " Products");
				ordReceipt = null;
			}
		}
		return ordReceipt;

	}

}
