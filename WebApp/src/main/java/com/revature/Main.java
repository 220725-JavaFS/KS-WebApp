package com.revature;

import com.revature.daos.ORMManager;
import com.revature.daos.ORMManagerImpl;
import com.revature.models.Categories;
import com.revature.models.Clothing;

public class Main {

	public static void main(String[] args) {
		System.err.println("Test: ");
		
		ORMManager orm = new ORMManagerImpl();
		Clothing testItem = new Clothing("TOPS", "HOODY", "GREY", "XM", 4.99, 19.99);
		Clothing testItem1 = new Clothing("BOTTOMS", "DISTRESSED JEANS", "WHT", "00", 17.99, 44.95);
		Clothing testItem2 = new Clothing("SHOES", "HIGH HEELS", "BLCK/PNK", "7", 10.99, 34.99);
	
		Class<?> c1 = testItem.getClass();
		Categories testCat = new Categories();
		Class<?> c2 = testCat.getClass();
		
		System.out.println(orm.getInfoByItemNo(c1, c2, 20));
		System.out.println(testItem2);
		
	}

}
