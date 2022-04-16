public class DFA {
	String[] RestOfNodesAfterSplitting;
	String[] GoalStatesAfterSplitting;

	public DFA(String description) {
		// TODO Write Your Code Here
		String[] RestOfNodes = description.split("#");
		RestOfNodesAfterSplitting = RestOfNodes[0].split(";");

		String[] GoalStates = description.split("#");
		GoalStatesAfterSplitting = GoalStates[1].split(",");

	}
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
