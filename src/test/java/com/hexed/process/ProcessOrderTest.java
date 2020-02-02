package com.hexed.process;

import org.junit.Test;

import com.hexed.model.OrderReceipt;
import com.hexed.model.Products;

import junit.framework.Assert;

public class ProcessOrderTest {

	@Test
	public void testPositiveOrders() {
		Products products = new Products("VS5", 13);
		OrderReceipt ordReceipt = Processor.calPriceBreakdownCount(products);
		String expected = "VS5 24.97 [5, 5, 3]";
		String actual = ordReceipt.getCode() + " " + ordReceipt.getTotalAmount() + " " + ordReceipt.getPacks();
		Assert.assertEquals("Positive case VS5:", expected, actual);

	}
	
	@Test
	public void testPositiveOrdersMinimum() {
		Products products = new Products("VS5", 6);
		OrderReceipt ordReceipt = Processor.calPriceBreakdownCount(products);
		String expected = "VS5 13.98 [3, 3]";
		String actual = ordReceipt.getCode() + " " + ordReceipt.getTotalAmount() + " " + ordReceipt.getPacks();
		Assert.assertEquals("Positive case VS5:", expected, actual);

	}
	
	@Test
	public void testPositiveOrder() {
		Products products = new Products("MB11", 14);
		OrderReceipt ordReceipt = Processor.calPriceBreakdownCount(products);
		String expected = "MB11 29.849998 [8, 2, 2, 2]";
		String actual = ordReceipt.getCode() + " " + ordReceipt.getTotalAmount() + " " + ordReceipt.getPacks();
		Assert.assertEquals("Positive case VS5:", expected, actual);

	}

	@Test
	public void testNegativeCaseInvalidCount() {
		Products products = new Products("VS5", 12);
		OrderReceipt ordReceipt = Processor.calPriceBreakdownCount(products);
		Assert.assertEquals(ordReceipt, null);

	}
	
	@Test
	public void testNegativeCaseInvalidInput() {
		Products products = null;
		OrderReceipt ordReceipt = Processor.calPriceBreakdownCount(products);
		Assert.assertEquals(ordReceipt, null);

	}
}
