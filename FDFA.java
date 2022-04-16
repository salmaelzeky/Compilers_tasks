package csen1002.main.task4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Write your info here
 * 
 * @name Salma Elzeky
 * @id 43-5917
 * @labNumber 13
 */
public class FDFA {
	/**
	 * FDFA constructor
	 * 
	 * @param description is the string describing a FDFA
	 */
	int i = 0;
	int j = 0;
	int k = 0;
	int left = 0;
	int right = 0;
	int right_1 = 0;
	int right_2 = 0;

	String[] states;
	String[] accept_state;
	String[][] transition2DList;

	Deque<String> currentStateStack = new ArrayDeque<String>();

	String current_state = "0";
	String getAction = "";
	String first_state;
	String state;

	public FDFA(String description) {
		// TODO Write Your Code Here
		states = description.split("#")[0].split(";");
		accept_state = description.split("#")[1].split(",");
		transition2DList = new String[states.length][3];

		while (k < description.split("#")[0].split(";").length) {
			String[] afterSplit = states[k].split(",");
			transition2DList[k] = afterSplit;
			k++;
		}

	}

	/**
	 * Returns a string of actions.
	 * 
	 * @param input is the string to simulate by the FDFA.
	 * @return string of actions.
	 */
	public String run(String input) {
		String currentAction = "";
		int firstCounter = 0;
		while (right < input.length()) {
			while (left < input.length()) {

				if (input.charAt(left) == '0') {
					for (int i = 0; i < transition2DList.length; i++) {
						boolean firstCheck = current_state.equals(transition2DList[i][0]);
						if (firstCheck) {
							String getFirst = transition2DList[i][1];
							current_state = getFirst;
							currentStateStack.push(getFirst);
							break;
						}

					}

				} else if ((input.charAt(left)) == '1') {
					for (int i = 0; i < transition2DList.length; i++) {
						boolean secondCheck = current_state.equals(transition2DList[i][0]);
						if (secondCheck) {
							String getSecond = transition2DList[i][2];
							current_state = getSecond;
							currentStateStack.push(getSecond);
							break;
						}

					}

				}
				left++;
			}

			System.out.println("right_2 " + right_2);

			first_state = currentStateStack.pop();
			state = first_state;
			right_1 = right;

			System.out.println();
			System.out.println("     L " + left);
			System.out.println("     R " + right);
			System.out.println("     R1 " + right_1);
			System.out.println("     R2 " + right_2);
			int x = 0;
			int z = 0;

			while (!currentStateStack.isEmpty()) {
				boolean acceptFlag = false;

				for (int i = 0; i < accept_state.length; i++) {
					if (accept_state[i].equals(state)) {
						acceptFlag = true;
					}

				}

				if (acceptFlag) {
					while (x < transition2DList.length) {
						boolean check1 = state.equals(transition2DList[x][0]);
						if (check1) {
							String getFromTransitions2DList = transition2DList[x][3];
							currentAction += getFromTransitions2DList;
							right = left;
						}
						x++;
					}
					break;
				} else {
					state = currentStateStack.pop();
					left--;

				}

			}

			boolean checkEmpty = currentStateStack.isEmpty();
			if (checkEmpty) {
				if (currentAction.length() == 0) {
					while (z < transition2DList.length) {
						boolean check2 = first_state.equals(transition2DList[z][0]);
						if (check2) {
							String getFromTransitions2DList = transition2DList[z][3];
							currentAction += getFromTransitions2DList;
							right = input.length();

						}
						z++;
					}
				}
			}

			while (!currentStateStack.isEmpty()) {
				currentStateStack.pop();
			}
			getAction += currentAction;
			currentAction = "";
			current_state = "0";
			currentStateStack.push("0");
		}

		System.out.println(" R1   " + right_1);
		System.out.println(right);

		if (right == left && left == input.length()) {
			if (getAction.length() < 3 && getAction.length() != 1) {
				System.out.println("Dakhlt hena");
				String first_part = "";
				String second_part = "";
				System.out.println();
				System.out.println("     L " + left);
				System.out.println("     R " + right);
				for (int counter1 = 0; counter1 < right_1; counter1++) {
					first_part += input.charAt(counter1);

				}
				for (int counter2 = right_1; counter2 < input.length(); counter2++) {
					second_part += input.charAt(counter2);

				}

				System.out.println(
						first_part + "," + getAction.charAt(0) + ";" + second_part + "," + getAction.charAt(1) + ";");
				return first_part + "," + getAction.charAt(0) + ";" + second_part + "," + getAction.charAt(1) + ";";

			} else if (getAction.length() < 4 && getAction.length() != 1) {
				String first_part = "";
				String second_part = "";
				String third_part = "";
				System.out.println("     L " + left);
				System.out.println("     R " + right);
				System.out.println("R1 " + right_1);
				System.out.println("R " + right);

				System.out.println(" L  " + left);
				for (int counter1 = 0; counter1 < left - right_1 - 1; counter1++) {
					first_part += input.charAt(counter1);

				}
				System.out.println(" L - R1 " + (left - right_1 - 1));
				System.out.println("input.length() " + (input.length() - right_1));
				for (int counter2 = left - right_1 - 1; counter2 <= input.length() - right_1 + 1; counter2++) {
					second_part += input.charAt(counter2);

				}
				for (int counter3 = input.length() - right_1 + 2; counter3 < input.length(); counter3++) {
					third_part += input.charAt(counter3);

				}
				System.out
						.println("ANA HENAAAAA " + first_part + "      rr " + second_part + "        ss" + third_part);
				return first_part + "," + getAction.charAt(0) + ";" + second_part + "," + getAction.charAt(1) + ";"
						+ third_part + "," + getAction.charAt(2) + ";";
			} else {
				System.out.println(input + "," + getAction + ";");
				return input + "," + getAction + ";";
			}

		} else {
			if (getAction.length() < 3 && getAction.length() != 1) {
				String first_part = "";
				String second_part = "";

				System.out.println();
				System.out.println("     L " + left);
				System.out.println("     R " + right);

				for (int counter1 = 0; counter1 < left; counter1++) {
					first_part += input.charAt(counter1);

				}
				for (int counter3 = left; counter3 < input.length(); counter3++) {
					second_part += input.charAt(counter3);

				}

				System.out.println(
						first_part + "," + getAction.charAt(0) + ";" + second_part + "," + getAction.charAt(1) + ";");
				return first_part + "," + getAction.charAt(0) + ";" + second_part + "," + getAction.charAt(1) + ";";

			} else {
				System.out.println(input + "," + getAction + ";");
				return input + "," + getAction + ";";
			}
		}

	}

//	public static void main(String[] args) {
//		String dfa = "0,3,1,N;1,8,2,O;2,8,8,A;3,4,6,P;4,8,5,Q;5,8,8,B;6,7,8,R;7,8,8,C;8,8,8,S#2,5,7";
//		FDFA fdfa = new FDFA(dfa);
//		fdfa.run("11001010");
//
//	}
}
