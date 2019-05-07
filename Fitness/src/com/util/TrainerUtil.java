package com.util;

import java.io.IOException;
import java.time.LocalTime;

import com.entities.Equipment;
import com.entities.Trainer;

public class TrainerUtil {
	static Trainer getTrainer(String id) throws IOException {
		String[] memo=CsvUtil.retrieveMemoForId(id,"trainers.csv").split(",");		
		Trainer t=new Trainer(Integer.parseInt(memo[0]),memo[1],LocalTime.parse(memo[2]));
		return t;
	}
	

}
