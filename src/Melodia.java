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
		
		arvoAlukkeet(osaA);
		arvoAlukkeet(osaB);
		arvoAlukkeet(osaC);
		
		tarkistaAlukkeet(osaA, osaB, osaC, soinnut.annaRakenne());
		
		//arvoRytmit(osaA);
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
	
	public void arvoAlukkeet(ArrayList<Motiivi> a) {
		for (int i = 0; i<a.size(); i++) {
			//Jos nimi on a2, niin kopioidaan edellisen ominaisuudet
			if (a.get(i).annaNimi().equals("a2")) {
				a.set(i, new Motiivi(a.get(i-1)));
				
				if (a.get(i).annaAluke()) {
					if (i != 0) {
						a.get(i-1).asetaSeuraavallaAluke(true);
					}
				}
			}
			
			else {
				//Arvotaan onko aluke(1), ei aluketta eika viivetta (2), vai viive (3)
				int x = apuri.arpoja(new int[] {1, 3, 2, 3, 3, 1});
				if (x == 1) {
					a.get(i).asetaAluke(true);
					//a.get(i).asetaViive(false);
					
					if (i != 0) {
						a.get(i-1).asetaSeuraavallaAluke(true);
					}
				}
				
				else if (x == 2) {
					//a.get(i).asetaAluke(false);
					//a.get(i).asetaViive(false);
				}
				
				else {
					//a.get(i).asetaAluke(false);
					a.get(i).asetaViive(true);
				}
			}
		}
	}
	
	public void tarkistaAlukkeet(ArrayList<Motiivi> a, ArrayList<Motiivi> b, ArrayList<Motiivi> c, int rakenne) {
		if (a.get(0).annaAluke() && rakenne == 1) {
			b.get(b.size()-1).asetaSeuraavallaAluke(true);
		}
		
		if (a.get(0).annaAluke() && rakenne == 2) {
			a.get(a.size()-1).asetaSeuraavallaAluke(true);
		}
		
		if (b.get(0).annaAluke()) {
			a.get(a.size()-1).asetaSeuraavallaAluke(true);
		}
		
		if (c.get(0).annaAluke() && rakenne == 1) {
			a.get(a.size()-1).asetaSeuraavallaAluke(true);
		}
		
		if (c.get(0).annaAluke() && rakenne == 2) {
			b.get(b.size()-1).asetaSeuraavallaAluke(true);
		}
		
	}
	
	public void arvoRytmit(ArrayList<Motiivi> a) {
		boolean onkoSama = apuri.onkoSama(a);
	}
}
