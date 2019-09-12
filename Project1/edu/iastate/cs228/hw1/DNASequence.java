package edu.iastate.cs228.hw1;

/*
 * @author Zhanghao Wen
*/

public class DNASequence extends Sequence {
	/**
	 * If the character array argument has a character on which the method
	 * isValidLetter() returns false, then it throws an IllegalArgumentException
	 * with the message "Invalid sequence letter for class X" where X denotes
	 * "edu.iastate.cs228.hw1.DNASequence" or the name of a subclass of which an
	 * object is created. Otherwise, the constructor saves a copy of the character
	 * array argument in the eld of its superclass.
	 * 
	 * @param dnaarr
	 */
	public DNASequence(char[] dnaarr) {
		// since isValid is overwritten, so just call super constructor
		super(dnaarr);
		// for(int i=0;i<dnaarr.length;i++)
		// if(isValidLetter(dnaarr[i]))
		// super(dnaarr);
		// else {
		// IllegalArgumentException exception = new IllegalArgumentException("Invalid
		// sequence letter for class" + this.getClass().toString())
		// throw exception;
		// }
	}

	/**
	 * The method returns true if the character argument is equal to one of the
	 * eight characters 'a', 'A', 'c', 'C', 'g', 'G', 't' and 'T'. Otherwise, it
	 * returns false. This method overrides the one in its superclass.
	 */
	@Override
	public boolean isValidLetter(char let) {
		let = Character.toLowerCase(let);
		if (let == 'a' || let == 'c' || let == 'g' || let == 't')
			return true;
		return false;

	}

	/**
	 * The method produces the reverse complement of the DNA sequence saved in the
	 * char array seqarr, and returns a char array with the resulting sequence. DNA
	 * is double-stranded with one strand being the reverse complement of the other
	 * strand. The reverse complement of a DNA sequence is obtained by reversing the
	 * sequence (turning it by 180 degrees or making the right end left) and
	 * complementing each base (changing A, C, G and T into T, G, C, and A,
	 * respectively and changing a, c, g, and t into t, g, c and a, respectively).
	 * See the example below.
	 * 
	 * @return
	 */
	public char[] getReverseCompSeq() {
		char[] reversal = new char[seqLength()];
		// stupid method
		for (int i = 0; i < seqLength(); i++) {
			reversal[i] = seqarr[seqLength() - 1 - i];
			if (reversal[i] == 'A')
				reversal[i] = 'T';

			else if (reversal[i] == 'T')
				reversal[i] = 'A';

			else if (reversal[i] == 'G')
				reversal[i] = 'C';

			else if (reversal[i] == 'C')
				reversal[i] = 'G';
		}

		return reversal;
	}

	/**
	 * The method calls getReverseCompSeq() and saves the result in a temporary
	 * array and then copies the array back into the char array seqarr. As a result,
	 * the char array seqarr contains the reverse complement of its original
	 * sequence. When a coding region is in the other strand of one in char array
	 * seqarr, this method is used to save the other strand in the char array
	 * seqarr. See markCoding(int first, int last) below.
	 */
	public void reverseComplement() {
		char[] temp = getReverseCompSeq();
		for (int i = 0; i < seqLength(); i++)
			seqarr[i] = temp[i];

	}
}
