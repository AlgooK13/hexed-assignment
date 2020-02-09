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

	public static Map<Integer, Integer> calPriceBreakdownCount(Products products) {
		Map<Integer, Integer> mapPacks = null;
		if (products != null) {
			mapPacks = new HashMap<Integer, Integer>();
			List<Products> lstProduct = ProductStore.getAvailblePacks(products.getCode());
			Map<String, List<Integer>> sortedProductData = Utility.getSortedPackList(lstProduct);
			List<Integer> packs = sortedProductData.get(products.getCode());
			int totalCount = products.getQuantity();
			int modulus = 0;
			int packValue = 0;
			int size = packs.size();
			while (totalCount > 0 && size > 0) {
				if (packValue > 0) {
					int packIndex = packs.indexOf(packValue);
					if (packIndex == 0) {
						packValue = packs.get(size - 1);
					}
					if (mapPacks.containsKey(packValue)) {
						size = size - 1;
						if (mapPacks.get(packValue) > 1) {
							mapPacks.put(packValue, mapPacks.get(packValue) - 1);
						} else
							mapPacks.remove(packValue);
					}

					totalCount = totalCount + packValue;
				}

				for (int i = size - 1; i >= 0; i--) {
					modulus = totalCount / packs.get(i);
					if (modulus > 0) {
						packValue = packs.get(i);
						mapPacks.put(packValue, modulus);
						totalCount = totalCount % packValue;
					}
				}
			}
			if (totalCount > 0) {
				logger.info("Invalid Count Provided");
			}
		}
		return mapPacks;

	}

	public static OrderReceipt calPriceBreakdownCountbkp(Products products) {
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

	public static OrderReceipt genrateOrderBill(Products products, Map<Integer, Integer> orderCount) {
		OrderReceipt orderReceipt = null;
		try {
			if (orderCount != null && products != null) {
				float totalAmount = 0.f;
				orderReceipt = new OrderReceipt();
				List<Products> lstProduct = ProductStore.getAvailblePacks(products.getCode());
				Map<Integer, Float> packPrice = Utility.getPackPrice(lstProduct);
				List<String> packs=new ArrayList<String>();
				for (Map.Entry<Integer, Integer> ordData : orderCount.entrySet()) {
					float price=packPrice.get(ordData.getKey());
					totalAmount=totalAmount+(price*ordData.getValue());
					packs.add(ordData.getValue()+" X "+ordData.getKey()+" $"+price);
				}
				orderReceipt.setCode(products.getQuantity()+" "+products.getCode());
				orderReceipt.setTotalAmount(totalAmount);
				orderReceipt.setPacks(packs);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return orderReceipt;

	}
}
