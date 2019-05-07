package com.util;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.entities.Subscription;

public class SubscriptionUtil {
	public static List<Subscription> getSubscriptions(LocalDate date, String hallName) {
		List<Subscription> subscriptons =new ArrayList<>();
		List<String> content = CsvUtil.getSubscriptionsForDate(date, hallName);
		content.forEach((line) -> {
			try {
				subscriptons.add(new Subscription(line.split(",")));
			} catch (NumberFormatException | IOException e) {

				e.printStackTrace();
			}
		});

		return subscriptons;

	}
	

}
