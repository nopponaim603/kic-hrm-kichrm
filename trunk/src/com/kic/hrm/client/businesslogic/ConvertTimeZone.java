package com.kic.hrm.client.businesslogic;

import java.util.Date;

public class ConvertTimeZone {
	public static Date ClineTimeZoneToBangkok(Date input) {
		return new Date(input.getTime() - 25200000L);
	}
}
