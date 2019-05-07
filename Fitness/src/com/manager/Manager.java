package com.manager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Hashtable;

import com.entities.Equipment;
import com.entities.ExersisePlan;
import com.entities.Hall;
import com.entities.Subscription;
import com.entities.Trainer;
import com.util.CsvUtil;
import com.util.CustomerUtil;
import com.util.ExersisePlanUtil;
import com.util.HallUtil;
import com.util.TrainerUtil;

public class Manager {

	public static Hashtable<String, Hall> halls;
	static {
		halls = CsvUtil.getHalls();
		System.out.println(halls.keySet());
	}

	public static void main(String[] args) throws IOException {
		HallUtil.displayHall(halls.get("lotusGym"));
		CustomerUtil.displayCustomers();
		ExersisePlanUtil.displayExersisePlans();
		// t.updateExistingPlan(1,e, 60);
		// ExersisePlanUtil.displayExersisePlans();

		Hall hall = halls.get("lotusGym");
		Trainer t = hall.getTrainers().get(2);
		Equipment e = hall.getEquipments().get(1);
		ExersisePlan p = ExersisePlanUtil.getPlan(1);
		LocalDateTime app = LocalDateTime.of(2019, 05, 20, 15, 00);

		// System.out.println(app);
		CustomerUtil.getCustomer(1).insertNewSubscription(hall, t, p, app);
		// CsvUtil.getSubscriptionsForDate(LocalDate.of(2019, 05, 21), "roseGym");
		//System.out.println(Arrays.toString(p.getEquipmentAndTime().get(1)));
		
	}

}
