/*
 * Copyright (C) 2008 by Eric Nadeau (nado18@gmail.com)
 * 
 * Distributed under the terms of the GPL
 * 	See LICENSE.TXT or http://www.gnu.org/copyleft/gpl.html
 */ 
package analysis;

import java.util.*;

import data.*;

public abstract class Analyzer {
	
	private int firstToLastProbabilityRatio;
	
	public Analyzer(int aFirstToLastProbabilityRatio)
	{
		firstToLastProbabilityRatio = aFirstToLastProbabilityRatio;
	}
	
	/**
	 * Linear probability function used for rank selection.
	 * 
	 * Interesting properties:
	 * <ul>
	 * <li>The sum of P(0) to P(n-1) is 1
	 * <li>r*P(n-1) = P(0)
	 * </ul>
	 * 
	 * @param x The member for which to check the probability of being selected.
	 * @param r The probability ration between the first and last members.
	 * @param n The number of members in the population.
	 * @return The probability of member 'x' being selected out of 'n' members with a 'r' ratio between the first and last members.
	 */
	protected double probability(double x, int r, int n)
	{
		double y = 
			(
				2 * ( r*n - (r-1)*x )
			) 
			/ 
			(
				( n - 1 ) 
				* 
				(
					n * ( r+1 )
					+
					( r-1 )
				)
			);
		
		return y;
	}
	
	private double getEstimate(double estimatedTime, List<Estimate> history, int k)
	{
		double velocity = getVelocity( history, k );
		double result = velocity * estimatedTime;
		return result;
	}
	
	public double getEstimate(Collection<Estimate> estimates, List<Estimate> history)
	{
		int r = firstToLastProbabilityRatio;
		Collections.sort(history);
		
		double sum = 0;
		for(Estimate estimate: estimates)
		{
			sum += getEstimate( estimate.getExpected(), history, r );
		}
		return sum;
	}
	
	public abstract boolean isProbabilistic();
	
	public abstract double getVelocity(List<Estimate> history, int k);
}
