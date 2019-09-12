package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ProteinSequenceTest {
	ProteinSequence seq;
	char[] chararr;

	@Before
	public void setup() {
		String str = "ATGCCTCAATAG";
		chararr = str.toCharArray();
		seq = new ProteinSequence(chararr);
	}

	@Test
	public void testisValidLetter() {
		assertEquals(false, seq.isValidLetter('1'));
	}

	public void testisValidLetter1() {
		assertEquals(true, seq.isValidLetter('B'));
	}
}
