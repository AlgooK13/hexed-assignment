package com.hexed.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.hexed.model.Products;

import junit.framework.Assert;

public class UtilityTest {

	@Test
	public void getPackPriceTest() {
		Products prd = new Products(null, 0);
		List<Products> lstProducts = new ArrayList<Products>();
		lstProducts.add(prd);
		Map<Integer, Float> mapPackPrice = Utility.getPackPrice(lstProducts);
		Assert.assertTrue(mapPackPrice != null);

	}
}
