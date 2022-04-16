package csen1002.main.task3;

import java.util.ArrayList;

/**
 * Write your info here
 * 
 * @name Salma Elzeky
 * @id 43-5917
 * @labNumber 13
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

import csen1002.main.task3.RegToNFA;

/**
 * Write your info here
 * 
 * @name John Smith
 * @id 43-0234
 * @labNumber 07
 */

public class RegToNFA {

	/**
	 * Constructs an NFA corresponding to a regular expression based on Thompson's
	 * construction
	 * 
	 * @param regex The regular expression in postfix notation for which the NFA is
	 *              to be constructed
	 */
	String res;

	public static class Trans {
		public int state_from, state_to;
		public char trans_symbol;

		public Trans(int v1, int v2, char sym) {
			this.state_from = v1;
			this.state_to = v2;
			this.trans_symbol = sym;
		}

		public int getStateFrom() {
			return this.state_from;
		}

		public int getStateTo() {
			return this.state_to;
		}

		public int getTransSymbol() {
			return this.trans_symbol;
		}

	}

	public static class NFA {
		public ArrayList<Integer> states;
		public ArrayList<Trans> transitions;

		public int final_state;
		public int start_state;
		public int newState;
		String zeroTransition;
		String oneTransition;
		String eTransition;
		String finalres = "";

		public NFA() {
			this.states = new ArrayList<Integer>();
			this.transitions = new ArrayList<Trans>();
			this.final_state = 0;
			this.start_state = 0;
			this.newState = 0;
			this.zeroTransition = "";
			this.oneTransition = "";
			this.eTransition = "";

		}

		public void display() {
			String x;
			for (Trans t : transitions) {
				System.out.println("(FROM " + t.state_from + ", TO " + t.state_to + ", SYMBOL " + t.trans_symbol + ")");
				// transitionsLast.add(new Trans(t.state_from, t.state_to, t.trans_symbol));
				x = "(" + t.state_from + "," + t.state_to + "," + t.trans_symbol + ")";
				 System.out.println(x);
			

			}
			// System.out.println(Arrays.toString(transitionsLast.toArray()));
			// System.out.println(transitions.get(0));
		}

	}

	public int count(String s, char c, int i) {
		int n = 0;
		for (int j = i; j > -1; j--) {
			if (s.charAt(j) == '|' || s.charAt(j) == '.')
				n++;
			else
				return n;
		}
		return n;
	}

	public static int getMin(ArrayList<Integer> a) {
		int m = a.get(0);
		for (int i = 1; i < a.size(); i++) {
			if (a.get(i) < m)
				m = a.get(i);
		}
		return m;
	}

