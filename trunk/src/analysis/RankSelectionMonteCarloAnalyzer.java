/*
 * Copyright (C) 2008 by Eric Nadeau (nado18@gmail.com)
 * 
 * Distributed under the terms of the GPL
 * 	See LICENSE.TXT or http://www.gnu.org/copyleft/gpl.html
 */ 
package analysis;

import java.util.*;

import data.*;

public class RankSelectionMonteCarloAnalyzer extends ProbabilisticAnalyzer {

	public RankSelectionMonteCarloAnalyzer(int firstToLastProbabilityRatio) {
		super(firstToLastProbabilityRatio);
	}

	@Override
	public double getVelocity(List<Estimate> history, int k) {
		double r = Math.random();
		double s = 0;
		for(int i=0;;i++)
		{
			s += probability( i, k, history.size() );
			if( s >= r )
			{
				double result = history.get(i).getVelocity();
				return result;
			}
		}
	}
}
