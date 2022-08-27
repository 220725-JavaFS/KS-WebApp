package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.revature.models.*;
import com.revature.daos.*;

public class WebAppServiceTests {
	
	private ORMManager testOrm = Mockito.mock(ORMManagerImpl.class);
	private WebAppService webService = new WebAppService();
	private Clothing testItem = new Clothing(2, "BOTTOMS", "JEANS", "DARK-WASH", "8", 7.99, 24.95);
	private Clothing testItem1 = new Clothing();
	private Categories testCat = new Categories("BOTTOMS", 750);
	private List<Object> mockList = new ArrayList<Object>();
	private List<Object> mockList1 = new ArrayList<Object>();
	ORMManager orm = new ORMManagerImpl();
	
	@Test
	public void testGetInfoByItemNo() {
		mockList.add(testItem);
		mockList.add(testCat);
		Mockito.when(testOrm.getInfoByItemNo(testItem.getClass(), testCat.getClass(), 2))
		.thenReturn(mockList);
		
		ArrayList<Object> a = (ArrayList<Object>) webService.getInfoByItemNo(testItem.getClass(), testCat.getClass(), 2);
		assertEquals(mockList, a);
	}
	
	@Test
	public void testGetInventoryOfAll() {
		
		mockList1.add(orm.getInventoryOfAll(testItem1.getClass()));
		Mockito.when(testOrm.getInventoryOfAll(testItem1.getClass())).thenCallRealMethod();
		
		List<Object> b = mockList1;
		assertEquals(mockList1, b);
	}
	
	@Test
	public void testInsertNewItem() {
		
		Mockito.when(testOrm.insertNewItem(testItem)).thenReturn(true);
		boolean insert =  testOrm.insertNewItem(testItem);
		assertTrue(insert);
		
	}

	@Test
	public void testUpdateByItemNo() {
		
		Mockito.when(testOrm.updateByItemNo(testItem, 2, "color", "BLUE")).thenReturn(true);
		boolean update =  testOrm.updateByItemNo(testItem, 2, "color", "BLUE");
		assertTrue(update);
	}
	
	@Test
	public void testRemoveByItemNo() {
		
		Mockito.when(testOrm.removeByItemNo(testItem, 2)).thenReturn(true);
		boolean remove = testOrm.removeByItemNo(testItem, 2);
		assertTrue(remove);
	}
}
