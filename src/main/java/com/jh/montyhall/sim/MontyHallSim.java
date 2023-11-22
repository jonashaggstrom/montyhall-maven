package com.jh.montyhall.sim;

import java.util.List;

import com.jh.montyhall.utils.Helpers;
import com.jh.montyhall.conf.AppConfig;
import com.jh.montyhall.participant.MontyHallContestant;

public class MontyHallSim {
	//there are three boxes[1,2,3] and one contains a prize

	int prizeBox;
	List<Integer> boxList;
	public MontyHallSim(){
		initShow();
	}
	
	public void initShow() {
		if(AppConfig.debugoutput)
			System.out.println("initializing show..");
		prizeBox=Helpers.getRandomBox();
		boxList=Helpers.getBoxes();
	}
	
	public Boolean compete(MontyHallContestant p) throws Exception {
		int guess;
		while(boxList.size()>2) {
			
			//get the guess from participant
			guess=p.guess(boxList);
			if(AppConfig.debugoutput)
				System.out.println("Player: " + p.getName() + " noOfBoxes: " + boxList.size() + " guess: " + guess);
			removeEmtyBox(guess);
		}
		guess=p.guess(boxList);
		if(AppConfig.debugoutput)
			System.out.println("Player: " + p.getName() + " noOfBoxes: " + boxList.size() + " last guess: " + guess + " prizeBox " + prizeBox);
		return isPrize(guess);
	}
	//Helpers
	private void removeEmtyBox(int playersBox) {
		boxList.stream()
		        .filter(e -> e!=playersBox && e!=prizeBox)
		        .findFirst()
		        .ifPresent(boxList::remove);
	}
	private Boolean isPrize(int guess) {
		return guess==prizeBox;
	}
}
