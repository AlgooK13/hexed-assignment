package com.hexed.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.hexed.common.ProductRepository;
import com.hexed.model.OrderReceipt;
import com.hexed.model.Product;

public class OrderProcessor {

	private static final Logger logger = Logger.getLogger(OrderProcessor.class.getName());

	public static Map<Integer, Integer> calculateOrder(Product product) {
		Map<Integer, Integer> mapPacks = null;
		if (product != null) {
			mapPacks = new HashMap<Integer, Integer>();
			List<Product> lstProduct = ProductRepository.getAvailblePacks(product.getCode());
			Map<String, List<Integer>> sortedProductData = ProductSetup.getSortedPackList(lstProduct);
			List<Integer> packs = sortedProductData.get(product.getCode());
			int totalCount = product.getQuantity();
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

						if (mapPacks.containsKey(packValue)) {
							mapPacks.put(packValue, mapPacks.get(packValue) + modulus);
						} else
							mapPacks.put(packValue, modulus);
						totalCount = totalCount % packValue;
					}
				}
			}
			if (totalCount > 0) {
				logger.info("Invalid Count Provided");
				mapPacks = null;
			}
		}
		return mapPacks;

	}

	public static OrderReceipt genrateOrderBill(Product product, Map<Integer, Integer> orderCount) {
		OrderReceipt orderReceipt = null;
		try {
			if (orderCount != null && product != null) {
				float totalAmount = 0.f;
				orderReceipt = new OrderReceipt();
				List<Product> lstProduct = ProductRepository.getAvailblePacks(product.getCode());
				Map<Integer, Float> packPrice = ProductSetup.getPackPrice(lstProduct);
				List<String> packs = new ArrayList<String>();
				for (Map.Entry<Integer, Integer> ordData : orderCount.entrySet()) {
					float price = packPrice.get(ordData.getKey());
					totalAmount = totalAmount + (price * ordData.getValue());
					packs.add(ordData.getValue() + " X " + ordData.getKey() + " $" + price);
				}
				orderReceipt.setCode(product.getQuantity() + " " + product.getCode());
				orderReceipt.setTotalAmount(totalAmount);
				orderReceipt.setPacks(packs);

			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			
		}
		return orderReceipt;

	}
}
