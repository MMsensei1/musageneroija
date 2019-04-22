import java.util.ArrayList;

public class Melodia {
	Apu apuri = new Apu();
	public ArrayList<Motiivi> osaA;
	public ArrayList<Motiivi> osaB;
	public ArrayList<Motiivi> osaC;
	
	public void luoMelodia(Soinnut soinnut) {
		osaA = luoMelodiaOsa();
		osaB = luoMelodiaOsa();
		osaC = luoMelodiaOsa();
		
		arvoAlukkeet(osaA, soinnut.annaRakenne());
		arvoAlukkeet(osaB, soinnut.annaRakenne());
		arvoAlukkeet(osaC, soinnut.annaRakenne());
		
		tarkistaAlukkeet(osaA, osaB, osaC, soinnut.annaRakenne());
		
		arvoRytmit(osaA);
	}
	
	public ArrayList<Motiivi> luoMelodiaOsa() {
		ArrayList<Motiivi> melodiaOsa = new ArrayList<Motiivi>();
		
		//Arpoo A:n rakenteen: aa(1), ab(2), a + pitka(3)
		int rakenne = apuri.arpoja(new int[] {1, 1, 2, 1, 3, 1});
		if (rakenne == 1) {
			//Motiivilista1 + Motiivilista1
			ArrayList<Motiivi> a = luoMotiiviLista();
			for (int j = 0; j<2; j++) {
				for (int i = 0; i<a.size(); i++) {
					melodiaOsa.add(new Motiivi(a.get(i)));
				}
			}
		}
		else if (rakenne == 2) {
			//Motiivilista1 + Motiivilista2
			ArrayList<Motiivi> a = luoMotiiviLista();
			ArrayList<Motiivi> b = luoMotiiviLista();
			for (int i = 0; i<a.size(); i++) {
				melodiaOsa.add(new Motiivi(a.get(i)));
			}
			for (int i = 0; i<b.size(); i++) {
				melodiaOsa.add(new Motiivi(b.get(i)));
			}
		}
		else {
			//Motiivilista1 + SuperPitkäMotiivi
			ArrayList<Motiivi> a = luoMotiiviLista();
			for (int i = 0; i<a.size(); i++) {
				melodiaOsa.add(new Motiivi(a.get(i)));
			}
			melodiaOsa.add(new Motiivi("superPitka", 8));
		}
		
		return melodiaOsa;
	
	}
	
	public ArrayList<Motiivi> luoMotiiviLista(){
		ArrayList<Motiivi> palautus = new ArrayList<Motiivi>();
		
		//Arpoo motiivilistan rakenteen: aa(1), ab(2), aaaa(3), a+pitka(4), pitka+a(5)
		int rakenne = apuri.arpoja(new int[] {1, 1, 2, 1, 3, 1, 4, 1, 5, 1});
		if (rakenne == 1) {
			//Motiivi1 + Motiivi1
			palautus.add(new Motiivi("a", 4));
			palautus.add(new Motiivi("a2", 4));
		}
		else if (rakenne == 2) {
			//Motiivi1 + Motiivi2
			palautus.add(new Motiivi("a", 4));
			palautus.add(new Motiivi("b", 4));
		}
		else if (rakenne == 3) {
			//LyhytMotiivi * 4
			palautus.add(new Motiivi("a", 2));
			palautus.add(new Motiivi("a2", 2));
			palautus.add(new Motiivi("a2", 2));
			palautus.add(new Motiivi("a2", 2));
		}
		else if (rakenne == 4) {
			palautus.add(new Motiivi("a", 4));
			palautus.add(new Motiivi("pitka", 4));
		}
		else {
			palautus.add(new Motiivi("pitka", 4));
			palautus.add(new Motiivi("a", 4));
		}
		
		return palautus;
	}
	
	public void arvoAlukkeet(ArrayList<Motiivi> a, int rakenne) {
		for (int i = 0; i<a.size(); i++) {
			//Jos nimi on a2, niin kopioidaan edellisen ominaisuudet lukuunottamatta onkoSeuraavaAluke
			if (a.get(i).annaNimi().equals("a2")) {
				a.set(i, new Motiivi(a.get(i-1)));
				
				//Edelliselle motiiville annetaan tieto alukkeesta
				if (a.get(i).annaAluke() != 0) {
					if (i != 0) {
						a.get(i-1).asetaSeuraavallaAluke(a.get(i).annaAluke());
					}
				}
			}
			
			else {
				//Arvotaan onko aluke(1), ei aluketta eika viivetta (2), vai viive (3)
				int x = apuri.arpoja(new int[] {1, 3, 2, 3, 3, 1});
				if (x == 1) {
					//Jos lyhyt motiivi, alukkeen pituus lyhyt (1)
					if (a.get(i).annaPituus() == 2) {
						a.get(i).asetaAluke(1);
					}
					
					//Muulloin arvotaan onko aluke lyhyt (1) vai pitka (2)
					else {
						a.get(i).asetaAluke(apuri.arpoja(new int[] {1, 1, 2, 1}));
					}
					
					//Edelliselle motiiville kerrotaan alukkeesta
					if (i != 0) {
						a.get(i-1).asetaSeuraavallaAluke(a.get(i).annaAluke());
					}
				}
				else if (x == 2) {}
				else {
					a.get(i).asetaViive(true);
				}
			}
		}
	}
	
