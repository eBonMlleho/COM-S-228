package edu.iastate.cs228.hw1;

/*
 * @author Zhanghao Wen
*/

public class Sequence {
	public char[] seqarr; // made public instead of protected for grading.

	/**
	 * The constructor first uses the isValidLetter() method to check if every
	 * character in the array sarr is valid. If so, it makes and keeps a copy of the
	 * array sarr in the field seqarr of type char[]. Otherwise, it throws an
	 * IllegalArgument Exception with the message "Invalid sequence letter for class
	 * X" where X denotes `edu.iastate.cs228.hw1.Sequence' or the name of a subclass
	 * of which an object is created. Examples are given below to illustrate what
	 * exactly is denoted by X after the subclasses of class Sequence are defined.
	 * Note that the length of the field seqarr is equal to the length of the array
	 * sarr. This constructor should be implemented in such a way that it is
	 * unnecessary to have more than one line of code in the body of the constructor
	 * of any subclass of this class.
	 * 
	 * @param sarr
	 */
	public Sequence(char[] sarr) {
		seqarr = new char[sarr.length];
		boolean isValid = true;
		for (int i = 0; i < sarr.length; i++)
			if (!isValidLetter(sarr[i]))
				isValid = false;
		if (isValid)
			for (int i = 0; i < sarr.length; i++)
				seqarr[i] = sarr[i];
		else {
			IllegalArgumentException exception = new IllegalArgumentException(
					"Invalid sequence letter for class" + this.getClass().toString());
			throw exception;
		}

	}

	/**
	 * The method returns the length of the character array seqarr.
	 * 
	 * @return seqarr.length
	 */
	public int seqLength() {
		return seqarr.length;
	}

	/**
	 * The method makes and returns a copy of the char array seqarr.
	 * 
	 * @return result
	 */
	public char[] getSeq() {
		// return seqarr; ?????
		char[] result = new char[seqarr.length];
		for (int i = 0; i < seqarr.length; i++)
			result[i] = seqarr[i];
		return result;

	}

	/**
	 * The method returns the string representation of the char array seqarr.
	 */
	public String toString() {
		// not sure just use the following? why we need this method then
		// return seqarr.toString();

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < seqarr.length; i++)
			result.append(seqarr[i]);
		return result.toString();
	}

	/**
	 * The method returns true if the argument obj is not null and is of the same
	 * type as this object such that both objects represent the identical sequence
	 * of characters in a case insensitive mode ("ACgt" is identical to "AcGt"). The
	 * equals() method should be de ned in such a way that there is no need to de ne
	 * it again in any subclass of class Sequence. In other words, when an object of
	 * the subclass calls the equals() method, it should return the right answer.
	 */
	public boolean equals(Object obj) {
		// not sure
		if (obj != null && this.getClass() != obj.getClass())
			return true;
		return false;

	}

	/**
	 * The method returns true if the character let is an uppercase
	 * (Character.isUpperCase(let) is true) or lowercase (Character.isLowerCase(let)
	 * is true). Otherwise, it returns false.
	 * 
	 * @param let
	 * @return Character.isLetter(let)
	 */
	public boolean isValidLetter(char let) {
		return Character.isLetter(let);
	}

}
