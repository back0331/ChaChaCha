package s;

class Person{
	private int perID;
	private int milID;
	private int birthYear;
	private int birthMonth;
	private int birthDay;
	
	public Person(int perID, int milID, int birthYear, int birthMonth, int birthDay){  //int ���� 5���� �޾ƿͼ� PersonŬ���� ������ �����ϴ� �����ڸ� ����ÿ�
		 this.perID= perID ;
		 this.milID= milID  ;
		 this.birthYear=birthYear  ;
		 this.birthMonth=birthMonth;
		 this.birthDay =birthDay;
		
		
	}
	
	public Person(int pID, int bYear, int bMonth, int bDay){//int ���� 4���� �޾ƿͼ� �ٷ� ���� Person �����ڿ� ������ �� �ֵ���
					 											//���ο� �����ڸ� ����ÿ�
				
		/*Person(int,int,int,int,int)=this;  ���� �� ��.*/
		this(pID,0, bYear,bMonth,bDay);		
	}
	
	public void showInfo(){
		System.out.println("�ι� : "+perID);
		System.out.println("������� : "+birthYear+"/"+birthMonth+"/"+birthDay);
		if(milID != 0){
			System.out.println("����: "+milID+"\n");
		}else{
			System.out.println("���� ���� ����.\n");
		}
	}
}

public class Overloading0712copy {
	public static void main(String[] args){
		Person man= new Person(951203, 990102, 1995, 12, 3); //int ���� 5���� �ָ鼭 Perso��ü�� �����Ͻÿ�	}
		Person woman= new Person(991107,1999,11,7); //int ����Person 4���� �ָ鼭Perso��ü�� �����Ͻÿ�
		man.showInfo();
		woman.showInfo();
	}
}

