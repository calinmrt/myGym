package com.entities;

import java.io.IOException;
import java.time.LocalDateTime;

import com.manager.Manager;
import com.util.CustomerUtil;
import com.util.ExersisePlanUtil;
import com.util.HallUtil;

public class Subscription {
	private Hall hall;
	private LocalDateTime appointment;
	private Customer customer;
	private Trainer trainer;
	private ExersisePlan exercisePlan;

	public Subscription(Hall hall, LocalDateTime appointment, Customer customer, Trainer trainer,
			ExersisePlan exercisePlan) {
		this.hall = hall;
		this.appointment = appointment;
		this.customer = customer;
		this.trainer = trainer;
		this.exercisePlan = exercisePlan;
	}
	public Subscription(String[] line) throws NumberFormatException, IOException {
		this.hall=Manager.halls.get(line[0]);
		this.appointment=LocalDateTime.parse(line[1]);
		this.customer=CustomerUtil.getCustomer(Integer.parseInt(line[2]));
		this.trainer=this.hall.getTrainers().get(Integer.parseInt(line[3]));
		this.exercisePlan=ExersisePlanUtil.getPlan(Integer.parseInt(line[4]));
		
	}
	public Hall getHall() {
		return hall;
	}
	public LocalDateTime getAppointment() {
		return appointment;
	}
	public Customer getCustomer() {
		return customer;
	}
	public Trainer getTrainer() {
		return trainer;
	}
	public ExersisePlan getExercisePlan() {
		return exercisePlan;
	}
	

}
