/*
 * Copyright (C) 2008 by Eric Nadeau (nado18@gmail.com)
 * 
 * Distributed under the terms of the GPL
 * 	See LICENSE.TXT or http://www.gnu.org/copyleft/gpl.html
 */ 
package analysis;

import java.util.*;

import data.*;

public class MonteCarloAnalyzer extends ProbabilisticAnalyzer {

	public MonteCarloAnalyzer(int firstToLastProbabilityRatio) {
		super(firstToLastProbabilityRatio);
	}

	@Override
	public double getVelocity(List<Estimate> history, int k) {
		int i = (int) (Math.random() * history.size());
		double result = history.get(i).getVelocity();
		return result;
	}
}
