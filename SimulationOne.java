public class SimulationOne {
	//create a method that creates a 1 or 0 for each seat (random)
	//add seats
	int totalSeats = 0;
	double[] repSeatOdds;
	double[] demSeatOdds;
	int demCompetitiveSeats;
	public SimulationOne(double[] repSeatOdds, double[] demSeatOdds, int demCompetitiveSeats){
		this.repSeatOdds = repSeatOdds;
		this.demSeatOdds = repSeatOdds;
		this.demCompetitiveSeats = demCompetitiveSeats;
	}
	public int simulateRep (int seat){
		double rando = Math.random();//random odds
		if(repSeatOdds[seat] >= rando){//did the democrat beat the odds
			return 1; //democratic win
		}
		else{
			return 0;//republican win
		}
	}
	public int simulateDem (int seat){
		double rando = Math.random();
		if(demSeatOdds[seat] < rando){
			return 1; //democrats fail to hold seat
		}
		else{
			return 0;
		}
	} 
	public int totalSim(){
		int count = 0;
		for (int j = 0; j < repSeatOdds.length; j++){//add R->D flips
			int winner = simulateRep(j);
			count += winner;
		}
		int count2 = 0;
		for(int j = 0; j < demCompetitiveSeats; j++){//subtract D->R flips
			int winner = simulateDem(j);
			count2 -= winner;
		}
		return count; //return the total democratic seats
	}
}
