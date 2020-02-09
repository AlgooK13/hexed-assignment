package com.hexed.process;

import java.util.Map;

import org.junit.Test;

import com.hexed.model.OrderReceipt;
import com.hexed.model.Products;

import junit.framework.Assert;

public class ProcessOrderTest {

	@Test
	public void testPositiveOrders() {
		Products products = new Products("VS5", 13);
    	Map<Integer, Integer> orderData = Processor.calPriceBreakdownCount(products);
    	OrderReceipt ordReceipt = Processor.genrateOrderBill(products,orderData);
    	
		String expected = "13 VS5 $24.97 [1 X 3 $6.99, 2 X 5 $8.99]";
		Assert.assertEquals("Positive case VS5:", expected, ordReceipt.toString());

	}
	
	@Test
	public void testPositiveOrdersMinimum() {
		Products products = new Products("VS5", 6);
		Map<Integer, Integer> orderData = Processor.calPriceBreakdownCount(products);
    	OrderReceipt ordReceipt = Processor.genrateOrderBill(products,orderData);
    	String expected = "6 VS5 $13.98 [2 X 3 $6.99]";    	
		Assert.assertEquals("Positive case VS5:", expected, ordReceipt.toString());

	}
	
	@Test
	public void testPositiveOrder() {
		Products products = new Products("MB11", 14);
    	Map<Integer, Integer> orderData = Processor.calPriceBreakdownCount(products);
    	OrderReceipt ordReceipt = Processor.genrateOrderBill(products,orderData);
    	String expected = "14 MB11 $54.8 [3 X 2 $9.95, 1 X 8 $24.95]";    	
		Assert.assertEquals("Positive case VS5:", expected, ordReceipt.toString());

	}

	@Test
	public void testNegativeCaseInvalidCount() {
		Products products = new Products("VS5", 9);
		Map<Integer, Integer> orderData = Processor.calPriceBreakdownCount(products);
    	OrderReceipt ordReceipt = Processor.genrateOrderBill(products,orderData);
		Assert.assertEquals(ordReceipt, null);

	}
	
	@Test
	public void testNegativeCaseInvalidInput() {
		Products products = null;
		Map<Integer, Integer> orderData = Processor.calPriceBreakdownCount(products);
    	OrderReceipt ordReceipt = Processor.genrateOrderBill(products,orderData);
		Assert.assertEquals(ordReceipt, null);

	}
}
