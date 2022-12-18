package com.springboot.lombok;

public class Test {

	public static void main(String[] args) {
		long money = 33l;
				
		long money25 = money / 25;
		money = money % 25;
		System.out.println("25 : " + money25);
		long money10 = money / 10;
		money = money % 10;
		System.out.println("10 : " + money10);
		long money5 = money / 5;
		money = money % 5;
		System.out.println("5 : " +money5);
		
		System.out.println("1 : " +money);
		
		
				
	}
}
