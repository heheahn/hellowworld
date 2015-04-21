package com.heheahn.chapter2;

import java.sql.SQLException;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DUserDao dUser = new DUserDao();
		dUser.deleteAll();
		
		User user = new User();
		user.setId("2");
		user.setName("test");
		user.setPassword("123");
		
		dUser.add(user);
		
		User user2 = dUser.get("2");
		System.out.println(user2.getName());		
	}
}
