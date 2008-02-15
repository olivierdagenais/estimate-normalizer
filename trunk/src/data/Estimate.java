/*
 * Copyright (C) 2008 by Eric Nadeau (nado18@gmail.com)
 * 
 * Distributed under the terms of the GPL
 * 	See LICENSE.TXT or http://www.gnu.org/copyleft/gpl.html
 */ 
package data;

import java.util.*;

public class Estimate implements Comparable<Estimate> {
	private Calendar date;
	private double expected;
	private double actual;

	public Estimate(double anExpected) {
		expected = anExpected;
	}

	public Estimate(Calendar aDate, double anExpected, double anActual) {
		date = aDate;
		expected = anExpected;
		actual = anActual;
	}

	public double getVelocity() {
		double result = actual / expected;
		return result;
	}

	public double getExpected() {
		return expected;
	}

	public int compareTo(Estimate o) {
		int result = date.compareTo(o.date);
		return result;
	}
}
