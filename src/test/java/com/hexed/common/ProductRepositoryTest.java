package com.hexed.common;

import java.util.List;

import org.junit.Test;

import com.hexed.model.Product;

import junit.framework.Assert;

public class ProductRepositoryTest {
	@Test
	public void productSettingPositiveTest() {
		String productCode = "VS5";
		List<Product> lstProduct = ProductRepository.getAvailblePacks(productCode);
		Assert.assertTrue(lstProduct.get(0) instanceof Product);
		
		productCode = "MB11";
		lstProduct = ProductRepository.getAvailblePacks(productCode);
		Assert.assertTrue(lstProduct.get(0) instanceof Product);
		
		productCode = "CF";
		lstProduct = ProductRepository.getAvailblePacks(productCode);
		Assert.assertTrue(lstProduct.get(0) instanceof Product);

	}

	@Test
	public void productSettingNagetiveTest() {
		String productCode = "VS6";
		List<Product> lstProduct = ProductRepository.getAvailblePacks(productCode);
		Assert.assertFalse(!(lstProduct!=null));
	}

}
