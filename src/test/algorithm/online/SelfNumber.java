package test.algorithm.online;

import java.util.HashSet;
import java.util.Set;

public class SelfNumber {
	public static void main(String[] args) {
		
		Set<Integer> generatedNumbers = new HashSet<Integer>();
		int sumSelfNumbers = 0;
		for (int i = 1; i < 5000; i++) {
			generatedNumbers.add(getGeneratedNumber(i));
			if(!generatedNumbers.contains(i)){
				sumSelfNumbers += i;
			}
		}	
		System.out.println("Sum of self-numbers : " + sumSelfNumbers);
	}
	public static int getGeneratedNumber(int k){
		return k + k/1000 + (k%1000)/100 + (k%100)/10 + (k%10);
	}
}
