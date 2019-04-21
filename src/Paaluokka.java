
public class Paaluokka {
	public static void main(String[] args){
		Soinnut soinnut = new Soinnut();
		soinnut.arvoA();
		for	(int i = 0; i<4; i++) {
			System.out.println(soinnut.soinnutA[i]);
		}
		soinnut.arvoB();
		for	(int i = 0; i<4; i++) {
			System.out.println(soinnut.soinnutB[i]);
		}
		for	(int i = 0; i<4; i++) {
			System.out.println(soinnut.soinnutA[i]);
		}
		soinnut.arvoC();
		for	(int i = 0; i<4; i++) {
			System.out.println(soinnut.soinnutC[i]);
		}
	}	
}
