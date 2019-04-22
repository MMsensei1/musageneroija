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
		arvoRytmit(osaB);
		arvoRytmit(osaC);
		
		for (Motiivi m : osaA) {
			arvoSavelet(m);
		}
		for (Motiivi m : osaB) {
			arvoSavelet(m);
		}
		for (Motiivi m : osaC) {
			arvoSavelet(m);
		}
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
			//Jos nimi on a2, niin kopioidaan edellisen ominaisuudet lukuunottamatta onkoSeuraavaAluke ja Nimi
			if (a.get(i).annaNimi().equals("a2")) {
				Motiivi b = new Motiivi(a.get(i-1));
				b.asetaSeuraavallaAluke(a.get(i).annaSeuraavallaAluke());
				b.asetaNimi(a.get(i).annaNimi());
				a.set(i, b);
				
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
				//kopioidaan edellisen rytmi
				Motiivi b = new Motiivi(a.get(i-1));
				a.get(i).asetaRytmi(b.annaRytmi());
				
			}
			else if (i >= a.size()/2 && onkoSama) {
				//kopioidaan rytmi osan alkupuolelta
				Motiivi b = new Motiivi(a.get(((i+1) / 2) - 1));
				a.get(i).asetaRytmi(b.annaRytmi());
			}
			else {
				alukeLuoja(a.get(i));
				rytmiLuoja(a.get(i));
				viiveLisaaja(a.get(i));
			}
		}
		
		//Asetetaan loppuun tilaa seuraavaa aluketta varten
		for (int i = 0; i<a.size(); i++) {
			lopunPoistaja(a.get(i));
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
			if (x == 3) {
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
	
	
	
	public void viiveLisaaja(Motiivi m) {
		if (m.annaViive()) {
			
			//Jos pitka nuotti, ei viivetta
			if (m.annaRytmi().get(1) == 4 || m.annaNimi().equals("pitka") || m.annaNimi().equals("superPitka")) {}
			
			//Muulloin poistetaan ensimmainen nuotti
			else {
				ArrayList<Integer> a = m.annaRytmi();
				a.set(1, a.get(1)*10);
				m.asetaRytmi(a);
			}
		}
	}
	
	public void lopunPoistaja(Motiivi m) {
		if (m.annaSeuraavallaAluke() == 0) {}
		
		else if (m.annaSeuraavallaAluke() == 1) {
			//Jos viimeinen nuotti 1/8, poistetaan kaksi viimeistä
			if (m.annaRytmi().get(m.annaRytmi().size()-1) == 1) {
				ArrayList<Integer> a = m.annaRytmi();
				a.set(a.size()-1, a.get(a.size()-1)*10);
				a.set(a.size()-2, a.get(a.size()-2)*10);
				m.asetaRytmi(a);
			}
			
			//Jos viimeinen nuotti 1/4, poistetaan se
			else if (m.annaRytmi().get(m.annaRytmi().size()-1) == 2) {
				ArrayList<Integer> a = m.annaRytmi();
				a.set(a.size()-1, a.get(a.size()-1)*10);
				m.asetaRytmi(a);
			}
			
			//Jos viimeinen nuotti 1/2, lyhennetaan
			else if (m.annaRytmi().get(m.annaRytmi().size()-1) == 4) {
				ArrayList<Integer> a = m.annaRytmi();
				a.set(a.size()-1, 2);
				a.add(20);
				m.asetaRytmi(a);
			}
			
			//Jos viimeinen nuotti 1/1, lyhennetaan
			else if (m.annaRytmi().get(m.annaRytmi().size()-1) == 8) {
				ArrayList<Integer> a = m.annaRytmi();
				a.set(a.size()-1, 6);
				a.add(20);
				m.asetaRytmi(a);
			}
			
			//Jos viimeinen nuotti 2/1, lyhennetaan
			else if (m.annaRytmi().get(m.annaRytmi().size()-1) == 16) {
				ArrayList<Integer> a = m.annaRytmi();
				a.set(a.size()-1, 14);
				a.add(20);
				m.asetaRytmi(a);
			}
		}
		
		else if (m.annaSeuraavallaAluke() == 2) {
			//Jos viimeiset nuotit 1/8 * 4, poistetaan nelja viimeista
			if (m.annaRytmi().get(m.annaRytmi().size()-1) == 1 && m.annaRytmi().get(m.annaRytmi().size()-3) == 1) {
				ArrayList<Integer> a = m.annaRytmi();
				a.set(a.size()-1, a.get(a.size()-1)*10);
				a.set(a.size()-2, a.get(a.size()-2)*10);
				a.set(a.size()-3, a.get(a.size()-3)*10);
				a.set(a.size()-4, a.get(a.size()-4)*10);
				m.asetaRytmi(a);
			}
			
			//Jos viimeiset nuotit 1/8 ja 1/4 yhdistelma, poistetaan kolme viimeista
			else if ((m.annaRytmi().get(m.annaRytmi().size()-1) == 1 && m.annaRytmi().get(m.annaRytmi().size()-3) == 2) || (m.annaRytmi().get(m.annaRytmi().size()-1) == 2 && m.annaRytmi().get(m.annaRytmi().size()-3) == 1)) {
				ArrayList<Integer> a = m.annaRytmi();
				a.set(a.size()-1, a.get(a.size()-1)*10);
				a.set(a.size()-2, a.get(a.size()-2)*10);
				a.set(a.size()-3, a.get(a.size()-3)*10);
				m.asetaRytmi(a);
			}
			
			//Jos viimeiset nuotit 1/4*2, poistetaan kaksi viimeista
			if (m.annaRytmi().get(m.annaRytmi().size()-1) == 2 && m.annaRytmi().get(m.annaRytmi().size()-2) == 2) {
				ArrayList<Integer> a = m.annaRytmi();
				a.set(a.size()-1, a.get(a.size()-1)*10);
				a.set(a.size()-2, a.get(a.size()-2)*10);
				m.asetaRytmi(a);
			}
			
			//Jos viimeinen nuotti 1/2, poistetaan se
			else if (m.annaRytmi().get(m.annaRytmi().size()-1) == 4) {
				ArrayList<Integer> a = m.annaRytmi();
				a.set(a.size()-1, a.get(a.size()-1)*10);
				m.asetaRytmi(a);
			}
			
			//Jos viimeinen nuotti 1/1, lyhennetaan
			else if (m.annaRytmi().get(m.annaRytmi().size()-1) == 8) {
				ArrayList<Integer> a = m.annaRytmi();
				a.set(a.size()-1, 4);
				a.add(40);
				m.asetaRytmi(a);
			}
			
			//Jos viimeinen nuotti 2/1, lyhennetaan
			else if (m.annaRytmi().get(m.annaRytmi().size()-1) == 16) {
				ArrayList<Integer> a = m.annaRytmi();
				a.set(a.size()-1, 12);
				a.add(40);
				m.asetaRytmi(a);
			}
		}
	}
	
	//Arvotaan motiivin savelkulku
	public void arvoSavelet(Motiivi m) {
		ArrayList<Integer> s = new ArrayList<Integer>();
		
		//Jos on aluke, arvotaan ensin alukkeen savelkulku
		if (m.annaRytmi().get(0) != 40) {
				int loppu = 0;
				int i = 0;
				while (true) {
					//Jos tauko, ei arvota savelta
					if (m.annaRytmi().get(i) >= 10) {
						loppu = loppu + ((m.annaRytmi().get(i))/(10));
					}
					
					//Muulloin arvotaan savel
					else {
						int x = apuri.arpoja(new int[] {1, 4, 2, 6, 3, 6, 4, 1, 5, 1});
						s.add(x);
						loppu = loppu + m.annaRytmi().get(i);
					}
					
					if (loppu >= 4) break;
					
					i++;
				}
			}
		
		//Arvotaan varsinainen savelkulku
		int edellinenSuunta = 0;
		for (int b : m.annaRytmi()) {
			//Jos tauko, ei arvota savelta
			if (b >= 10 && b % 10 != 0) {}
			
			//Muulloin arvotaan savel
			else {
				
				int x = 0;
				//Suuntaa ei oteta huomioon
				if (edellinenSuunta == 0) {
					
					//Arvotaan. Jos rytmi on 1/8, savelhyppyyn ei ole mahdollisuutta
					while (true) {
						 x = apuri.arpoja(new int[] {1, 2, 2, 6, 3, 6, 4, 1, 5, 1});
						 if (b != 1) break;
						 else if (x != 4 && x != 5) break;
					}
					
					s.add(x);
					
					//Asetetaan edellinen suunta tulosten mukaan: ylos (1), alas(2)
					if (x == 2 || x== 4) edellinenSuunta = 1;
					else if (x == 3 || x== 5) edellinenSuunta = 2;
				}
				
				//Tod.nak. ylos
				else if (edellinenSuunta == 1) {
					while (true) {
						 x = apuri.arpoja(new int[] {1, 2, 2, 8, 3, 4, 4, 1, 5, 1});
						 if (b != 1) break;
						 else if (x != 4 && x != 5) break;
					}
					s.add(x);
					
					if (x == 2 || x== 4) edellinenSuunta = 1;
					else if (x == 3 || x== 5) edellinenSuunta = 2;
				}
				
				//Tod.nak. alas
				else if (edellinenSuunta == 2) {
					while (true) {
						 x = apuri.arpoja(new int[] {1, 2, 2, 4, 3, 8, 4, 1, 5, 1});
						 if (b != 1) break;
						 else if (x != 4 && x != 5) break;
					}
					s.add(x);
					
					if (x == 2 || x== 4) edellinenSuunta = 1;
					else if (x == 3 || x== 5) edellinenSuunta = 2;
				}
			}
		}
		m.asetaSavelet(s);
		asetaPaino(m);
	}
	
	//Kerrotaan painolliset savelet *10
	public void asetaPaino(Motiivi m) {
		int tauot = 0;
		boolean onkoPitka = false;
		
		for (int i = 0; i<m.annaRytmi().size(); i++) {
			if (m.annaRytmi().get(i) >= 10 && m.annaRytmi().get(i) % 10 != 0) tauot++;
			if (m.annaRytmi().get(i) == 4 || m.annaNimi().equals("pitka") || m.annaNimi().equals("superPitka")) {
				ArrayList<Integer> s = new ArrayList<Integer>(m.annaSavelet());
				s.set(i-tauot, s.get(i-tauot)*10);
				m.asetaSavelet(s);
				onkoPitka = true;
				break;
			}
		}
		
		int aluke = 0;
		tauot = 0;
		if (!onkoPitka) {
			for (int i = 0; i<m.annaRytmi().size(); i++) {
				if (aluke >= 4) {
					ArrayList<Integer> s = new ArrayList<Integer>(m.annaSavelet());
					s.set(i-tauot, s.get(i-tauot)*10);
					m.asetaSavelet(s);
					break;
				}
				else {
					if (m.annaRytmi().get(i) >= 10 && m.annaRytmi().get(i) != 16) {
						aluke = aluke + (m.annaRytmi().get(i) / 10);
						tauot++;
					}
					else {
						aluke = aluke + m.annaRytmi().get(i);
					}
				}
				
			}
		}
	}
}
