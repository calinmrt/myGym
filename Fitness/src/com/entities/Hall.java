package com.entities;

import java.time.LocalTime;
import java.util.Hashtable;

public class Hall {
	private String hallName;	
	private Hashtable<Integer,Trainer> trainers;
	private Hashtable<Integer,Equipment> equipments;
	private static final LocalTime openHours = LocalTime.parse("09:00");
	private static final LocalTime closeHours = LocalTime.parse("21:00");
	public String getHallName() {
		return hallName;
	}
	public Hashtable<Integer, Equipment> getEquipments() {
		return equipments;
	}
	public Hashtable<Integer, Trainer> getTrainers() {
		return trainers;
	}
	public  LocalTime getOpenhours() {
		return openHours;
	}
	public  LocalTime getClosehours() {
		return closeHours;
	}
	public void setHallName(String hallName) {
		this.hallName = hallName;
	}
	public void setEquipments(Hashtable<Integer, Equipment> equipments) {
		this.equipments = equipments;
	}
	public void setTrainers(Hashtable<Integer, Trainer> trainers) {
		this.trainers = trainers;
	}

	

}