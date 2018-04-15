import java.util.*;
import java.util.Random;

public class main{
	public static void main(String[] Args){
	System.out.println("==================================================================================");
	System.out.println("========================Welcome to the Election Simulator!========================");
	System.out.println("==================================================================================");
	System.out.println();
	Scanner scan = new Scanner(System.in);
	System.out.println("How many U.S. House seats currently held by the REPUBLICAN PARTY do you think will be competitive this year?");
	int repCompetitiveSeatNumber = scan.nextInt();
	System.out.println("How many U.S. House seats currently held by the DEMOCRATIC PARTY do you think will be competitive this year?");
	int demCompetitiveSeatNumber = scan.nextInt();
	System.out.println("==================================================================================");
	System.out.println("There are two simulation types: uncorrelated and correlated.");
	System.out.println("An uncorrelated simulation basically tosses a die for each seat. The correlated simulation just tosses one per simulation.");
	System.out.println("The correlated system might better represent a wave election.");
	System.out.println("Which simulation type would you like to run? 1) Non-correlated, 2) Correlated");
	int simType = scan.nextInt();
	System.out.println("==================================================================================");
	if(simType != 1 && simType != 2){
		System.out.println("Sorry, didn't recognize that number.");
		System.exit(0);
	}
	if (repCompetitiveSeatNumber <= 23){
		System.out.println("With that many competitive seats, the REPUBLICAN PARTY is certain to hold the House.");
	}
	else{
		double[] repSeatOdds = new double[repCompetitiveSeatNumber];
		double[] demSeatOdds = new double[demCompetitiveSeatNumber];
		System.out.println("The program is now going to ask you about the odds of individual seats.");
		System.out.println("As the program prompts you, please insert number of seats for each likelihood, starting with REPUBLICAN-held seats.");
		//The percent odds of each rating are taken from https://fivethirtyeight.com/features/are-democrats-senate-chances-overrated/
		//The rating is given its appropriate percentage for Democrats below
		System.out.println("How many REPUBLICAN seats do you think are LIKELY DEMOCRAT?");
		int rlikelyDem = scan.nextInt();
		int i;
		for(i = 0; i < rlikelyDem; i++){
			repSeatOdds[i] = .85;
		}
		System.out.println("How many REPUBLICAN seats do you think are LEAN DEMOCRAT?");
		int rleanDem = scan.nextInt();
		for(; i < rlikelyDem+rleanDem; i++){
			repSeatOdds[i] = .75;
		}
		System.out.println("How many REPUBLICAN seats do you think are TOSSUPS?");
		int rtossup = scan.nextInt();
		for(; i < rlikelyDem + rleanDem + rtossup; i++){
			repSeatOdds[i] = .5;
		}
		System.out.println("How many REPUBLICAN seats do you think are LEAN REPUBLICAN?");
		int rleanRep = scan.nextInt();
		for(; i < rlikelyDem+rleanDem+rtossup+rleanRep; i++){
			repSeatOdds[i] = .25;
		}
		System.out.println("We will assume the remaining REPUBLICAN seats are LIKELY REPUBLICAN.");
		for(; i < repCompetitiveSeatNumber; i++){
			repSeatOdds[i] = .15;
		}
		System.out.println("==================================================================================");
		System.out.println("We will now move on to DEMOCRATIC-held seats.");
		System.out.println("How many DEMOCRATIC seats do you think are LIKELY DEMOCRAT?");
		int dlikelyDem = scan.nextInt();
		for(i = 0; i < dlikelyDem; i++){
			demSeatOdds[i] = .85;
		}
		System.out.println("How many DEMOCRATIC seats do you think are LEAN DEMOCRAT?");
		int dleanDem = scan.nextInt();
		for(; i < dlikelyDem+dleanDem; i++){
			demSeatOdds[i] = .75;
		}
		System.out.println("We will assume all remaining DEMOCRATIC seats are TOSSUPS.");
		for(; i < demCompetitiveSeatNumber; i++){
			demSeatOdds[i] = .5;
		}
		System.out.println("==================================================================================");
		System.out.println("How many simulations would you like to run (we recommend 10000+)");
		int simNumber = scan.nextInt();
		int demWins = 0;
		int tally =0;
		if(simType == 1){
			SimulationOne sim = new SimulationOne(repSeatOdds, demSeatOdds, demCompetitiveSeatNumber);
			for(i = 0; i < simNumber; i++){//sum democratic wins
				int winner = sim.totalSim();
				if(winner >= 24){
					demWins++;
				}
				tally += winner;
			}
		}
		if(simType == 2){
			SimulationTwo sim = new SimulationTwo(repSeatOdds, demSeatOdds, demCompetitiveSeatNumber);
			for(i = 0; i < simNumber; i++){//sum democratic wins
				double rando = Math.random();
				int winner = sim.totalSim(rando);
				if(winner >= 24){
					demWins++;
				}
				tally += winner;
			}
		}
		double result = (double)demWins/simNumber;
		System.out.println("We estimate the DEMOCRATIC PARTY has a " + result*100 + "% chance of winning the House with these odds.");
		System.out.println("The average number of DEMOCRATIC seats is " + (193 + ((double)tally/simNumber)));
	}
	System.out.println("Thank you for using the Simulator.");
	System.exit(0);
	}
}