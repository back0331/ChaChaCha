package s;

public class LastIndexOf {
	public static void main(String[] args) {
		String fullPath = "c:\\coool\\work\\siwon.java";
		String path = " ";
		String fileName="";
		
		int xx = fullPath.lastIndexOf("\\");  //�ڿ��� ���� ã�Ƽ� �� ���ڰ� �����ϴ� index�� ���Ѵ�.
		fileName = fullPath.substring(xx+1);	//�Ű����� �ϳ��� ~ �� �ε��� �ڷ� �߶� �����´�.
		path = fullPath.substring(0,xx );
		
		System.out.println("fullPath: "+fullPath);
		System.out.println("path : "+path);
		System.out.println("fileName : "+fileName);
	}
}
