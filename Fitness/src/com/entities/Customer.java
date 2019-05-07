package com.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.manager.Manager;
import com.util.CustomerUtil;
import com.util.SubscriptionUtil;

public class Customer {
	private int customerId;
	private String customerName;
	private String customerAdress;
	private String customerPhone;

	public Customer(int customerId, String customerName, String customerAdress, String customerPhone) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerAdress = customerAdress;
		this.customerPhone = customerPhone;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerAdress() {
		return customerAdress;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void insertNewSubscription(Hall h, Trainer t, ExersisePlan p, LocalDateTime app) {
		CustomerUtil.insertSubscription(app,h,t,p,this.customerId);

	}

}
