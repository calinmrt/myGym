package com.util;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.print.attribute.standard.MediaSize.ISO;

//import org.joda.time.DateTime;
//import org.joda.time.Instant;
//import org.joda.time.Interval;
//import org.joda.time.JodaTimePermission;
//import org.joda.time.convert.InstantConverter;

import com.entities.Customer;
import com.entities.Equipment;
import com.entities.ExersisePlan;
import com.entities.Hall;
import com.entities.Subscription;
import com.entities.Trainer;

public class CustomerUtil {
	public static void displayCustomers() {
		CsvUtil.getCustomers("customers.csv");
	}

	public static Customer getCustomer(int id) throws IOException {
		String[] memo = CsvUtil.retrieveMemoForId(id + "", "customers.csv").split(",");
		Customer c = new Customer(Integer.parseInt(memo[0]), memo[1], memo[2], memo[3]);
		return c;
	}

	public static void insertSubscription(LocalDateTime app, Hall h, Trainer t, ExersisePlan p,int custId) {
		ArrayList<int[]> myEq = p.getEquipmentAndTime();
		ArrayList<UsedEquipment> myPlanEq = new ArrayList<>();
		LocalTime st = LocalTime.from(app);
		for (int[] e : myEq) {
			myPlanEq.add(new UsedEquipment(e[0], st, st.plusMinutes(e[1])));
			st = st.plusMinutes(e[1]);
		}
		// myPlanEq.forEach(u->{System.out.println(u.getEquipmentId()+" "+u.getStart()+"
		// "+u.getEnd());});
		// System.out.println(myPlanEq.get(0).overlaps(myPlanEq.get(1)));

		LocalTime start = LocalTime.from(app);
		LocalTime end = start.plusMinutes(p.planTotalTime());
		System.out.println("\ncustomer work time will be between " + start + "-" + end);
		if (start.isBefore(h.getOpenhours()) || end.isAfter(h.getClosehours())) {
			System.out.println("not in Hall's hours");
			return;
		}
		if ((start.isBefore(t.getStartWorkingHours())) || (end.isAfter(t.getStartWorkingHours().plusHours(8)))) {
			System.out.println("trainer not at work");
			return;
		}
		List<Subscription> subs = SubscriptionUtil.getSubscriptions(LocalDate.from(app), h.getHallName());
		for (Subscription s : subs) {
			boolean isOverLapped = isOverlapping(start, end, LocalTime.from(s.getAppointment()),
					LocalTime.from(s.getAppointment()).plusMinutes(s.getExercisePlan().planTotalTime()));

			if (isOverLapped) {
				// LocalTime[] overLappedHours=getOverLappedPoints( start, end,
				// LocalTime.from(s.getAppointment()),
				// LocalTime.from(s.getAppointment()).plusMinutes(s.getExercisePlan().planTotalTime()));
				// System.out.println(Arrays.deepToString(overLappedHours));
				if (t == s.getTrainer()) {
					System.out.println("trainer "+t.getTrainerName()+" is busy at this moment, change time ");
					return;
				}
				// LocalTime startPoint=overLappedHours[0];
				// LocalTime endPoint=overLappedHours[1];
				ArrayList<UsedEquipment> subPlanEq = new ArrayList<>();
				LocalTime sStart = LocalTime.from(s.getAppointment());
				for (int[] e : s.getExercisePlan().getEquipmentAndTime()) {
					subPlanEq.add(new UsedEquipment(e[0], sStart, sStart.plusMinutes(e[1])));
					sStart = sStart.plusMinutes(e[1]);
				}
				for (UsedEquipment myE : myPlanEq) {
					for (UsedEquipment sE : subPlanEq) {
						if (myE.overlaps(sE)) {
							System.out.println("equipment with id " + myE.getEquipmentId() + " is in use at this time");
							return;
						}

					}
				}

			}

		}
		//at this point my sub is not overlapping any other, it can be saved to file
		String contentToWrite=h.getHallName()+","+app.toString()+","+custId+","+t.getTrainerId()+","+p.getPlanId();
		CsvUtil.writeLineInFile(contentToWrite, "subscriptions.csv");
		//System.out.println("subscription saved");
		

	}

	private static boolean isOverlapping(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
		return start1.isBefore(end2) && start2.isBefore(end1);
	}

	private static LocalTime[] getOverLappedPoints(LocalTime a, LocalTime b, LocalTime c, LocalTime d) {
		LocalTime[] hours = { a, b, c, d };
		Arrays.sort(hours);
		LocalTime[] points = new LocalTime[2];
		points[0] = hours[1];
		points[1] = hours[2];
		return points;

	}

}
