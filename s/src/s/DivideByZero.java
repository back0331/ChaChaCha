package s;
import java.util.Scanner;


public class DivideByZero extends Exception{
	public static void main(String[] args) {
		System.out.println("�� ���� ���� �Է�:");
		Scanner input=new Scanner(System.in);
		int num1 = input.nextInt();
		int num2 = input.nextInt();
				
		try{		
		int div= num1/num2;
		int na=num1%num2;
		System.out.println(div);
		System.out.println(na);
		//
		}catch(Exception e){
			e.getMessage();
					System.out.println(e.getMessage());
				
		}
		
		System.out.println("���α׷��� �����մϴ�.");
		
		System.out.println(num1);
		System.out.println(num2);
	}
}
