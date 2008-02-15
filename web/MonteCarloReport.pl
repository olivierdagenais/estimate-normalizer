#!/usr/bin/perl

my @historical = 
(
	[11.6, 16.9],
	[11.2, 17.4],
	[12.5, 17.6],
	[7.9, 11.5],
	[10, 15.8],
	[14.5, 21],
	[13.1, 20],
	[8.2, 11.9],
	[5.6, 8.4],
	[6.7, 10.4]
);

my @estimates = 
(
	13.6,
	13.2,
	6.2,
	5.6,
	10.3
);

# Pick a random historical entry and return its velocity
sub randomVelocity
{
	my $row = $historical[ rand(@historical) ];
	my $estimate = $row->[0];
	my $actual = $row->[1];
	$actual / $estimate;
}

# Runs one Monte-Carlo simulation on the above data
sub randomProjectEstimate
{
	my $sum = 0;
	map {
		$sum += $_ * randomVelocity;
	} @estimates;
	$sum;
}

# Runs many simulations and analyzes them
sub analyzeSimulations
{
	my @completion;
	my $max = 0;
	my $min = 2**31;
	for(my $i=0; $i<1000; $i++)
	{
		$completion[$i] = randomProjectEstimate;
		$max = $completion[$i] if $completion[$i] > $max;
		$min = $completion[$i] if $completion[$i] < $min;
	}

	my $buckets = 10.0;
	for(my $x=$min; $x<$max; $x+=( ($max-$min) / $buckets ))
	{
		my $count = 0;
		for(my $i=0; $i<@completion; $i++)
		{
			$count++ if $completion[$i] <= $x;
		}
		printf(
			"%d percent completed after %f time units\r\n", 
			$count * 100 / @completion, 
			$x
		);
	}
}

analyzeSimulations;

