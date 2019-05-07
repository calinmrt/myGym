package com.util;

import java.io.IOException;
import com.entities.Equipment;

public class EquipmentUtil {
	static Equipment getEquipment(String id) throws IOException {
		String[] memo=CsvUtil.retrieveMemoForId(id,"equipments.csv").split(",");		
		Equipment e=new Equipment(Integer.parseInt(memo[0]),memo[1]);
		return e;
	}
	
}
