package s;

import java.util.Scanner;

public class Exercise {
	public static void main(String[] args) {
		
		int  r = (int)(Math.random()*100)+1;
		System.out.println(r);
		int input= 0;
		int count = 0;
		
		System.out.println("INPUT VALUE BETWEEN 1 AND 100");
		do{
			count ++;
			try{
				input = new Scanner(System.in).nextInt();
				
			}catch(Exception e){
				System.out.println("no use this value");
				continue;   ///????
				
			}
			
			if(r < input){
				System.out.println("INPUT m1ore big value");   
			}else if(r > input){
				System.out.println("INPUT more small value");
			}else{
				System.out.println("right");
				System.out.println(count);
				break;
			}
			
		}while(true);
	}
}
