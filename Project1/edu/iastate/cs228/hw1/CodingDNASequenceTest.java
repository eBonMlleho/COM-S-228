package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CodingDNASequenceTest {

	CodingDNASequence seq;
	char[] shortarr;

	@Before
	public void setup() {

	}

	@Test
	public void testcheckStartCodon() {
		shortarr = new char[] { 'a', 'c' };
		seq = new CodingDNASequence(shortarr);
		assertEquals(false, seq.checkStartCodon());
	}

	@Test
	public void testcheckStartCodonorder() {
		shortarr = new char[] { 't', 'a', 'g' };
		seq = new CodingDNASequence(shortarr);
		assertEquals(false, seq.checkStartCodon());
	}

	@Test
	public void testcheckStartCodonUpperCase() {
		shortarr = new char[] { 'A', 'T', 'G' };
		seq = new CodingDNASequence(shortarr);
		assertEquals(true, seq.checkStartCodon());
	}

	@Test
	public void testtranslate() {
		String str = "ATGCCTCAATAG";
		seq = new CodingDNASequence(str.toCharArray());
		assertEquals("MPQ", new String(seq.translate()));
	}

	// @Test
	// public void testtranslate$() {
	// String str = "ATGCCTCAATAG";
	// seq = new CodingDNASequence(str.toCharArray());
	// assertEquals("MPQ", new String(seq.translate()));
	// }
}
