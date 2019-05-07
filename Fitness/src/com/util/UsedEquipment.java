package com.util;

import java.time.LocalTime;

public class UsedEquipment {
	private int equipmentId;
	private LocalTime start;
	private LocalTime end;
	public UsedEquipment(int equipmentId, LocalTime start, LocalTime end) {
		this.equipmentId = equipmentId;
		this.start = start;
		this.end = end;
	}
	public int getEquipmentId() {
		return equipmentId;
	}
	public LocalTime getStart() {
		return start;
	}
	public LocalTime getEnd() {
		return end;
	}
	
	public boolean overlaps(UsedEquipment eq) {
		return start.isBefore(eq.end) && eq.start.isBefore(end);
	}
	
	

}
