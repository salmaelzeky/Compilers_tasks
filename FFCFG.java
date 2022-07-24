package csen1002.main.task6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Write your info here
 * 
 * @name Salma Elzeky
 * @id 43-5917
 * @labNumber 13
 */

public class FFCFG {

	/**
	 * Constructs a CFG for which the First and Follow are to be computed
	 * 
	 * @param description A string representation of a CFG as specified in the task
	 *                    description
	 */

	HashMap<String, HashSet<String>> firstRules = new HashMap<String, HashSet<String>>();
	HashMap<String, HashSet<String>> followRules = new HashMap<String, HashSet<String>>();
	ArrayList<String[]> split = new ArrayList<String[]>();
	ArrayList<String> var = new ArrayList<String>();
	ArrayList<String> t = new ArrayList<String>();
	HashSet<String> newSub = new HashSet<String>();

	public FFCFG(String description) {
		String[] rules_split = description.split(";");
		int counter1 = 0;
		while (counter1 < rules_split.length) {
			String[] addV = rules_split[counter1].split(",");
			split.add(addV);
			counter1++;
		}

		int counter24 = 0;
		while (counter24 < split.size()) {
			var.add(split.get(counter24)[0]);
			counter24++;
		}

		int counter2 = 0;
		while (counter2 < split.size()) {
			String[] rule = split.get(counter2);
			int counter3 = 1;
			while (counter3 < rule.length) {
				int counter4 = 0;
				while (counter4 < rule[counter3].length()) {
					String containsCheck = rule[counter3].charAt(counter4) + "";
					boolean flag = var.contains(containsCheck);
					if (!flag) {
						if (!(containsCheck).equals("e")) {
							t.add(containsCheck);
						}
					}
					counter4++;
				}
				counter3++;
			}
			counter2++;
		}

		int counter5 = 0;
		while (counter5 < t.size()) {
			HashSet<String> terminalsFirst = new HashSet<String>();
			terminalsFirst.add(t.get(counter5));
			firstRules.put(t.get(counter5), terminalsFirst);
			counter5++;
		}

		int counter6 = 0;
		while (counter6 < var.size()) {
			HashSet<String> varsFirst = new HashSet<String>();
			firstRules.put(var.get(counter6), varsFirst);
			counter6++;
		}

		int counter7 = 0;
		while (counter7 < var.size()) {
			HashSet<String> varFollow = new HashSet<String>();
			if (counter7 == 0) {
				varFollow.add("$");
			}
			String v1 = var.get(counter7);
			followRules.put(v1, varFollow);
			counter7++;

		}
	}

	/**
	 * Calculates the First of each variable in the CFG.
	 * 
	 * @return A string representation of the First of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */

	public HashSet<String> gettingSubRule() {
		int counter = 0;
		while (counter < var.size()) {
			int i = 1;
			while (i < split.get(var.indexOf(var.get(counter))).length) {
				int counter2 = 0;
				while (counter2 < split.get(var.indexOf(var.get(counter)))[i].length()) {
					boolean flag1 = counter2 == 0;
					boolean flag2 = epsilonChecker(split.get(var.indexOf(var.get(counter)))[i].substring(0, counter2));
					if (flag1 || flag2) {
						boolean f = (split.get(var.indexOf(var.get(counter)))[i].charAt(counter2) + "").equals("e");
						if (!f) {
							boolean flag = false;
							for (String ff : firstRules
									.get(split.get(var.indexOf(var.get(counter)))[i].charAt(counter2) + "")) {
								if (!ff.equals("e"))
									if (!firstRules.get(var.get(counter)).contains(f))
										flag = false;
									else
										flag = true;

							}

							if (!flag) {
								String sub = split.get(var.indexOf(var.get(counter)))[i].charAt(counter2) + "";
								firstRules.get(sub).remove("e");
								System.out.println("ssssss " + firstRules.get(sub));
								newSub.addAll(firstRules.get(sub));
								System.out.println(" newwwww " + newSub);
							}
						}
					}

					counter2++;
				}

				i++;
			}
			counter++;
		}
		return newSub;
	}

	public boolean findTheIntersection(HashSet<String> v, HashSet<String> b) {
		for (String findV : b) {
			boolean flag = findV.equals("e");
			if (!flag) {
				boolean flagg = v.contains(findV);
				if (!flagg)
					return false;
			}
		}
		return true;

	}

