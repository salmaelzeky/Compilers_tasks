package csen1002.main.task7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * Write your info here
 * 
 * @name Salma Elzeky
 * @id 43-5917
 * @labNumber 13
 */
public class LL1CFG {
	/**
	 * LL1 CFG constructor
	 * 
	 * @param description is the string describing an LL(1) CFG, first, and follow
	 *                    as represented in the task description.
	 */

	String[] splitOne;
	String[] newList;

	String errorString = "ERROR";
	String myFinalOutput = "";
	String dollarSign = "$";
	String startLetter = "S";
	String eLetter = "e";

	static LinkedList<String> nst = new LinkedList<>();

	static LinkedList<String> st = new LinkedList<>();
	static Map<String, String[]> parsingTable = new HashMap<String, String[]>();
	LinkedList<String> containers = new LinkedList<String>();
	LinkedList<String> parsingList = new LinkedList<String>();

	public LL1CFG(String description) {
		splitOne = description.split("#")[0].split(";");
		int counter1 = 0;
		while (counter1 < splitOne.length) {
			String cfg_string = splitOne[counter1].split(",")[0];
			nst.add(cfg_string);
			int counter2 = 0;
			while (counter2 < splitOne[counter1].split(",").length) {
				int counter3 = 0;
				int counterr3 = splitOne[counter1].split(",")[counter2].length();
				while (counter3 < counterr3) {
					char splitOneString = splitOne[counter1].split(",")[counter2].charAt(counter3);
					@SuppressWarnings("unlikely-arg-type")
					boolean flag1 = !st.contains(splitOneString);
					System.out.println("Check hena flag1 " + flag1);
					if (flag1) {
						char splitTwoString = splitOne[counter1].split(",")[counter2].charAt(counter3);
						boolean flag2 = Character.isUpperCase(splitTwoString);
						System.out.println("Tb w flag2 " + flag2);

						if (!flag2) {
							char splitOneChar = splitOne[counter1].split(",")[counter2].charAt(counter3);
							System.out.println("CHAR CHECKER " + splitOneChar);

							boolean flag3 = splitOneChar == 'e';
							System.out.println("flag3 aho " + flag3);

							if (!flag3) {
								String splitOnes = splitOne[counter1].split(",")[counter2].charAt(counter3) + "";
								st.add(splitOnes);
							}
						}
					}
					counter3++;
				}
				counter2++;
			}

			counter1++;
		}
		int counter4 = 0;
		st.add(dollarSign);
		while (counter4 < nst.size()) {

			int length = st.size();
			newList = new String[length];
			System.out.println("FIRST " + splitOne[counter4].split(",")[0]);
			System.out.println("SECOND " +  description.split("#")[1].split(";")[counter4].split(",")[0]);
			System.out.println("THIRD " + description.split("#")[2].split(";")[counter4].split(",")[0]);

			parsingTable.put(nst.get(counter4), newList);

			int counter5 = 1;
			while (counter5 <  description.split("#")[1].split(";")[counter4].split(",").length) {
				boolean flag4 = ! description.split("#")[1].split(";")[counter4].split(",")[counter5].equals(eLetter);
				if (!flag4) {
					int counter6 = 0;
					while (counter6 < description.split("#")[2].split(";")[counter4].split(",")[1].length()) {
						String index_string = description.split("#")[2].split(";")[counter4].split(",")[1].charAt(counter6) + "";
						parsingTable.put(nst.get(counter4), newList);
						int index = st.indexOf(index_string);
						newList[index] = eLetter;
						counter6++;
					}
				} else {
					int counter7 = 0;
					int counterr7 =  description.split("#")[1].split(";")[counter4].split(",")[counter5].length();
					while (counter7 < counterr7) {
						String index1 =  description.split("#")[1].split(";")[counter4].split(",")[counter5].charAt(counter7) + "";
						parsingTable.put(nst.get(counter4), newList);
						int index2 = st.indexOf(index1);
						newList[index2] = splitOne[counter4].split(",")[counter5];
						System.out.println("OUTPUTT EHHH " + newList[index2]);
						counter7++;
					}
				}
				counter5++;
			}
			counter4++;
		}
	}

	/**
	 * Returns A string encoding a derivation is a comma-separated sequence of
	 * sentential forms each representing a step in the derivation..
	 * 
	 * @param input is the string to be parsed by the LL(1) CFG.
	 * @return returns a string encoding a left-most derivation.
	 */

	public void pushing() {
		containers.push(dollarSign);
		containers.push(startLetter);
		parsingList.add(startLetter);
	}

	public void rules(String input) {
		int counter = 0;
		boolean change = true;
		while (change) {
			boolean flag1 = counter != input.length();
			if (flag1) {
				boolean flag2 = !containers.element().equals(dollarSign);
				if (flag2) {
					int idx = parsingList.size() - 1;
					boolean flag3 = !parsingList.get(idx).equals(errorString);
					if (flag3) {
						while (counter < input.length()) {
							String lastElement = containers.element();
							boolean flag4 = Character.isUpperCase(containers.element().charAt(0));
							if (flag4) {
								int index = st.indexOf(input.charAt(counter) + "");
								String elem = containers.element();
								String parsingTableElement = parsingTable.get(elem)[index];
								boolean errorFlag = parsingTable.get(elem)[index] != null;
								if (!errorFlag) {
									parsingList.add(errorString);
									break;
								}
								boolean eFlag = !parsingTable.get(elem)[index].equals(eLetter);
								if (!eFlag) {
									containers.pop();
									int s = parsingList.size() - 1;
									String parsListElem = parsingList.get(s).replaceFirst(lastElement, "");
									parsingList.add(parsListElem);
								} else {
									containers.pop();
									int s = parsingList.size() - 1;
									String parsListElem = parsingList.get(s).replaceFirst(lastElement,
											parsingTableElement);
									parsingList.add(parsListElem);
									int counterr = parsingTableElement.length();
									while (counterr > 0) {
										String pushedElem = parsingTableElement.substring(counterr - 1, counterr);
										containers.push(pushedElem);
										counterr--;
									}
								}
							}
							boolean flagg = Character.isUpperCase(lastElement.charAt(0));
							if (!flagg) {
								boolean flagg1 = lastElement.charAt(0) != input.charAt(counter);
								if (flagg1) {
									parsingList.add(errorString);
									break;
								} else {
									containers.pop();
									counter++;
									break;
								}

							}
							boolean flaggg = lastElement.charAt(0) != dollarSign.charAt(0);
							if (!flaggg) {
								break;
							}
						}
					} else {
						break;
					}
				} else {
					break;
				}
			} else {
				break;
			}
		}

		st = new LinkedList<>();
		nst = new LinkedList<>();
	}

	public String parse(String input) {
		input = input + dollarSign;

		pushing();
		rules(input);

		String firstelem = parsingList.get(0);
		myFinalOutput = myFinalOutput + firstelem;

		int counterrr = 1;
		while (counterrr < parsingList.size()) {
			String addedOne = "," + parsingList.get(counterrr);
			myFinalOutput = myFinalOutput + addedOne;
			counterrr++;
		}

		return myFinalOutput;
	}

}
