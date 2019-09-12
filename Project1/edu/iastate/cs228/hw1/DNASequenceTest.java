package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * @author Zhanghao Wen
*/

public class DNASequenceTest {

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentException() {
		char[] mychar = new char[] { 'g', 'a', 's' };
		DNASequence sequence = new DNASequence(mychar);

	}

	@Test
	public void testreversal() {
		char[] mychar = new char[] { 'T', 'C', 'A', 'G', 'A', 'T', 'G', 'G', 'A', 'C', 'A', 'A', 'G', 'G', 'C' };
		DNASequence sequence = new DNASequence(mychar);
		assertArrayEquals(new char[] { 'G', 'C', 'C', 'T', 'T', 'G', 'T', 'C', 'C', 'A', 'T', 'C', 'T', 'G', 'A' },
				sequence.getReverseCompSeq());

	}

	@Test
	public void testIsValidLetter1() {
		char[] chararr = new char[] { 'a', 'c', 'g' };
		DNASequence seq = new DNASequence(chararr);
		assertEquals(false, seq.isValidLetter('B'));
	}

	@Test
	public void reverseComplement() {
		char[] mychar = new char[] { 'T', 'C', 'A', 'G', 'A', 'T', 'G', 'G', 'A', 'C', 'A', 'A', 'G', 'G', 'C' };
		DNASequence sequence = new DNASequence(mychar);
		sequence.reverseComplement();
		assertArrayEquals(new char[] { 'G', 'C', 'C', 'T', 'T', 'G', 'T', 'C', 'C', 'A', 'T', 'C', 'T', 'G', 'A' },
				sequence.seqarr);

	}

}
