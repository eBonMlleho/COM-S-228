package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GenomicDNASequenceTest {
	GenomicDNASequence seq;
	int[] ardemo;

	@Before
	public void setup() {
		ardemo = new int[] { 1, 5, 8, 10, 13, 16 };
		String demodna = new String("AATGCCAGTCAGCATAGCG");
		seq = new GenomicDNASequence(demodna.toCharArray());

	}

	@Test
	public void testextractExons() {
		char[] test = new char[] { 'A', 'T', 'G', 'C', 'C', 'T', 'C', 'A', 'A', 'T', 'A', 'G' };
		Assert.assertArrayEquals(test, seq.extractExons(ardemo));
	}

	@Test
	public void testseqLength() {
		assertEquals(19, seq.seqLength());
	}
}
