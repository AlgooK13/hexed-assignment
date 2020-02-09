package com.hexed.common;

import java.util.List;

import org.junit.Test;

import com.hexed.model.Products;

import junit.framework.Assert;

public class ProductStoreTest {
	@Test
	public void getAvailblePacksPositiveTest() {
		String productCode = "VS5";
		List<Products> lstProduct = ProductStore.getAvailblePacks(productCode);
		Assert.assertTrue(lstProduct.get(0) instanceof Products);
		
		productCode = "MB11";
		lstProduct = ProductStore.getAvailblePacks(productCode);
		Assert.assertTrue(lstProduct.get(0) instanceof Products);
		
		productCode = "CF";
		lstProduct = ProductStore.getAvailblePacks(productCode);
		Assert.assertTrue(lstProduct.get(0) instanceof Products);

	}

	@Test
	public void getAvailblePacksNagetiveTest() {
		String productCode = "VS6";
		List<Products> lstProduct = ProductStore.getAvailblePacks(productCode);
		Assert.assertFalse(!(lstProduct!=null));
	}

}
