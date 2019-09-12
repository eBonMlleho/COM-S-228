package edu.iastate.cs228.hw1;

/*
 * @author Zhanghao Wen
*/

public class ProteinSequence extends Sequence {
	/**
	 * If the character array argument psarr contains a character on which the
	 * method isValidLetter() returns false, then it throws an
	 * IllegalArgumentException with the message \Invalid sequence letter for class
	 * edu.iastate.cs228.hw1.ProteinSequence" Otherwise, the constructor saves a
	 * copy of the character array argument in the eld of its superclass Sequence.
	 * 
	 * @param psarr
	 */
	public ProteinSequence(char[] psarr) {
		super(psarr);
	}

	/**
	 * The method returns true if the character argument is equal to one of the 20
	 * English letters that are not in the set fB, b, J, j, O, o, U, u, X, x, Z, zg.
	 * Otherwise, it returns false. This method overrides the one in its superclass.
	 */
	@Override
	public boolean isValidLetter(char aa) {
		aa = Character.toLowerCase(aa);
		if (Character.isLetter(aa) && aa != 'b' && aa != 'j' && aa != 'o' && aa != 'u' && aa != 'x' && aa != 'z')
			return true;
		else
			return false;
	}
}
