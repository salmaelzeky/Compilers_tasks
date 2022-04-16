package csen1002.main.task1;

/**
 * Write your info here
 * 
 * @name Salma Elzeky
 * @id 43-5917
 * @labNumber 13
 */
public class DFA {
	/**
	 * DFA constructor
	 * 
	 * @param description is the string describing a DFA
	 */
	String[] RestOfNodesAfterSplitting;
	String[] GoalStatesAfterSplitting;

	public DFA(String description) {
		// TODO Write Your Code Here
		String[] RestOfNodes = description.split("#");
		RestOfNodesAfterSplitting = RestOfNodes[0].split(";");

		String[] GoalStates = description.split("#");
		GoalStatesAfterSplitting = GoalStates[1].split(",");

	}

	/**
	 * Returns true if the string is accepted by the DFA and false otherwise.
	 * 
	 * @param input is the string to check by the DFA.
	 * @return if the string is accepted or not.
	 */
	public boolean run(String input) {
		// TODO Write Your Code Here
		String[] ToTheNextStateInput = input.split("");
		String State = "0";
		int i = 0;
		int j = 0;

		while (i < ToTheNextStateInput.length) {
			if (ToTheNextStateInput[i].equals("0")) {
				State = RestOfNodesAfterSplitting[Integer.valueOf(State)];
				State = State.charAt(2) + "";
			} else {
				State = RestOfNodesAfterSplitting[Integer.valueOf(State)];
				State = State.charAt(4) + "";
			}
			i++;
		}

		while (j < GoalStatesAfterSplitting.length) {
			if (State.equals(GoalStatesAfterSplitting[j])) {
				return true;
			}
			j++;
		}
		return false;
	}
}
