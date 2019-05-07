package com.util;

import com.entities.ExersisePlan;

public class ExersisePlanUtil {
	public static void displayExersisePlans() {
		CsvUtil.getPlans();
	}

	public static ExersisePlan getPlan(int id) {
		return new ExersisePlan(id, CsvUtil.getPlan(id));

	}

}
