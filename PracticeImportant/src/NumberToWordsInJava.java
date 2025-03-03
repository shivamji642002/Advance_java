import java.util.Scanner;

public class NumberToWordsInJava {
private static final String[] belowTen= {"","one","two","three","four","five","six","seven","eight","nine"};
private static final String[] belowTwenty= {"ten","eleven","twelve","thirteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
private static final String[] tens= {"","","twenty","thirty","forty","sixty","seventy","eighty","ninety"};
private static final String[] thousands= {"","thousand"};
 public static void main(String[] args) {
	 int num;
	 Scanner sc=new Scanner(System.in);
	 System.out.print("Enter a number : ");
	 num =sc.nextInt();
	 
	 sc.close();
	 if(num<1000) {
	 System.out.print(conertToword(num));
	 }else {
		 System.out.print("invalid credencial");
	 }
 }
public static String conertToword(int num) {
	if(num ==0) {
		return "zero";
	}
	String result =" ";
	
	//Process thousands
	if(num>=1000) {
		result += belowTen[num/1000]+" "+thousands[1]+" ";
		num%=1000;
	}
	
	//Process hundreds
	
	if(num >=100) {
		
       result += belowTen[num/100]+" "+"Hundred";
       num%=100;
	}
	
	//procees tens and twenty
	if(num>=20) {
		result +=tens[num/10]+" ";
		num%=10;
	}
	
	if(num>0) {
		result += belowTen[num]+" ";
		
	}
	
	
	return result;
	
}
 
}
