package com.hexed.process;

import org.junit.Test;

import com.hexed.model.OrderReceipt;
import com.hexed.model.Products;

import junit.framework.Assert;

public class ProcessOrderTest {

	@Test
	public void testPositiveOrders() {
		Products products =  new Products("VS5", 13);
     	OrderReceipt ordReceipt = Processor.calPriceBreakdownCount(products);
     	ordReceipt.toString();
     	String expected="VS5 24.97 [5, 5, 3]";
     	String actual=ordReceipt.getCode()+" "+ordReceipt.getTotalAmount()+" "+ordReceipt.getPacks();
     	Assert.assertEquals("Positive case VS5:", expected, actual);
		
	}
}
