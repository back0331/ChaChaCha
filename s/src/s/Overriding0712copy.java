package s;
class Speaker{
	private int volumeRate;
	
	public void showCurrentState(){
		System.out.println("���� ũ��:"+volumeRate);
		
	}
	public void setVolume(int vol){
		volumeRate=vol;
	}
}//Speaker class
class BaseEnSpeaker extends Speaker{
	private int baseRate;
	
	//showCurrentState�޼ҵ带 �������̵��Ͻÿ�.
	//�߰��� ���� ��¹� System.out.printl("���̽� ũ��: "+baseRate);
	public void showCurrentState(){
		System.out.println("���̽� ũ��:"+baseRate);
	}
	//�̰� 
	
	public void setBaseRate(int base){
		baseRate=base;
	}
}//BAseEnSpeaker class

public class Overriding0712copy {
	public static void main(String[] args) {
		//���� �� ��ü �� �ڼհ���ü�� ������ �� ���� bs�� �����Ͻÿ�.
		
		BaseEnSpeaker bs = new BaseEnSpeaker();
			//�׷� �ƺ��� �ڼ� �װ� ����°�? 
		bs.setVolume(10);
		bs.setBaseRate(20); 
		bs.showCurrentState();
	}
}


//�ƺ��� �Ƶ� ���°�.
//�Ƶ��� ��ӹ޴°� �����δ� �ϳ��� �� �޾ƿ��°ǰ�

//������
//�̱���

