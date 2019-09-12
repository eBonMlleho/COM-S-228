package edu.iastate.cs228.hw1;

import java.util.Arrays;

/*
 * @author Zhanghao Wen
*/

public class CodingDNASequence extends DNASequence {
	/**
	 * If the character array argument has a character on which the method
	 * isValidLetter() returns false, then it throws an IllegalArgumentException
	 * with the message "Invalid sequence letter for class
	 * edu.iastate.cs228.hw1.CodingDNASequence" Otherwise, the constructor saves a
	 * copy of the character array argument in the eld of its superclass Sequence.
	 * 
	 * @param cdnaarr
	 */
	public CodingDNASequence(char[] cdnaarr) {
		super(cdnaarr);
	}

	/**
	 * If the length of the field character array seqarr is less than 3, then the
	 * method returns false. Otherwise, if the first three characters in the array
	 * seqarr are A/a, T/t, G/g in this order (case insensitive), then the method
	 * returns true. Otherwise, it returns false.
	 * 
	 * 
	 */
	public boolean checkStartCodon() {
		if (seqLength() < 3)
			return false;
		char[] a2 = new char[3];
		a2[0] = Character.toLowerCase(getSeq()[0]);
		a2[1] = Character.toLowerCase(getSeq()[1]);
		a2[2] = Character.toLowerCase(getSeq()[2]);
		if (Arrays.equals(new char[] { 'a', 't', 'g' }, a2))
			return true;
		else {
			return false;
		}
	}

	/**
	 * The method throws an RuntimeException exception with the message "No start
	 * codon" if a call to the method checkStartCodon() returns false. Otherwise,
	 * the method translates the coding sequence in the character array seqarr into
	 * a protein sequence by calling the private method getAminoAcid on every codon
	 * in the coding sequence. The translation stops if the method getAminoAcid
	 * returns the character `$', which is not part of the protein sequence.
	 * Otherwise, the translation stops when the end of the array seqarr is reached.
	 * The method returns the protein sequence in a new character array, where the
	 * length of the protein sequence is equal to the length of the array. Note that
	 * the first codon is a string consisting of the three characters in seqarr[0],
	 * seqarr[1] and seqarr[2]; the second codon is made of seqarr[3], seqarr[4] and
	 * seqarr[5]; and so on. The last codon may contain one or two characters if the
	 * length of the array seqarr is not a multiple of 3.
	 * 
	 * @return
	 */
	public char[] translate() {
		StringBuilder stringBuilder = new StringBuilder();
		StringBuilder codon = new StringBuilder();

		if (!checkStartCodon())
			throw new RuntimeException("No start codon");

		else {
			for (int i = 0; i < seqLength() - 3; i = i + 3) {
				for (int j = i; j < i + 3; j++)
					codon.append(getSeq()[j]);
				if (getAminoAcid(codon.toString()) != '$') {
					stringBuilder.append(getAminoAcid(codon.toString()));
					codon.setLength(0);
				} else
					break;

			}
		}
		// if (seqLength() / 3 != 0)
		// stringBuilder.append('$');

		return stringBuilder.toString().toCharArray();

	}

	/**
	 * If the string argument codon encodes an amino acid, then the method returns
	 * the character representing the amino acid. Otherwise, it returns the
	 * character `$'. An implementation of this method is provided and should be
	 * used without any change to the code.
	 * 
	 * @param codon
	 * @return aa
	 */
	private char getAminoAcid(String codon) {
		if (codon == null)
			return '$';
		char aa = '$';
		switch (codon.toUpperCase()) {
		case "AAA":
			aa = 'K';
			break;
		case "AAC":
			aa = 'N';
			break;
		case "AAG":
			aa = 'K';
			break;
		case "AAT":
			aa = 'N';
			break;

		case "ACA":
			aa = 'T';
			break;
		case "ACC":
			aa = 'T';
			break;
		case "ACG":
			aa = 'T';
			break;
		case "ACT":
			aa = 'T';
			break;

		case "AGA":
			aa = 'R';
			break;
		case "AGC":
			aa = 'S';
			break;
		case "AGG":
			aa = 'R';
			break;
		case "AGT":
			aa = 'S';
			break;

		case "ATA":
			aa = 'I';
			break;
		case "ATC":
			aa = 'I';
			break;
		case "ATG":
			aa = 'M';
			break;
		case "ATT":
			aa = 'I';
			break;

		case "CAA":
			aa = 'Q';
			break;
		case "CAC":
			aa = 'H';
			break;
		case "CAG":
			aa = 'Q';
			break;
		case "CAT":
			aa = 'H';
			break;

		case "CCA":
			aa = 'P';
			break;
		case "CCC":
			aa = 'P';
			break;
		case "CCG":
			aa = 'P';
			break;
		case "CCT":
			aa = 'P';
			break;

		case "CGA":
			aa = 'R';
			break;
		case "CGC":
			aa = 'R';
			break;
		case "CGG":
			aa = 'R';
			break;
		case "CGT":
			aa = 'R';
			break;

		case "CTA":
			aa = 'L';
			break;
		case "CTC":
			aa = 'L';
			break;
		case "CTG":
			aa = 'L';
			break;
		case "CTT":
			aa = 'L';
			break;

		case "GAA":
			aa = 'E';
			break;
		case "GAC":
			aa = 'D';
			break;
		case "GAG":
			aa = 'E';
			break;
		case "GAT":
			aa = 'D';
			break;

		case "GCA":
			aa = 'A';
			break;
		case "GCC":
			aa = 'A';
			break;
		case "GCG":
			aa = 'A';
			break;
		case "GCT":
			aa = 'A';
			break;

		case "GGA":
			aa = 'G';
			break;
		case "GGC":
			aa = 'G';
			break;
		case "GGG":
			aa = 'G';
			break;
		case "GGT":
			aa = 'G';
			break;

		case "GTA":
			aa = 'V';
			break;
		case "GTC":
			aa = 'V';
			break;
		case "GTG":
			aa = 'V';
			break;
		case "GTT":
			aa = 'V';
			break;

		case "TAA":
			aa = '$';
			break;
		case "TAC":
			aa = 'Y';
			break;
		case "TAG":
			aa = '$';
			break;
		case "TAT":
			aa = 'Y';
			break;

		case "TCA":
			aa = 'S';
			break;
		case "TCC":
			aa = 'S';
			break;
		case "TCG":
			aa = 'S';
			break;
		case "TCT":
			aa = 'S';
			break;

		case "TGA":
			aa = '$';
			break;
		case "TGC":
			aa = 'C';
			break;
		case "TGG":
			aa = 'W';
			break;
		case "TGT":
			aa = 'C';
			break;

		case "TTA":
			aa = 'L';
			break;
		case "TTC":
			aa = 'F';
			break;
		case "TTG":
			aa = 'L';
			break;
		case "TTT":
			aa = 'F';
			break;
		default:
			aa = '$';
			break;
		}
		return aa;
	}
}
