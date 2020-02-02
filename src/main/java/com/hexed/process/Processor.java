package com.hexed.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.hexed.common.ProductStore;
import com.hexed.common.Utility;
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
			Map<String, List<Integer>> sortedProductData = Utility.getSortedPackList(lstProduct);
			List<Integer> packs = sortedProductData.get(products.getCode());
			int totleCount = products.getQuantity();
			float totalAmount = 0f;
			for (int i = packs.size() - 1; i >= 0; i--) {

				while (totleCount >= packs.get(i)) {
					productData = lstProduct.get(i);

					if (totleCount % packs.size() == 0) {
						int rPacks = totleCount / packs.size();
						if (packs.contains(rPacks)) {
							totleCount = packs.size();
							float price = 0f;
							for (int j = 0; j < packs.size(); j++) {
								lstPacks.add(String.valueOf(rPacks));
								productData = lstProduct.get(j);
								if (productData.getQuantity() == rPacks) {
									price = productData.getPrice();
								}
								totalAmount = price * packs.size();
							}
							totleCount = totleCount - packs.size();
						}

						if (totleCount >= packs.get(i)) {
							totleCount = totleCount - packs.get(i);
							totalAmount = totalAmount + (productData.getPrice());
							lstPacks.add(String.valueOf(productData.getQuantity()));
						}
					}
					if (totleCount >= packs.get(i)) {
						totleCount = totleCount - packs.get(i);
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

	