/*
 * Copyright (C) 2008 by Eric Nadeau (nado18@gmail.com)
 * 
 * Distributed under the terms of the GPL
 * 	See LICENSE.TXT or http://www.gnu.org/copyleft/gpl.html
 */ 
package analysis;

import java.util.*;

import data.*;

public class WeightedAverageAnalyzer extends DeterministicAnalyzer {

	public WeightedAverageAnalyzer(int firstToLastProbabilityRatio) {
		super(firstToLastProbabilityRatio);
	}

	@Override
	public double getVelocity(List<Estimate> history, int k) {
		double sum = 0;
		int i = 0;
		for (Estimate estimate : history) {
			sum += 
				probability( i++, k, history.size() )
				* estimate.getVelocity();
		}
		return sum;
	}
}
