package com.jh.montyhall.participant;
import java.util.List;

import com.jh.montyhall.data.Helpers;
public class MontyHallContestant implements Contestant {

	public enum PType {
		Solid,
		Changer,
		LastMinute
	};
	
	private PType pType;
	private Integer lastGuess;
	private String name;
	
	public MontyHallContestant(PType type) {
		pType=type;
		lastGuess=Helpers.getRandomBox();
		name=pType.equals(PType.Changer)?"pChanger!":pType.equals(PType.LastMinute)?"pLastMinute":"pIStay";
	}


	public Integer guess(List<Integer> boxList) throws Exception{
		switch (pType) {
			case Solid:
				return lastGuess;
			case Changer:
				lastGuess= Helpers.pickANewBox(boxList, lastGuess);
				return lastGuess;
			case LastMinute:
				lastGuess= boxList.size()<=2 ? Helpers.pickANewBox(boxList, lastGuess):lastGuess;
				return lastGuess;
			default:
				throw new Exception("No such enum");
		}
	}
	
	public String getName() {
		return name;
	}
}
