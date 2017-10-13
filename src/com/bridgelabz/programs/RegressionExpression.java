package com.bridgelabz.programs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegressionExpression {

	public static void main(String args[]) {
		String name="shriteej";
		Pattern pattern=Pattern.compile("[a-z]");
		Matcher m=pattern.matcher(name);
		boolean bool=m.matches();
		System.out.println(bool);
		System.out.println(Pattern.matches("[a-zA-Z]", name));
	}
}
