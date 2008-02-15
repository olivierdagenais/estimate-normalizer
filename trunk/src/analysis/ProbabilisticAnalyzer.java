/*
 * Copyright (C) 2008 by Eric Nadeau (nado18@gmail.com)
 * 
 * Distributed under the terms of the GPL
 * 	See LICENSE.TXT or http://www.gnu.org/copyleft/gpl.html
 */ 
package analysis;

public abstract class ProbabilisticAnalyzer extends Analyzer {

	public ProbabilisticAnalyzer(int firstToLastProbabilityRatio) {
		super(firstToLastProbabilityRatio);
	}

	@Override
	public final boolean isProbabilistic() {
		return true;
	}

}
