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
			System.out.println("Vääränlainen sointulistaaja1 syöte");
		}
		
		return palautus;
	}
	
	//muuttaa parametrin String == "13" muotoon int[] = {1,10,3,9,2,1,4,1,5,1,6,1}
	//String.lenght() == 2
	//Parametri voi sisaltaa vain lukuja
	public int[] sointuListaaja2(String a) {
		int[] palautus = new int[12];
		ArrayList<Integer> kaytetytLuvut = new ArrayList<Integer>();
		
		try {
			int x1 = Integer.parseInt(a.substring(0, 1));
			int x2 = Integer.parseInt(a.substring(1, 2));
			
			
			kaytetytLuvut.add(x1);
			kaytetytLuvut.add(x2);
			
			palautus[0] = x1;
			palautus[1] = 10;
			palautus[2] = x2;
			palautus[3] = 9;
			
			for(int i = 2; i<6; i++) {
				for(int j = 1; j<=6; j++) {
					if (kaytetytLuvut.contains(j)) continue;
					else {
						palautus[i*2]=j;
						kaytetytLuvut.add(j);
						break;
					}
				}
				palautus[i*2 + 1] = 1;
			}
		}
		
		catch (Exception e) {
			System.out.println("Vääränlainen sointulistaaja2 syöte");
		}
		
		return palautus;
	}
	
	//Tarkistaa onko parametrina annettu melodianpatka rakenteeltaan AA
	//Palauttaa true jos rakenne on AA, muulloin false
	public boolean onkoSama(ArrayList<Motiivi> a) {
		boolean onkoSama = false;
		
		if (a.get(0).annaPituus() == 2 && a.size() == 8) {
			onkoSama = true;
			for (int i = 0; i<(a.size()/2); i++) {
				if (!a.get(i).equals(a.get(i+4))) onkoSama = false;
			}
		}
		
		else if (a.get(0).annaPituus() == 4 && a.size() == 4) {
			onkoSama = true;
			for (int i = 0; i<(a.size()/2); i++) {
				if (!a.get(i).equals(a.get(i+2))) onkoSama = false;
			}
		}
		
		return onkoSama;
		
	}
	
	public ArrayList<Motiivi> yhdistaMelodiat (int rakenne, ArrayList<Motiivi> a, ArrayList<Motiivi> b, ArrayList<Motiivi> c){
		 ArrayList<Motiivi> palautus = new  ArrayList<Motiivi>();
		 
		 if (rakenne == 1) {
			 for (Motiivi m : a) {
				 palautus.add(m);
			 }
			 for (Motiivi m : b) {
				 palautus.add(m);
			 }
			 for (Motiivi m : a) {
				 palautus.add(m);
			 }
			 for (Motiivi m : c) {
				 palautus.add(m);
			 }
		 }
		 
		 else if (rakenne == 2) {
			 for (Motiivi m : a) {
				 palautus.add(m);
			 }
			 for (Motiivi m : a) {
				 palautus.add(m);
			 }
			 for (Motiivi m : b) {
				 palautus.add(m);
			 }
			 for (Motiivi m : c) {
				 palautus.add(m);
			 }
		 }
		 
		 return palautus;
	}
}
