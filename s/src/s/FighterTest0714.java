package s;

public class FighterTest0714 {
	public static void main(String[] args) {
		Fighter f = new Fighter();
		Unit u = new Unit();
		
		int x = 10;
		int y = 10;
		
		if(f instanceof Unit){
			System.out.println("f�� UnitŬ������ �ڼ��Դϴ�.");
		}
		if(f instanceof Fightable){
			System.out.println("f�� Fightable�� Ŭ������ �ڼ��Դϴ�.");
		}
		if(f instanceof Movable){
			System.out.println("f�� MoveableŬ������ �ڼ��Դϴ�.");
		}
		if(f instanceof Attackable){
			System.out.println("f Attackable��Ŭ������ �ڼ��Դϴ�");
		}
		if(f instanceof Object){
			System.out.println("f�� ObjectŬ������ �ڼ��Դϴ�.");
		}
		f.move(x,y);
		f.attack(u);
	}
}
//FighterŬ������ �������Դϴ�. 
//class�� �������ּ���. �̸��� Fighter, UnitŬ���� ���, Fightable ����.
class Fighter extends Unit implements Fightable{
public void move(int x, int y){
	x =10;
	y = 10;
	System.out.println("x="+x+" y="+y);
	System.out.println("Movable�������̽��� move�޼ҵ� ���� ����");
	
}
public void attack(Unit u){
	currentHP=50;
	x=10;
	y=10;
	System.out.println("HP:"+currentHP+" x="+x+" y="+y);
	System.out.println("Attackable �������̽��� attack�޼ҵ� ����");
	
}
}

class Unit{
	int currentHP;
	int x;
	int y;
	
}

interface Fightable extends Movable, Attackable{}
interface Movable{void move(int x, int y);}
interface Attackable{void attack(Unit uu); }
/*
����Ÿ���� ���� move method�� �ȿ��������....?
*/


//�������̽� ����ü �� ���°���?? �������̽� �κ��� ���ٰ��ص� �Ȱ��� ���ư��°ǵ� ~ 

