package com.mbv.common.utils;

public class DateUtils {
	
	private static long date;
	
	public static void start(){
		date = System.currentTimeMillis();
	}
	
	public static long stop(){
		return System.currentTimeMillis()-date;
	}
}
