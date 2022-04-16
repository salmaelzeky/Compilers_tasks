package csen1002.main.task5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Write your info here
 * 
 * @name Salma Elzeky
 * @id 43-5917
 * @labNumber 13
 */
public class CFG {
	/**
	 * CFG constructor
	 * 
	 * @param description is the string describing a CFG
	 */
	String printed_string = "";
	String myIdx = "";

	public CFG(String description) {

		String[] symbols = description.split(";");
		LinkedList<String> setOfRules = new LinkedList<String>();

		int counter1 = 0;
		while (counter1 < symbols.length) {
			setOfRules.add(symbols[counter1]);
			counter1++;

		}

		for (int myCounter = 0; myCounter < setOfRules.size(); myCounter++) {
			String res_updated = "";
			String res = "";

			List<String> setOfRulesAfterSplit1 = (List<String>) Arrays.asList(setOfRules.get(myCounter).split(","));
			ArrayList<String> setOfRulesAfterSplitting1 = new ArrayList<String>(setOfRulesAfterSplit1);
			System.out.println("print linked list" + setOfRulesAfterSplitting1);

			for (int j = 0; j < myCounter; j++) {

				List<String> setOfRulesAfterSplit2 = (List<String>) Arrays.asList(setOfRules.get(j).split(","));
				ArrayList<String> setOfRulesAfterSplitting2 = new ArrayList<String>(setOfRulesAfterSplit2);
				System.out.println("print linked list" + setOfRulesAfterSplitting2);

				String getTheSubOfTheRules = setOfRules.get(myCounter);
				int index = setOfRules.get(myCounter).length() - 1;
				char getTheChar = getTheSubOfTheRules.charAt(index);

				if (getTheChar == ';') {
					int newIndex = setOfRules.get(myCounter).length() - 1;
					setOfRulesAfterSplit1 = (List<String>) Arrays
							.asList(getTheSubOfTheRules.substring(0, newIndex).split(","));
					System.out.println("setOfRulesAfterSplit1 " + setOfRulesAfterSplit1);
				}

				String updatedRules = "";
				int counter2 = 1;
				while (counter2 < setOfRulesAfterSplit1.size()) {

					String val1 = setOfRulesAfterSplit1.get(counter2).substring(0, 1);
					String val2 = setOfRulesAfterSplit2.get(0);
					boolean flag = val1.equals(val2);

					if (flag) {
						String val3 = setOfRulesAfterSplit1.get(counter2);
						String val3Sub = val3.substring(1);
						int counter3 = 1;
						while (counter3 < setOfRulesAfterSplit2.size()) {
							String s = setOfRulesAfterSplit2.get(counter3);
							int idx = s.length() - 1;
							char comparedS = s.charAt(idx);
							if (comparedS == ';') {
								String s1 = setOfRulesAfterSplit2.get(counter3);
								int newIdx = setOfRulesAfterSplit2.get(counter3).length() - 1;
								String s1Sub = s1.substring(0, newIdx);
								updatedRules += s1Sub + val3Sub + ",";
							} else {
								String s2 = setOfRulesAfterSplit2.get(counter3);
								updatedRules += s2 + val3Sub + ",";
							}
							counter3++;
						}

					} else {
						String val4 = setOfRulesAfterSplit1.get(counter2);
						updatedRules += val4 + ",";
					}

					counter2++;

				}

				String ss1 = setOfRulesAfterSplit1.get(0);
				int ss2_idx = updatedRules.length() - 1;
				String ss2 = updatedRules.substring(0, ss2_idx);
				updatedRules = ss1 + "," + ss2 + ";";
				setOfRules.set(myCounter, updatedRules);
			}

			String sss = setOfRules.get(myCounter);
			int sss_idx = sss.length() - 1;
			char sss_compared = sss.charAt(sss_idx);
			boolean new_flag = (sss_compared == ';');

			if (new_flag) {
				String sss_sub = sss.substring(0, sss_idx);
				System.out.println("sss_sub " + sss_sub);
				setOfRulesAfterSplit1 = (List<String>) Arrays.asList(sss_sub.split(","));
			}

			int counter4 = 1;
			while (counter4 < setOfRulesAfterSplit1.size()) {
				String sub_compared1 = setOfRulesAfterSplit1.get(0);
				String sub_compared2 = setOfRulesAfterSplit1.get(counter4).charAt(0) + "";
				boolean flagg = sub_compared1.equals(sub_compared2);
				String sub_new_added1 = setOfRulesAfterSplit1.get(counter4);
				String sub_new_added2 = setOfRulesAfterSplit1.get(0);

				if (flagg) {
					res_updated += sub_new_added1.substring(1) + sub_new_added2 + "',";
				} else {
					res += sub_new_added1 + sub_new_added2 + "',";
				}
				counter4++;
			}

			int counter5 = 0;
			while (counter5 < setOfRules.size()) {

				String h = setOfRules.get(counter5);
				int h_idx = h.length() - 1;
				char h_charAt = h.charAt(h_idx);
				boolean flaggg = (h_charAt != ';');
				if (flaggg) {
					setOfRules.set(counter5, h + ";");

				}

				counter5++;

			}

			boolean flagggg = res_updated.equals("");
			if (!flagggg) {
				String h1 = setOfRulesAfterSplit1.get(0);
				int h2_idx = res.length() - 1;
				String h2 = res.substring(0, h2_idx);
				int h3_idx = res_updated.length() - 1;
				String h3 = res_updated.substring(0, h3_idx);
				res = h1 + "," + h2 + ";";
				res_updated = h1 + "'," + h3 + ",e;";
				setOfRules.set(myCounter, res);
				int newCounter = myCounter + 1;
				changeAddOrder(newCounter, setOfRules, res_updated);
				myCounter++;
				myIdx += myCounter + ",";
			}

		}

		int counter6 = 0;
		while (counter6 < setOfRules.size()) {
			printed_string = printed_string + setOfRules.get(counter6);
			System.out.println("printed_string : " + printed_string);
			counter6++;
		}

	}

	public static void changeAddOrder(int counter, LinkedList<String> shifted_rules, String result) {
		boolean flag = (counter == shifted_rules.size() ); 
		if (flag) {
			shifted_rules.add(result);
			return;
		}
		
		int new_counter = shifted_rules.size();
		while(new_counter >= counter) {
			boolean flag2 = (new_counter == shifted_rules.size());
			if (flag2) {
				int added_val_idx = new_counter - 1;
				String added_val = shifted_rules.get(added_val_idx);
				shifted_rules.add(added_val);
			} else {
				
				boolean flag3 = (new_counter != counter);
				if (flag3) {
					int h_idx = new_counter-1;
					String h = shifted_rules.get(h_idx);
					shifted_rules.set(new_counter, h);
				} else {
					shifted_rules.set(counter, result);
				}
			}
			new_counter--;
			
		}
			
	}

	/**
	 * Returns a string of elimnated left recursion.
	 * 
	 * @param input is the string to simulate by the CFG.
	 * @return string of elimnated left recursion.
	 */
	public String lre() {
		String return_result = "";
		if (printed_string.length() > 0) {
			int idx22 = printed_string.length() - 1 ; 
			return_result = printed_string.substring(0, idx22);
			System.out.println("testing " + return_result);
			return return_result;
		} else
			return null;
	}

}
