
public class Paaluokka {
	public static void main(String[] args){
		Soinnut soinnut = new Soinnut();
		
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		
		for(int i = 0; i<50; i++) {
			int x = soinnut.arvoRakenne();
			if (x == 1) a = a+1;
			else if (x == 2) b = b+1;
			else if (x == 3) c = c+1;
			else if (x == 4) d = d+1;
		}
		

		System.out.println("1: " + a);
		System.out.println("2: " + b);
		System.out.println("3: " + c);
		System.out.println("4: " + d);
	}
}
