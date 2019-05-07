package com.entities;

import java.time.LocalTime;

import com.util.CsvUtil;

public class Trainer {
	private int trainerId;
	private String trainerName;
	private LocalTime startWorkingHours;
	public Trainer(int trainerId, String trainerName, LocalTime startWorkingHours) {
		this.trainerId = trainerId;
		this.trainerName = trainerName;
		this.startWorkingHours = startWorkingHours;
	}
	
	public int getTrainerId() {
		return trainerId;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public LocalTime getStartWorkingHours() {
		return startWorkingHours;
	}

	public void insertNewExersisePlan(Equipment e,int timeInMinutes) {
		String lineToWrite=CsvUtil.getAvailableId("exersisePlans.csv")+","+e.getEquipmentId()+","+timeInMinutes;
		CsvUtil.writeLineInFile(lineToWrite, "exersisePlans.csv");
		
	}
	public void updateExistingPlan(int id,Equipment e,int timeInMinutes) {
		String lineToWrite=""+id+","+e.getEquipmentId()+","+timeInMinutes;
		CsvUtil.writeLineInFile(lineToWrite, "exersisePlans.csv");
	}

	
}