	public boolean epsilonChecker(String r) {
		boolean flag1 = r.length() == 1;
		boolean flag2 = r.equals("e");
		if (flag1 && flag2) {
			return true;
		} else {
			int counter = 0;
			while (counter < r.length()) {
				String containsChecker = r.charAt(counter) + "";
				boolean flag = var.contains(containsChecker);
				if (!flag) {
					return false;
				} else {
					if (flag) {
						HashSet<String> s = firstRules.get(containsChecker);
						boolean flagg = s.contains("e");
						if (!flagg)
							return false;
					}
				}
				counter++;
			}
			return true;
		}
	}

	public String first() {
		// TODO Auto-generated method stub
		boolean change = true;
		String output = "";

		while (change) {
			change = false;
			int counter1 = 0;
			while (counter1 < var.size()) {
				int counter2 = 1;
				int splitV = var.indexOf(var.get(counter1));
				while (counter2 < split.get(splitV).length) {
					boolean flag11 = epsilonChecker(split.get(var.indexOf(var.get(counter1)))[counter2]);
					if (flag11) {
						boolean flag12 = (firstRules.get(var.get(counter1)).contains("e"));
						if (!flag12) {
							String x = var.get(counter1);
							firstRules.get(x).add("e");
							change = true;
						}

					}
					for (int j = 0; j < split.get(var.indexOf(var.get(counter1)))[counter2].length(); j++) {
						boolean flag22 = j == 0;
						boolean flag21 = epsilonChecker(
								split.get(var.indexOf(var.get(counter1)))[counter2].substring(0, j));
						if (flag22 || flag21) {
							boolean flag23 = (split.get(var.indexOf(var.get(counter1)))[counter2].charAt(j) + "")
									.equals("e");
							if (!flag23) {

								boolean flag24 = findTheIntersection(firstRules.get(var.get(counter1)), firstRules
										.get(split.get(var.indexOf(var.get(counter1)))[counter2].charAt(j) + ""));

								if (!flag24) {
									firstRules.get(split.get(var.indexOf(var.get(counter1)))[counter2].charAt(j) + "")
											.remove("e");
									System.out.println("ssssss " + firstRules
											.get(split.get(var.indexOf(var.get(counter1)))[counter2].charAt(j) + ""));
									newSub.addAll(firstRules
											.get(split.get(var.indexOf(var.get(counter1)))[counter2].charAt(j) + ""));
									System.out.println(" newwwww " + newSub);
									firstRules.get(var.get(counter1)).addAll(firstRules
											.get(split.get(var.indexOf(var.get(counter1)))[counter2].charAt(j) + ""));

									change = true;
								}
							}
						}

					}
					counter2++;
				}
				counter1++;
			}
		}

		int j = 0;
		while (j < var.size()) {
			String[] firstVariables = firstRules.get(var.get(j)).toArray(new String[firstRules.get(var.get(j)).size()]);
			Arrays.sort(firstVariables);
			String value = "";
			int counter1 = 0;
			while (counter1 < firstVariables.length) {
				value = value + firstVariables[counter1];
				counter1++;
			}

			boolean emptyFlag = value == "";
			if (!(emptyFlag)) {
				char valueChar = value.charAt(0);
				boolean flag1 = valueChar == '$';
				boolean flag2 = firstVariables.length > 1;
				if (flag1 && flag2) {
					value = value.substring(1) + '$';
				}
			}

			int counter2 = 0;
			while (counter2 < value.length()) {
				String fv = value.charAt(counter2) + "";
				firstVariables[counter2] = fv;
				counter2++;
			}

			output = output + var.get(j) + ",";
			boolean flag25 = firstVariables.length == 0;
			if (flag25) {
				output = output + ";";

			} else {
				int counter22 = 0;
				while (counter22 < firstVariables.length) {
					boolean flag22 = counter22 == firstVariables.length - 1;
					boolean flag23 = j < var.size() - 1;
					if (flag22 && flag23) {
						String newV = firstVariables[counter22] + ";";
						output += newV;
					} else {
						output += firstVariables[counter22];
					}
					counter22++;
				}
			}
			j++;
		}
		return output;
	}

