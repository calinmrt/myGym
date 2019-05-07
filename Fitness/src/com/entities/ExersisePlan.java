package com.entities;

import java.util.ArrayList;

public class ExersisePlan {
	private int planId;
	private ArrayList<int[]> equipmentAndTime;

	public ExersisePlan(int planId, ArrayList<int[]> equipmentAndTime) {
		this.planId = planId;
		this.equipmentAndTime = equipmentAndTime;
	}
	
	public int planTotalTime() {
		int time=0;
		for (int[] is : equipmentAndTime) {
			time+=is[1];			
		}
		return time;
	}

	public int getPlanId() {
		return planId;
	}

	public ArrayList<int[]> getEquipmentAndTime() {
		return equipmentAndTime;
	}
	

}
