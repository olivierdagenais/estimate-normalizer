/*
 * Copyright (C) 2008 by Eric Nadeau (nado18@gmail.com)
 * 
 * Distributed under the terms of the GPL
 * 	See LICENSE.TXT or http://www.gnu.org/copyleft/gpl.html
 */ 
package analysis;

import java.util.*;

import data.*;

public class AverageAnalyzer extends DeterministicAnalyzer {

	public AverageAnalyzer(int firstToLastProbabilityRatio) {
		super(firstToLastProbabilityRatio);
	}

	@Override
	public double getVelocity(List<Estimate> history, int k) {
		double avg = 0;
		int total = 0;
		for (Estimate estimate : history) {
			avg += estimate.getVelocity();
			total++;
		}
		avg /= total;
		return avg;
	}
}
