package com.hexed.common;

import java.util.ArrayList;
import java.util.List;

import com.hexed.model.Product;

public class ProductRepository {
	public static List<Product> getAvailblePacks(String productCode) {
		List<Product> lstProduct = new ArrayList<Product>();
		switch(productCode) {
			case "VS5":
				lstProduct.add(new Product("VS5", 3, 6.99f));
				lstProduct.add(new Product("VS5", 5, 8.99f));
				break;
			case "MB11":
				lstProduct.add(new Product("MB11", 2, 9.95f));
				lstProduct.add(new Product("MB11", 5, 16.95f));
				lstProduct.add(new Product("MB11", 8, 24.95f));
				break;
			case "CF":
				lstProduct.add(new Product("CF", 3, 5.95f));
				lstProduct.add(new Product("CF", 5, 9.95f));
				lstProduct.add(new Product("CF", 9, 16.99f));
				break;
		}
		return lstProduct;
	}
}
