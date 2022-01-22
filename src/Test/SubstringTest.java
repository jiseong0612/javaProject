package Test;

import java.util.UUID;

public class SubstringTest {
	public static void main(String[] args) {
		UUID uuid = UUID.randomUUID();
		
		String fileName = uuid.toString()+"_"+"test.txt";
		System.out.println(fileName);
		System.out.println(fileName.lastIndexOf("_")+1);
		
		//substring(num) 제외한 나머지 값을 리턴!!
		String originalName = fileName.substring(fileName.lastIndexOf("_")+1);
		System.out.println(originalName);
		
		System.out.println("unhappy".substring(2));
	}
}
