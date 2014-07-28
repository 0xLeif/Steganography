package org.apcs.zeriksen.binary;

public class BinaryNum{
	private int[] digits;

	public BinaryNum(int num){
		digits = new int[8];
		int q = 0;
		for(int i = num;i > 0;){
			int j = i;
			i /= 2;
			int w = i * 2;
			int u = j - w;
			digits[q] = u;
			q++;
		}
	}

	public BinaryNum(int[] digits){
		this.digits = digits;
	}

	public BinaryNum(char c){
		this((int)c);
	}

	public int[] getDigits(){
		return digits;
	}

	public int toDecimal() {
		int sum = 0;
		for (int i = 0; i < digits.length; i++) {
			sum += digits[i] * Math.pow(2, i);
		}
		return sum;
	}

	public String toString(){
		String sum = "";
		String sum2 = "";
		for(int a : digits){
			sum += a;
		}
		int i = digits.length-1;
		for(int a : digits){
			sum2 += sum.charAt(i);
			i--;
		}
		return sum2;
	}

	public char getChar(){
		System.out.println((char)this.toDecimal() + " to char");
		System.out.println(this.toDecimal());
		return (char)this.toDecimal();
	}
}