	/**
	 * Calculates the Follow of each variable in the CFG.
	 * 
	 * @return A string representation of the Follow of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String follow() {
		// TODO Auto-generated method stub
		first();
		boolean change = true;
		String output = "";
		while (change) {
			change = false;
			int counter1 = 0;
			while (counter1 < var.size()) {
				int counter2 = 0;
				while (counter2 < split.size()) {
					int counter3 = 1;
					while (counter3 < split.get(counter2).length) {
						ArrayList<Integer> varIndex = new ArrayList<Integer>();
						int counter4 = 0;
						while (counter4 < split.get(counter2)[counter3].length()) {
							boolean flaggg = (split.get(counter2)[counter3].charAt(counter4) + "")
									.equals(var.get(counter1));
							if (flaggg)
								varIndex.add(counter4);
							counter4++;
						}
						int counter5 = 0;
						while (counter5 < varIndex.size()) {
							HashSet<String> modifiedInput = new HashSet<String>();
							int counter = 0;
							while (counter < split.get(counter2)[counter3].substring(varIndex.get(counter5) + 1)
									.length()) {
								modifiedInput.addAll(firstRules.get(split.get(counter2)[counter3]
										.substring(varIndex.get(counter5) + 1).charAt(counter) + ""));
								boolean flag1 = t.contains(split.get(counter2)[counter3]
										.substring(varIndex.get(counter5) + 1).charAt(counter) + "");
								boolean flag2 = modifiedInput.contains("e");
								if (flag1 || !flag2)
									break;
								counter++;
							}

							HashSet<String> firstBettaNoEpsilon = modifiedInput;
							HashSet<String> firstBetta = modifiedInput;
							if (firstBetta.isEmpty()) {
								firstBetta.add("e");
							}
							firstBettaNoEpsilon.remove("e");

							boolean flag34 = findTheIntersection(followRules.get(var.get(counter1)),
									firstBettaNoEpsilon);

							if (!flag34) {
								String addedFollowRules = var.get(counter1);
								followRules.get(addedFollowRules).addAll(firstBettaNoEpsilon);
								boolean flag35 = followRules.get(addedFollowRules)
										.equals(firstRules.get(var.get(counter1)));
								if (flag35) {
									HashSet<String> comparedV = followRules.get(var.get(counter1));
									boolean flag36 = firstRules.get(addedFollowRules).contains(comparedV);
									if (!flag36)
										firstRules.get(var.get(counter1)).addAll(comparedV);
								}
								change = true;
							}

							boolean flag37 = epsilonChecker(
									split.get(counter2)[counter3].substring(varIndex.get(counter5) + 1));
							if (flag37) {
								boolean flag38 = findTheIntersection(followRules.get(var.get(counter1)),
										followRules.get(split.get(counter2)[0]));
								if (!flag38) {
									followRules.get(var.get(counter1)).addAll(followRules.get(split.get(counter2)[0]));
									change = true;
								}
							}
							counter5++;
						}
						counter3++;
					}
					counter2++;
				}
				counter1++;
			}

		}

		for (int j = 0; j < var.size(); j++) {
			String v = var.get(j);
			String[] varFollow = followRules.get(v).toArray(new String[followRules.get(v).size()]);
			Arrays.sort(varFollow);

			String value = "";
			int counter1 = 0;
			while (counter1 < varFollow.length) {
				value += varFollow[counter1];
				counter1++;
			}

			boolean emptyFlag = value == "";
			if (!(emptyFlag)) {
				char valueChar = value.charAt(0);
				boolean flag1 = valueChar == '$';
				boolean flag2 = varFollow.length > 1;
				if (flag1 && flag2) {
					value = value.substring(1) + '$';
				}
			}

			int counter2 = 0;
			while (counter2 < value.length()) {
				String addedV = value.charAt(counter2) + "";
				varFollow[counter2] = addedV;
				counter2++;
			}

			output = output + v + ",";
			int counterr = 0;
			while (counterr < varFollow.length) {
				boolean flagggg = counterr == varFollow.length - 1;
				boolean flaggggg = j < var.size() - 1;
				if (flagggg && flaggggg) {

					String addedV = varFollow[counterr] + ";";
					;
					output += addedV;
				} else {

					output += varFollow[counterr];
				}
				counterr++;
			}
		}
		return output;
	}

}