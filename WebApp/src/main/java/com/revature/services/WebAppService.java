package com.revature.services;

import java.util.List;

import com.revature.daos.ORMManager;
import com.revature.daos.ORMManagerImpl;
import com.revature.models.Categories;
import com.revature.models.Clothing;

public class WebAppService {
	
	private ORMManager orm = new ORMManagerImpl();
	
	public void insertNewItem(Object o) {
		
		orm.insertNewItem(o);
		
		System.out.println("Your clothing item has been added. Thank you.");
	}

	public <T> List<T> getInfoByItemNo(Class<? extends com.revature.models.Clothing> class1, Class<? extends Categories> class2, int itemNum){
		
		return orm.getInfoByItemNo(class1, class2, itemNum);
	}
	
	public <Clothing> List<Clothing> getInventoryOfAll(Class<Clothing> a){
		
		return orm.getInventoryOfAll(a);
	}
	
	public void updateByItemNo(Object o, int itemNum, String column, String value) {
		
		orm.updateByItemNo(o, itemNum, column, value);
		
		System.out.println("Your clothing item has been updated. Thank you.");
	}
	
	public void removeByItemNo(Object o, int itemNum) {
		
		orm.removeByItemNo(o, itemNum);
		
		System.out.println("Your item has been removed. Thank you.");
	}

}