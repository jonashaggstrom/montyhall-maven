package com.jh.montyhall;

import com.jh.montyhall.conf.AppConfig;
import com.jh.montyhall.participant.MontyHallContestant;
import com.jh.montyhall.participant.MontyHallContestant.PType;
import com.jh.montyhall.sim.MontyHallSim;

public class MontyHall {

	// This program simulates the Monty Hall Problem
	// You can select the number of boxes used and how many iterations to run in
	// config.properties
	// There are 3 types of participants in the show,
	// one who does not change selection one that changes box every time and one
	// that changes his last guess.

	public static void main(String[] args) {

		// Create an instance of AppConfig
		new AppConfig();
		// Host Simulator
		MontyHallSim sim = new MontyHallSim();

		// participant who is not changing box
		MontyHallContestant pSolid = new MontyHallContestant(PType.Solid);

		// participant who is changing box
		MontyHallContestant pChanger = new MontyHallContestant(PType.Changer);

		// participant who is changing his last decision
		MontyHallContestant pLastMinuteChanger = new MontyHallContestant(PType.LastMinute);
		// Simulate iterations
		Integer pISolidWins = 0;
		Integer pChangerWins = 0;
		Integer pLastChangeWins = 0;
		for (int i = 0; i < AppConfig.iterations; i++) {
			// start a new comp for iStay
			sim.initShow();

			try {
				// If pISolidWins competition add 1 win
				pISolidWins += sim.compete(pSolid).compareTo(false);
			} catch (Exception e) {
				System.err.println("Player couldn't choose, exception: " + e.getMessage());
			}

			// start a new comp for Changer
			sim.initShow();
			try {
				pChangerWins += sim.compete(pChanger).compareTo(false);
			} catch (Exception e) {
				System.err.println("Player couldn't choose, exception: " + e.getMessage());
			}

			// start a new comp for lastMinuteChanger
			sim.initShow();
			try {
				pLastChangeWins += sim.compete(pLastMinuteChanger).compareTo(false);
			} catch (Exception e) {
				System.err.println("Player couldn't choose, exception: " + e.getMessage());
			}
		}
		printStats(pISolidWins, pChangerWins, pLastChangeWins);

	}

	private static void printStats(Integer pFixedWins, Integer pChangeWins, Integer pLastChange) {
		float fixedWinPercent = 100.0f * pFixedWins / AppConfig.iterations;
		float changedWinPercent = 100.0f * pChangeWins / AppConfig.iterations;
		float lastMinuteChangeWinPercent = 100.0f * pLastChange / AppConfig.iterations;
		System.out.println("Participant who stays with guess: " + fixedWinPercent + "% wins(" + pFixedWins + ") in "
				+ AppConfig.iterations + " iterations of show with " + AppConfig.numberOfBoxes + " boxes");
		System.out.println("Participant who changes every decision: " + changedWinPercent + "% wins(" + pChangeWins
				+ ") in " + AppConfig.iterations + " iterations of show with " + AppConfig.numberOfBoxes + " boxes");
		System.out.println("Participant who changes his last decision: " + lastMinuteChangeWinPercent + "% wins("
				+ pLastChange + ") in " + AppConfig.iterations + " iterations of show with " + AppConfig.numberOfBoxes
				+ " boxes");
	}

	public int correctGuess(Boolean correct) {

		return correct.compareTo(false);
	}
}
