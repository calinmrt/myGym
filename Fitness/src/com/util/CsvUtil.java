package com.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import com.entities.ExersisePlan;
import com.entities.Hall;
import com.entities.Subscription;

public class CsvUtil {
	public static String retrieveMemoForId(String id, String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;
		while ((line = br.readLine()) != null) {
			if (line.split(",")[0].equals(id)) {
				br.close();
				return line;
			}

		}
		br.close();
		return null;
	}

	public static void writeLineInFile(String line, String path) {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(path, true));
			bw.write(line);
			bw.close();
		} catch (IOException e) {
			System.out.println("for some reason the file couldn't be open");
		} finally {
			System.out.println("information memorized");
		}

	}

	public static Hashtable<String, Hall> getHalls() {
		Hashtable<String, Hall> h = new Hashtable<String, Hall>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("halls.csv"));
			String name, line;
			while ((line = br.readLine()) != null) {
				name = line.split(",")[0];
				String[] tr = br.readLine().substring(9).split(",");
				String[] eq = br.readLine().substring(11).split(",");
				Hall hall = HallUtil.getHall(name, tr, eq);
				h.put(name, hall);
			}
			br.close();
			return h;
		} catch (IOException e) {
			System.out.println("file won't open");
		}
		return h;

	}

	public static int getAvailableId(String path) {
		int id = 1;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null) {
				id = Integer.parseInt(line.split(",")[0]);
			}
			br.close();
			id++;
			return id;
		} catch (IOException e) {
			return id;
		}
	}

	static void getCustomers(String path) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			System.out.println(
					"\nyour customers are:\n\nid                  name                adress              phone\n"
							+ String.join("", Collections.nCopies(80, "_")));
			while ((line = br.readLine()) != null) {
				String[] l = line.split(",");
				System.out.println(l[0] + String.join("", Collections.nCopies(20 - l[0].length(), " ")) + l[1]
						+ String.join("", Collections.nCopies(20 - l[1].length(), " ")) + l[2]
						+ String.join("", Collections.nCopies(20 - l[2].length(), " ")) + l[3]);

			}
			br.close();
			System.out.println(String.join("", Collections.nCopies(80, "_")));
		} catch (IOException e) {
			System.out.println("a customer list is not available ");
		}

	}

	static void getPlans() {
		Hashtable<Integer, String> h = new Hashtable<>();
		int index = 1;
		try {
			BufferedReader br = new BufferedReader(new FileReader("exersisePlans.csv"));
			String line;
			System.out.println(
					"\navailable exersise plans are:\n\nid                  equipment           time(minutes)\n"
							+ String.join("", Collections.nCopies(60, "_")));
			while ((line = br.readLine()) != null) {
				String[] l = line.split(",");
				String equipmentName = EquipmentUtil.getEquipment(l[1]).getEquipmentName();
				h.put(Integer.parseInt(l[0]) * 100 + (++index),
						(l[0]) + String.join("", Collections.nCopies(20 - l[0].length(), " ")) + equipmentName
								+ String.join("", Collections.nCopies(20 - equipmentName.length(), " ")) + l[2]);

			}
			br.close();

			h.forEach((k, v) -> System.out.println(v));

			System.out.println(String.join("", Collections.nCopies(60, "_")));
		} catch (IOException e) {
			System.out.println("exersise list is not available ");
		}
	}

	static ArrayList<int[]> getPlan(int id) {
		ArrayList<int[]> list = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("exersisePlans.csv"));
			String line;
			while ((line = br.readLine()) != null) {
				String[] l = line.split(",");
				if (Integer.parseInt(l[0]) == id) {
					int[] arr = new int[2];
					arr[0] = Integer.parseInt(l[1]);
					arr[1] = Integer.parseInt(l[2]);
					list.add(arr);
				}
			}
			br.close();
			return list;
		} catch (IOException e) {
			System.out.println("something went terible wrong");
		}
		return null;

	}

	// gets a list of subscriptions for a date in a hall
	public static List<String> getSubscriptionsForDate(LocalDate date, String hallName) {
		Path file = Paths.get("subscriptions.csv");
		try {
			List<String> content = Files.readAllLines(file);
			if (content.isEmpty()) {
				throw new IOException();
			}
			List<String> filteredContent = content.stream().filter(line -> line.contains(hallName))
					.filter(line -> line.contains(date.toString())).collect(Collectors.toList());
			// filteredContent.forEach(System.out::println);
			return filteredContent;

		} catch (IOException e) {
			System.err.println("the file subscriptions.csv does not exist or is empty");
		}
		return null;
	}

}
