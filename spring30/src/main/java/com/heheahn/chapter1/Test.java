package com.heheahn.chapter1;

import java.sql.SQLException;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = new UserDao();
		
		User user = new User();
		user.setId("1");
		user.setName("hehe");
		user.setPassword("123124");
		
		dao.add(user);
		
		System.out.println(user.getId() + " 생성!!!!");
		
		User user2 = dao.get("1");
		System.out.println(user2.getName());
	}
}
