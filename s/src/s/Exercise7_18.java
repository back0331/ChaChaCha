package s;

public class Exercise7_18 {
	public static void action(Robot r){
		
		
		
		if(r instanceof DanceRobot){		//�޾ƿ� r�� ����� ~�� �ν��Ͻ���? 
			DanceRobot dr = new DanceRobot();
			dr.dance();
		}
		
		if(r instanceof SingRobot){
			SingRobot sr = new SingRobot();
			sr.sing();
		}
		if(r instanceof DrawRobot){
			DrawRobot wr = new DrawRobot();
			wr.draw();
		}
		
		
		
	}
	
	public static void main(String[] args){
		Robot dr= new DanceRobot();
		Robot sr= new SingRobot();
		Robot wr= new DrawRobot();
		Robot robot[] ={dr ,sr, wr};
		
		for(int i=0; i< robot.length; i++){
			Exercise7_18.action(robot[i]);	
				//���� Ŭ�����̱� ������ Exercise7_18. �Ƚᵵ �ȴ�.
			
		}
	}
}

class Robot{}

class DanceRobot extends Robot{
	void dance(){
		System.out.println("���� ��");
	}
}

class SingRobot extends Robot{
	void sing(){
		System.out.println("�뷡�� �մϴ�.");
	}
}

class DrawRobot extends Robot{
	void draw(){
		System.out.println("�׸��� �׸��ϴ�.");
	}
}