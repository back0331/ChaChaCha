package s;

public class ArrayCopy_toStringArray {
	public static String fillZero(String src, int length){
		
		//(1) �˸��� �ڵ带 �־� �ϼ��Ͻÿ�.
		
		if(src==null || src.length()==length){
			return src;
		}else if(length <= 0){
			return "";
		}else if(src.length() > length){
			return src.substring(0, length);
		}
		char b[] =new char[length];
		for(int i=0;i<b.length;i++){
			b[i]=0;
		}
		
	
			
		char c[] =toCharArray(src);
		b.arrayCopy(c);
				
				
		
		System.arraycopy(src, length, null, length, 0);
		//1. src�� null�̰ų� src.length() �� length�� ������ src�� �״�� ��ȯ�Ѵ�. if
		
		
		//2.length�� ���� 0���� ���ų� ������ �� ���ڿ�("")�� ��ȯ�Ѵ�. else if
		//3.src�� ���̰� length�� ������ ũ�� src�� length��ŭ �߶� ��ȯ�Ѵ�. else if
		
		//4. ���̰� length�� char�迭�� �����Ѵ�.
		//5. 4���� ������ char�迭�� '0'���� ä���.
		
		//6. src���� ���ڹ迭�� �̾Ƴ��� 4���� ������ �迭�� �����Ѵ�.
		//7. 4���� ������ �迭�� String�� �������ؼ� ��ȯ�Ѵ�.
		
	}
	
	public static void main(String[] args){
		String src = "12345";
		System.out.println(fillZero(src,10));
		System.out.println(fillZero(src,-1));
		System.out.println(fillZero(src,3));
	}
}
