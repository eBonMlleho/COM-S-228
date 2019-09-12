package edu.iastate.cs228.hw1;

public class test {
	public static void main(String[] args) {
		char[] mychar = new char[] { 'g', 'a', 's' };
		char[] another = mychar;
		System.out.println(mychar.toString());
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("asdf");
		stringBuilder.append('c');
		System.out.println(stringBuilder.toString().toCharArray().length);
	}
}
