package Test;

public class Lottery {

	public static void main(String[] args) {
		int[] num  = new int[5];
		
		for(int i = 0 ; i< num.length; i++) {
			num[i] = (int)(Math.random()*45)+1;
			
			for(int j = 0; j < i; j++) {
				if(num[i] == num[j]) {
					i--;
				}else {
					int tmp = 0;
					if(num[i] < num[j]) {
						tmp = num[i];
						num[i] = num[j];
						num[j]= tmp;
					}
				}
			}
		}
		
		for(int i = 0 ; i < num.length; i++) {
			System.out.print(num[i]+", ");
		}
	}

}
