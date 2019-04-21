import java.util.Random;
import java.util.ArrayList;

public class Apu {
	Random random = new Random();
	
	//Parametrina int[], joka on muotoa: arvo, maara, arvo, maara
	//int[].length % 2 == 0
	//Esim. 1, 2, 3, 1 vastaa tilannetta, jossa kolmesta arvottavasta kortista kahdessa on arvo 1, ja yhdessä on arvo 3
	public int arpoja(int[] a) {
		ArrayList<Integer> arvottavat = new ArrayList<Integer>();
		
		for (int i = 0; i < a.length; i = i+2) {
			for (int j = 0; j<a[i+1]; j++) {
				arvottavat.add(a[i]);
			}
		}
		
		int x = random.nextInt(arvottavat.size());
		return arvottavat.get(x);
	}
	
	//muuttaa parametrin String == "123456" muotoon int[] = {1,6,2,5,3,4,4,3,5,2,6,1}
	//String.lenght() == 6
	//Parametri voi sisaltaa vain lukuja
	public int[] sointuListaaja(String a) {
		int[] palautus = new int[12];
		
		try {
			int x=6;
			for(int i = 0; i<6; i++) {
				palautus[i*2] = Integer.parseInt(a.substring(i, i+1));
				palautus[i*2 + 1] = x;
				x = x-1;
			}
		}
		
		catch (Exception e) {
			System.out.println("Vääränlainen sointulistaajan syöte");
		}
		
		return palautus;
	}
}
