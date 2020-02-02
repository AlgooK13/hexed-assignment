package com.hexed.common;

import java.util.ArrayList;
import java.util.List;

import com.hexed.model.Products;

public class ProductStore {
	public static List<Products> getAvailblePacks(String productCode) {
		List<Products> lstProduct = new ArrayList<Products>();
		switch(productCode) {
			case "VS5":
				lstProduct.add(new Products("VS5", 3, 6.99f));
				lstProduct.add(new Products("VS5", 5, 8.99f));
				break;
			case "MB11":
				lstProduct.add(new Products("MB11", 2, 9.95f));
				lstProduct.add(new Products("MB11", 5, 16.95f));
				lstProduct.add(new Products("MB11", 8, 24.95f));
				break;
			case "CF":
				lstProduct.add(new Products("CF", 3, 5.95f));
				lstProduct.add(new Products("CF", 5, 9.95f));
				lstProduct.add(new Products("CF", 9, 16.99f));
				break;
		}
		return lstProduct;
	}
}