	public RegToNFA(String regex) {
		NFA result = new NFA();
		int pointer = 0;
		// ArrayList<Integer> usedPos = new ArrayList<Integer>();
		List<Integer> nfa_acceptstate = new ArrayList<Integer>();
		List<Integer> nfa_startstate = new ArrayList<Integer>();
		ArrayList<Integer> usedPos = new ArrayList<Integer>();

		ArrayList<String> transitionsLast = new ArrayList<String>();

		for (int i = 0; i < regex.length(); i++) {
			char c = regex.charAt(i);

			// add 0 transition from new state to new state+1
			if (c == '0') {
				result.transitions.add(new Trans(result.newState, result.newState + 1, '0'));
				// nfa_acceptstate.add(res)
				result.zeroTransition += result.newState + "," + (result.newState + 1) + ";";
				pointer++;
				nfa_acceptstate.add(result.newState + 1);
				nfa_startstate.add(result.newState);
				result.final_state = result.newState + 1;
				result.newState += 2;

			}

			// add 1 transition from new state to new state+1
			else if (c == '1') {
				result.transitions.add(new Trans(result.newState, result.newState + 1, '1'));
				result.oneTransition += result.newState + "," + (result.newState + 1) + ";";
				pointer++;
				result.final_state = result.newState + 1;
				nfa_acceptstate.add(result.newState + 1);
				nfa_startstate.add(result.newState);
				result.newState += 2;

			}

			// add epsilon transition from new state to new state+1
			else if (c == 'e') {
				result.transitions.add(new Trans(result.newState, result.newState + 1, 'e'));
				result.eTransition += result.newState + "," + (result.newState + 1) + ";";
				int z = result.newState + 1;
				String x = result.newState + "," + z;
				transitionsLast.add(x);
				pointer++;
				result.final_state = result.newState + 1;
				nfa_acceptstate.add(result.newState + 1);
				nfa_startstate.add(result.newState);
				result.newState += 2;
			}

			else if (c == '|') {
				int counter = 0;
				for (int j = i; j > -1; j--) {
					if (regex.charAt(j) == '|' || regex.charAt(j) == '.')
						counter++;

				}

				System.out.println("n " + counter);
				System.out.println("pointer " + pointer);

				int p = pointer - (counter * 2);
				System.out.println(" P " + p);

//				if (usedPos.contains(p)) {
//					p = getMin(usedPos) - 1;
//
//				}

				int a1 = result.transitions.get(p).getStateFrom();
				int a2 = result.transitions.get(p).getStateTo();
				int b1 = result.transitions.get(pointer - 1).getStateFrom();
				int b2 = result.transitions.get(pointer - 1).getStateTo();

//				System.out.println("ADY a1 " + a1);
//				System.out.println("ADY a2 " + a2);
//				System.out.println("ADY b1 " + b1);
//				System.out.println("ADY b2 " + b2);

				// connect first final state to new goal state
				result.eTransition += a2 + "," + (result.newState + 1) + ";";
				System.out.println(result.eTransition);
				int y = result.newState + 1;
				transitionsLast.add(a2 + "," + y);

				// connect second final state to new goal state
				result.eTransition += b2 + "," + (result.newState + 1) + ";";
			//	System.out.println( result.transitions.get(pointer-2).getStateTo());
				System.out.println(result.eTransition);

				int z = result.newState + 1;
				transitionsLast.add(b2 + "," + z);

				// connect new start state to first initial state
				result.eTransition += result.newState + "," + a1 + ";";
				System.out.println(result.eTransition);

				transitionsLast.add(result.newState + "," + a1);

				// connect new start state to second initial state
				result.eTransition += result.newState + "," + b1 + ";";
				System.out.println(result.eTransition);

				transitionsLast.add(result.newState + "," + b1);

				result.start_state = result.newState;
				result.final_state = result.newState + 1;
				result.newState += 2;
				result.transitions.add(new Trans(result.start_state, result.final_state, 'e'));

				pointer++;
				String x ;
				for (Trans t : result.transitions) {
					System.out.println("(FROM " + t.state_from + ", TO " + t.state_to + ", SYMBOL " + t.trans_symbol + ")");
					// transitionsLast.add(new Trans(t.state_from, t.state_to, t.trans_symbol));
					x = "(" + t.state_from + "," + t.state_to + "," + t.trans_symbol + ")";
					 System.out.println(x);
				

				}

			}

			else if (c == '.') {

				int n = count(regex, c, i);
				int p = pointer - (n * 2);
				if (usedPos.contains(p))
					p = getMin(usedPos) - 1;
				int a1 = result.transitions.get(p).getStateFrom();
				int a2 = result.transitions.get(p).getStateTo();
				int b1 = result.transitions.get(pointer - 1).getStateFrom();
				int b2 = result.transitions.get(pointer - 1).getStateTo();
				usedPos.add(p);
				usedPos.add(pointer - 1);

				// e trans from last final state to next initial state

				result.eTransition += a2 + "," + b1 + ";";
				if (!transitionsLast.contains((a2 + "," + b1))) {
					transitionsLast.add(a2 + "," + b1);

				}


				result.start_state = a1;
				result.final_state = b2;

				result.transitions.add(new Trans(a1, b2, 'e'));

				pointer++;
			}

			else if (c == '*') {

				int b1 = result.transitions.get(pointer - 1).getStateFrom();
				System.out.println("b1 " + b1);
				int b2 = result.transitions.get(pointer - 1).getStateTo();
				System.out.println("b2 " + b2);

				// from final state to start state
				result.eTransition += b2 + "," + b1 + ";";
				transitionsLast.add(b2 + "," + b1);

				// from final state to new final state
				result.eTransition += b2 + "," + (result.newState + 1) + ";";
				// int s = result.newState + 1;
				transitionsLast.add(b2 + "," + (result.newState + 1));

				// new start state to original start state
				result.eTransition += result.newState + "," + b1 + ";";
				transitionsLast.add(result.newState + "," + b1);

				// new start state to new final state
				result.eTransition += result.newState + "," + (result.newState + 1) + ";";
				int y2 = result.newState + 1;
				transitionsLast.add(result.newState + "," + y2);

				// test
				// result.transitions.remove(pointer-1);
				 result.transitions.add(new Trans (result.final_state, result.start_state,
				 'e'));

				// result.transitions.add(t);
				result.start_state = result.newState;
				result.final_state = result.newState + 1;
				result.newState += 2;
			}
			

		}

		Collections.sort(transitionsLast);
		System.out.println(transitionsLast);
		String eTransStringLast = "";
		for (int k = 0; k < transitionsLast.size(); k++) {
			eTransStringLast += transitionsLast.get(k) + ";";

		}
		if (result.zeroTransition.length() > 0)
			result.zeroTransition = result.zeroTransition.substring(0, result.zeroTransition.length() - 1);
		if (result.oneTransition.length() > 0)
			result.oneTransition = result.oneTransition.substring(0, result.oneTransition.length() - 1);
		if (eTransStringLast.length() > 0)
			eTransStringLast = eTransStringLast.substring(0, eTransStringLast.length() - 1);

		result.finalres = result.newState + "#" + result.start_state + "#" + result.final_state + "#"
				+ result.zeroTransition + "#" + result.oneTransition + "#" + eTransStringLast;

		System.out.println(result.finalres);
		res = result.finalres;
		res.toString();
	}

	/**
	 * @return Returns a formatted string representation of the NFA. The string
	 *         representation follows the one in the task description
	 */

	public String toString() {

		if (res.length() > 0)
			return res;
		else
			return null;
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		RegToNFA t = new RegToNFA("00*|*");
		System.out.println(t.toString());
		
	}

}