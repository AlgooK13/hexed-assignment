package com.hexed.domain;

import org.junit.Test;

import com.hexed.model.OrderReceipt;
import com.hexed.model.Product;

import junit.framework.Assert;

public class ProcessOrderTest {

	@Test
	public void testPositiveOrders() {
		Product product = new Product("VS5", 13);
    	OrderReceipt ordReceipt = OrderProcessor.genrateOrderBill(product,OrderProcessor.calculateOrder(product));
    	
		String expected = "13 VS5 $24.97 [1 X 3 $6.99, 2 X 5 $8.99]";
		Assert.assertEquals("Positive case VS5:", expected, ordReceipt.toString());

	}
	
	@Test
	public void testPositiveOrdersMinimum() {
		Product product = new Product("VS5", 6);
    	OrderReceipt ordReceipt = OrderProcessor.genrateOrderBill(product,OrderProcessor.calculateOrder(product));
    	String expected = "6 VS5 $13.98 [2 X 3 $6.99]";    	
		Assert.assertEquals("Positive case VS5:", expected, ordReceipt.toString());

	}
	
	@Test
	public void testPositiveGivenCase() {
		Product product = new Product("MB11", 14);
    	OrderReceipt ordReceipt = OrderProcessor.genrateOrderBill(product,OrderProcessor.calculateOrder(product));
    	String expected = "14 MB11 $54.8 [3 X 2 $9.95, 1 X 8 $24.95]";    	
		Assert.assertEquals("Positive case VS5:", expected, ordReceipt.toString());

	}

	@Test
	public void testNegativeCaseInvalidCount() {
		Product product = new Product("VS5", 12);
    	OrderReceipt ordReceipt = OrderProcessor.genrateOrderBill(product,OrderProcessor.calculateOrder(product));
		Assert.assertEquals(ordReceipt, null);

	}
	
	@Test
	public void testNegativeCaseInvalidInput() {
		Product product = null;
    	OrderReceipt ordReceipt = OrderProcessor.genrateOrderBill(product,OrderProcessor.calculateOrder(product));
		Assert.assertEquals(ordReceipt, null);

	}
}
