package com.jiadao.util;


public class UserUtil {
	
	private UserUtil(){ }
	
	private static final ThreadLocal<User> USER_INFO = new ThreadLocal<User>();
 
	public static void set(User userInfo) {		
		USER_INFO.set(userInfo);
	}
	
	
	public static User get() {
		return USER_INFO.get();
	}
	
	public static void remove() {
		USER_INFO.remove();
	}

}