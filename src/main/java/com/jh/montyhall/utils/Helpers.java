package com.jh.montyhall.utils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.jh.montyhall.conf.AppConfig;

public class Helpers {

	public static int getRandomBox() {
		return new Random().nextInt(AppConfig.highestBox - AppConfig.lowestBox + 1) + AppConfig.lowestBox;
	}

	public static List<Integer> getBoxes() {
		return IntStream.rangeClosed(AppConfig.lowestBox, AppConfig.highestBox).boxed().collect(Collectors.toList()); 
	}
	
	public static Integer pickANewBox(List<Integer> boxList, int lastGuess) throws NoSuchElementException{
		return boxList.stream()
        .filter(e -> e!=lastGuess)
        .findFirst().get();
	}
}
