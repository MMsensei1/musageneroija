
public class Paaluokka {
	public static void main(String[] args){
		Soinnut soinnut = new Soinnut();
		soinnut.arvo();
		for (int i = 0; i<soinnut.annaSointukierto().length; i++) {
			System.out.println(soinnut.annaSointukierto()[i]);
		}
	}	
}
