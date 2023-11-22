package com.jh.montyhall.participant;

import java.util.List;

public interface Contestant {

	abstract Integer guess(List<Integer> boxList) throws Exception;
}
