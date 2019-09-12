package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SequenceTest {
	private char[] chararr;
	private Sequence seq;

	@Before
	public void setup() // runs before every test case
	{
		chararr = new char[] { 'a', 'c', 'g' };
		seq = new Sequence(chararr);
	}

	@Test
	public void testIsValidLetter() {
		assertEquals(false, seq.isValidLetter('1'));
	}

	@Test
	public void testseqLength() {
		assertEquals(3, seq.seqLength());
	}

	@Test
	public void testgetSeq() {
		char[] test = new char[] { 'a', 'c', 'g' };
		Assert.assertArrayEquals(test, seq.getSeq());
	}

	@Test
	public void testtoString() {
		assertEquals("acg", seq.toString());
	}

	@Test
	public void testequals() {
		char[] test = new char[] { 'A', 'C', 'G' };
		DNASequence dnaSeq = new DNASequence(test);
		assertEquals(true, seq.equals(dnaSeq));
	}
}
