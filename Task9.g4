/**
 * Write your info here
 *
 * @name Salma Elzeky
 * @id 43-5917
 * @labNumber 13
 */

grammar Task9;

@members {
	/**
	 * Compares two integer numbers
	 *
	 * @param x the first number to compare
	 * @param y the second number to compare
	 * @return 1 if x is equal to y, and 0 otherwise
	 */
	public static int equals(int x, int y) {
	    return x == y ? 1 : 0;
	}
}

s returns [int check]:
 // Write the definition of parser rule "s" here
 a c b {$check = equals($a.n, $b.n) * equals($a.n, $c.n); };

a returns [int n]:
 // Write the definition of parser rule "s" here
A a1=a {$n = $a1.n + 1;} |  {$n = 0;};

c returns [int n]:
 // Write the definition of parser rule "s" here
C c1=c {$n= $c1.n +1;} |  {$n = 0;};

b returns [int n]:
 // Write the definition of parser rule "s" here
B b1=b {$n= $b1.n +1;} |  {$n = 0;};

A : [a];
B : [b];
C : [c];
// Write additional lexer and parser rules here