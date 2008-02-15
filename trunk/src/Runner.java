/*
 * Copyright (C) 2008 by Eric Nadeau (nado18@gmail.com)
 * 
 * Distributed under the terms of the GPL
 * 	See LICENSE.TXT or http://www.gnu.org/copyleft/gpl.html
 */ 
import java.util.*;

import analysis.*;
import data.*;

public class Runner 
{
	private Vector<Estimate> estimates = new Vector<Estimate>();
	private Vector<Estimate> history = new Vector<Estimate>();

	public Runner() 
	{
		estimates.add( new Estimate( 13.629632047 ) );
		estimates.add( new Estimate( 13.225809549 ) );
		estimates.add( new Estimate( 6.1810914991 ) );
		estimates.add( new Estimate( 5.6048259375 ) );
		estimates.add( new Estimate( 10.309531072 ) );

		history.add( new Estimate(getJuly(1), 11.627370979, 10.598694079) );
		history.add( new Estimate(getJuly(2), 11.224044015, 12.11983836) );
		history.add( new Estimate(getJuly(3), 12.507756812, 12.154407321) );
		history.add( new Estimate(getJuly(4), 7.868743801, 7.8297623926) );
		history.add( new Estimate(getJuly(5), 9.9770627799, 9.367126898) );
		history.add( new Estimate(getJuly(6), 14.494142346, 13.39286244) );
		history.add( new Estimate(getJuly(7), 13.050406677, 12.877894729) );
		history.add( new Estimate(getJuly(8), 8.1850159788, 8.4309122992) );
		history.add( new Estimate(getJuly(9), 5.5635564218, 5.1192791935) );
		history.add( new Estimate(getJuly(10), 6.69278137, 7.2197459619) );
	}

	private Calendar getJuly(int n) 
	{
		Calendar date = GregorianCalendar.getInstance();
		date.set(2004, 07, n);
		return date;
	}

	private void calculate(DeterministicAnalyzer a) 
	{
		double computerEstimate = a.getEstimate(estimates, history);

		double userEstimate = 0;
		for (Estimate estimate : estimates) {
			userEstimate += estimate.getExpected();
		}

		System.out.println(
			String.format(
				"User thinks it can be done in %f days. Computer says %f days.",
				userEstimate, 
				computerEstimate
			)
		);
	}

	private void simulate(ProbabilisticAnalyzer a) 
	{
		int runs = 1000;
		int dataPoints = 10;

		double[] total = new double[runs];
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		for (int i = 0; i < runs; i++) {
			double estimate = a.getEstimate(estimates, history);
			total[i] = estimate;

			if (estimate > max) {
				max = estimate;
			}

			if (estimate < min) {
				min = estimate;
			}
		}

		for (int i = 0; i <= dataPoints; i++) {
			double x = min + i * (max - min) / dataPoints;
			int done = 0;
			for (int j = 0; j < runs; j++) {
				if (total[j] <= x) {
					done++;
				}
			}

			System.out.println(
				String.format(
					"Done within %f days: %d %%.", 
					x,
					Math.round(
						done * 100 / runs
					)
				)
			);
		}
	}

	private void process(Analyzer a) 
	{
		if( a.isProbabilistic() )
		{
			simulate( (ProbabilisticAnalyzer) a );
		}
		else
		{
			calculate( (DeterministicAnalyzer) a );
		}
	}

	public static void main(String[] args) 
	{
		Runner r = new Runner();
		int ratio = 5;
		
		System.out.println("=== Average ===");
		r.process( new AverageAnalyzer(ratio) );
		System.out.println();
		
		System.out.println("=== Weighted Average ===");
		r.process( new WeightedAverageAnalyzer(ratio) );
		System.out.println();
		
		System.out.println("=== Monte Carlo ===");
		r.process( new MonteCarloAnalyzer(ratio) );
		System.out.println();
		
		System.out.println("=== Rank Selection Monte Carlo ===");
		r.process( new RankSelectionMonteCarloAnalyzer(ratio) );
	}
}