	//Otetaan huomioon myos osien loput ja alut alukkeiden suhteen, ja lisataan naista tiedot
	public void tarkistaAlukkeet(ArrayList<Motiivi> a, ArrayList<Motiivi> b, ArrayList<Motiivi> c, int rakenne) {
		if (a.get(0).annaAluke() != 0 && rakenne == 1) {
			b.get(b.size()-1).asetaSeuraavallaAluke(a.get(0).annaAluke());
		}
		
		if (a.get(0).annaAluke() != 0 && rakenne == 2) {
			a.get(a.size()-1).asetaSeuraavallaAluke(a.get(0).annaAluke());
		}
		
		if (b.get(0).annaAluke() != 0) {
			a.get(a.size()-1).asetaSeuraavallaAluke(b.get(0).annaAluke());
		}
		
		if (c.get(0).annaAluke() != 0 && rakenne == 1) {
			a.get(a.size()-1).asetaSeuraavallaAluke(c.get(0).annaAluke());
		}
		
		if (c.get(0).annaAluke() != 0 && rakenne == 2) {
			b.get(b.size()-1).asetaSeuraavallaAluke(c.get(0).annaAluke());
		}
		
	}
	
	//Arvotaan motiivien rytmit
	public void arvoRytmit(ArrayList<Motiivi> a) {
		boolean onkoSama = apuri.onkoSama(a);
		
		for (int i = 0; i<a.size(); i++) {
			if (a.get(i).annaNimi().equals("a2")) {
				//kopio
			}
			else if (i >= a.size()/2 && onkoSama) {
				//kopio
			}
			else {
				alukeLuoja(a.get(i));
				rytmiLuoja(a.get(i));
				//viiveLisaaja(a.get(i));
			}
		}
		
		for (int i = 0; i<a.size(); i++) {
			//lopunPoistaja(a.get(i));
		}
	}
	
	public void rytmiLuoja(Motiivi m) {
		
		//Jos pitka, asetetaan 1/1 nuotti
		if (m.annaNimi().equals("pitka")) {
			ArrayList<Integer> a = new ArrayList<Integer>(m.annaRytmi());
			a.add(8);
			m.asetaRytmi(a);
		}
		
		//Jos superPitka, asetetaan 2/1 nuotti
		else if (m.annaNimi().equals("superPitka")) {
			ArrayList<Integer> a = new ArrayList<Integer>(m.annaRytmi());
			a.add(16);
			m.asetaRytmi(a);
		}
		
		//Muulloin arvotaan rytmi
		else {
			ArrayList<Integer> a = new ArrayList<Integer>(m.annaRytmi());
			
			for (int i = 0; i < m.annaPituus()/2; i++) {
				//Arvotaan rytmi: 1/2 (1), 1/4 * 2 (2), 1/8 * 4 (3), 1/4 + 1/8 * 2 (4), 1/8 * 2 + 1/4 (5)
				int x = apuri.arpoja(new int[] {1, 1, 2, 1, 3, 1, 4, 1, 5, 1});
				if (x == 1) {
					a.add(4);
				}
				else if (x == 2) {
					a.add(2);
					a.add(2);
				}
				else if (x == 3) {
					a.add(1);
					a.add(1);
					a.add(1);
					a.add(1);
				}
				else if (x == 4) {
					a.add(2);
					a.add(1);
					a.add(1);
				}
				else if (x == 5) {
					a.add(1);
					a.add(1);
					a.add(2);
				}
			}
			
			m.asetaRytmi(a);
		}
	}
	
	public void alukeLuoja(Motiivi m) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		
		if (m.annaAluke() == 0) {
			//Tyhja aluke
			a.add(40);
			m.asetaRytmi(a);
		}
		
		else if (m.annaAluke() == 1) {
			//Arvotaan onko aluke 1/8 * 2 (1), 1/4 (2) vai 1/8 (3)
			int x = apuri.arpoja(new int[] {1, 1, 2, 1, 3, 1});
			if (x == 1) {
				a.add(20);
				a.add(1);
				a.add(1);
			}
			if (x == 2) {
				a.add(20);
				a.add(2);
			}
			if (x == 1) {
				a.add(30);
				a.add(1);
			}
			
			m.asetaRytmi(a);
		}
		
		else if (m.annaAluke() == 2) {
			//Arvotaan onko aluke 1/8 * 4 (1), 1/8 * 3 (2), 1/4 * 2 (3), 1/8 * 2 + 1/4 (4) vai 1/4 + 1/8 * 2 (5)
			int x = apuri.arpoja(new int[] {1, 1, 2, 1, 3, 1, 4, 1 , 5, 1});
			if (x == 1) {
				a.add(1);
				a.add(1);
				a.add(1);
				a.add(1);
			}
			else if (x == 2) {
				a.add(10);
				a.add(1);
				a.add(1);
				a.add(1);
			}
			else if (x == 3) {
				a.add(2);
				a.add(2);
			}
			else if (x == 4) {
				a.add(1);
				a.add(1);
				a.add(2);
			}
			else if (x == 5) {
				a.add(2);
				a.add(1);
				a.add(1);
			}
			
			m.asetaRytmi(a);
		}
	}
	
	
	
	public void lisaaViive(Motiivi m) {
		if (m.annaRytmi().get(1) == 4) {}
		else {
			ArrayList<Integer> a = m.annaRytmi();
			a.set(1, a.get(1)*10);
			m.asetaRytmi(a);
		}
	}
}
