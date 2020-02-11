package com.hexed.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.hexed.domain.ProductSetup;
import com.hexed.model.Product;

import junit.framework.Assert;

public class ProductSetupTest {

	@Test
	public void getPackPriceTest() {
		Product prd = new Product(null, 0);
		List<Product> lstProducts = new ArrayList<Product>();
		lstProducts.add(prd);
		Map<Integer, Float> mapPackPrice = ProductSetup.getPackPrice(lstProducts);
		Assert.assertTrue(mapPackPrice != null);

	}
}
