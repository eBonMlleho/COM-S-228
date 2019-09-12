package edu.iastate.cs228.hw1;

import java.util.Arrays;

/*
 * @author Zhanghao Wen
*/

public class GenomicDNASequence extends DNASequence {
	public boolean[] iscoding; // made public instead of private for grading.

	/**
	 * If the character array argument has a character on which the method
	 * isValidLetter() returns false, then it throws an IllegalArgumentException
	 * with the message \In- valid sequence letter for class
	 * edu.iastate.cs228.hw1.GenomicDNASequence" Other- wise, the constructor saves
	 * a copy of the character array argument in the field of its superclass
	 * Sequence. And it creates a boolean array of the same length as gdnaarr, saves
	 * its reference in the field iscoding, and sets the boolean array to false at
	 * each index.
	 * 
	 * @param gdnaarr
	 */
	public GenomicDNASequence(char[] gdnaarr) {
		super(gdnaarr);
		iscoding = new boolean[seqLength()];
		Arrays.fill(iscoding, false);
	}

	/**
	 * The method throws an IllegalArgumentException exception with the message
	 * "Coding border is out of bound" if first or last is less than 0, or first or
	 * last is greater than or equal to the length (named slen) returned by the
	 * seqLength() method. If first is greater than last, then obtain the coding
	 * strand by calling the method reverseComplement() and transforming first and
	 * last with the formula x = slen - 1 - x. (If first is 55, last is 10 and slen
	 * is 61, then first becomes 61 - 1 - 55 = 5 and last becomes 61 - 1 - 10 = 50.)
	 * Otherwise, the sequence saved in the field seqarr is the coding strand, so no
	 * reverse complementation or transformation is needed. Finally, the method sets
	 * the boolean array iscoding to true at each index between first and last
	 * inclusive.
	 * 
	 * @param first
	 * @param last
	 */
	public void markCoding(int first, int last) {
		if ((first < 0 || last < 0) || (first >= seqLength() || last >= seqLength())) {
			IllegalArgumentException Exception = new IllegalArgumentException("Coding border is out of bound");
			throw Exception;
		} else if (first > last) {
			reverseComplement();
			first = seqLength() - 1 - first;
			last = seqLength() - 1 - last;
		}
		for (int i = first; i < last; i++)
			iscoding[i] = true;
	}

	/**
	 * The integer array argument exonpos is used to specify the start and end
	 * positions of every coding exon in the genomic sequence. The method throws an
	 * IllegalArgumentException exception with the message "Empty array or odd
	 * number of array elements" if the length of exonpos is 0 or an odd number. It
	 * throws an IllegalArgumentException exception with the message "Exon position
	 * is out of bound" if an element in the exonpos is less than 0 or is greater
	 * than or equal to the length returned by the seqLength() method. It throws an
	 * IllegalArgumentException exception with the message "Exon positions are not
	 * in order" if an element in exonpos is greater than its right neighbor
	 * element. It throws an IllegalStateException exception with the message
	 * "Noncoding position is found" if the boolean array iscoding is false at a
	 * coding exon position. Otherwise, the method takes all the coding exons speci
	 * ed by the array exonpos, concatenates them in order, and returns the
	 * resulting sequence in a new character array (see example below). Note that
	 * the array length is the length of the resulting sequence.
	 * 
	 * @param exonpos
	 * @return
	 */
	public char[] extractExons(int[] exonpos) {
		if (exonpos.length == 0 || exonpos.length % 2 == 1) {
			IllegalArgumentException exception = new IllegalArgumentException(
					"Empty array or odd number of array elements");
			throw exception;
		}
		if ((exonpos.length < 0) || (exonpos.length >= seqLength())) {
			IllegalArgumentException exception = new IllegalArgumentException("Exon position is out of bound");
			throw exception;
		}

		boolean isInOrder = true;
		for (int i = 0; i < exonpos.length - 2; i++)
			if (exonpos[i] > exonpos[i + 1])
				isInOrder = false;

		if (isInOrder == false) {
			IllegalArgumentException exception = new IllegalArgumentException("Exon positions are not in order");
			throw exception;
		}

		boolean isPosition = true;
		for (int i = 0; i < exonpos.length; i = i + 2)
			for (int j = exonpos[i]; j < exonpos[i + 1]; j++)
				if (iscoding[j] == false)
					isPosition = false;

		if (isPosition == false) {
			IllegalArgumentException exception = new IllegalArgumentException("Noncoding position is found");
			throw exception;
		}

		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < exonpos.length; i = i + 2)
			for (int j = exonpos[i]; j <= exonpos[i + 1]; j++)
				stringBuilder.append(getSeq()[j]);

		return stringBuilder.toString().toCharArray();
	}

}
