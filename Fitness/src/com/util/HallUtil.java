package com.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import com.entities.Equipment;
import com.entities.Hall;
import com.entities.Trainer;

public class HallUtil {
	static Hall getHall(String name, String[] tr, String[] eq) throws IOException {
		Hall h = new Hall();
		h.setHallName(name);
		Hashtable<Integer, Trainer> trainers = new Hashtable<Integer, Trainer>();
		Hashtable<Integer, Equipment> equipments = new Hashtable<Integer, Equipment>();
		for (int i = 0; i < tr.length; i++) {
			trainers.put(Integer.parseInt(tr[i]), TrainerUtil.getTrainer(tr[i]));
		}
		for (int i = 0; i < eq.length; i++) {
			equipments.put(Integer.parseInt(eq[i]), EquipmentUtil.getEquipment(eq[i]));
		}
		h.setTrainers(trainers);
		h.setEquipments(equipments);
		return h;

	}

	public static void displayHall(Hall h) {
		System.out.println("hall name is:        "+h.getHallName());		
		System.out.println("trainers are:        ");
		h.getTrainers().forEach((k,v)->System.out.println("                     "+k+"-"+v.getTrainerName()+"-at work from: "+v.getStartWorkingHours()+" to "+v.getStartWorkingHours().plusHours(8)));		
		System.out.println("equipments:          ");
		h.getEquipments().forEach((k,v)->System.out.println("                     "+k+"-"+v.getEquipmentName()));		
		System.out.println("\nHall open hours are: " + h.getOpenhours() + "-" + h.getClosehours());

	}

}
