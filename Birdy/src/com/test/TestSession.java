package com.test;

import com.services.session.SessionServices;

public class TestSession {

	public static void main(String[] args) {
		System.out.println(SessionServices.randomAlphaNumeric(32).length());
	}

}
