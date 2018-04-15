public class SimulationTwo {
	//Very similar to SimulationOne, but uses a single coin toss for each sim
	//Can be thought of as a 'correlated' model vs the uncorrelated simone
	int totalSeats = 0;
	double[] repSeatOdds;
	double[] demSeatOdds;
	int demCompetitiveSeats;
	public SimulationTwo(double[] repSeatOdds, double[] demSeatOdds, int demCompetitiveSeats){
		this.repSeatOdds = repSeatOdds;
		this.demSeatOdds = repSeatOdds;
		this.demCompetitiveSeats = demCompetitiveSeats;
	}
	public int simulateRep (int seat, double odds){
		if(repSeatOdds[seat] >= odds){//did the democrat beat the odds
			return 1; //democratic win
		}
		else{
			return 0;//republican win
		}
	}
	public int simulateDem (int seat, double odds){
		if(demSeatOdds[seat] <= odds){
			return 1; //democrats fail to hold seat
		}
		else{
			return 0;
		}
	} 
	public int totalSim(double odds){//the odds are generated outside the function for each simulation
		int count = 0;
		for (int j = 0; j < repSeatOdds.length; j++){//add R->D flips
			int winner = simulateRep(j, odds);
			count += winner;
		}
		int count2 = 0;
		for(int j = 0; j < demCompetitiveSeats; j++){//subtract D->R flips
			int winner = simulateDem(j, odds);
			count2 -= winner;
		}
		return count; //return the total democratic seats
	}
}