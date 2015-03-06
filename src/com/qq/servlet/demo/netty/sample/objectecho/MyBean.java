package com.qq.servlet.demo.netty.sample.objectecho;

import java.io.Serializable;

public class MyBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2308474473716359859L;
	
	
	
	String name="ankerdiao";
	long age=30;
	
	
	
	@Override
	public String toString() {
		return "MyBean [name=" + name + ", age=" + age + "]";
	}
	
	
	
}
