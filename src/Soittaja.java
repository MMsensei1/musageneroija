import java.util.ArrayList;
import javax.sound.midi.*;
 
public class Soittaja {
	Apu apuri = new Apu();
	
	int soitin = 5;
	int tempo = 130;
	int savellaji = 12; //C = 12
	int moodi = 25 - 7*2; //27 = Duuri, 25 = Molli
	
	public void soita(int[] soinnut, ArrayList<Motiivi> melodia) {
		
		
		
		
		//Luodaan lista savellajin nuoteista
		ArrayList<Integer> nuotit = new ArrayList<Integer>();
		for (int i = savellaji; i<=96+savellaji; i++) {
			nuotit.add(i); i = i+2;
			nuotit.add(i); i = i+2;
			nuotit.add(i); i = i+1;
			nuotit.add(i); i = i+2;
			nuotit.add(i); i = i+2;
			nuotit.add(i); i = i+2;
			nuotit.add(i);
		}
		
		try {
			//Avataan sekvensseri, luodaan kappale, asetetaan tempo, ja luodaan raita
			Sequencer s = MidiSystem.getSequencer();
			s.open();
			Sequence kappale = new Sequence(Sequence.PPQ, 4);
			s.setTempoInBPM(tempo);
			Track track = kappale.createTrack();
			 
			MidiEvent event = null;
			 
			//Asetetaan soitin
			ShortMessage soitinVaihtoSM = new ShortMessage();
			soitinVaihtoSM.setMessage(192, 1, soitin, 0);
			MidiEvent soitinVaihto = new MidiEvent(soitinVaihtoSM, 1);
			track.add(soitinVaihto);
			
			int paikka = 8;
			
			//Asetetaan soinnut
			for (int sointu : soinnut) {
				lisaaNuotti(nuotit.get(moodi+sointu), paikka, 8, track, 70);
				paikka = paikka+8;
				lisaaNuotti(nuotit.get(moodi+sointu+7), paikka, 8, track, 70);
				paikka = paikka+8;
				
				/*for (int i = 0; i<2; i++) {
					lisaaNuotti(nuotit.get(moodi+sointu), paikka, 2, track, 70);
					paikka = paikka+2;
					lisaaNuotti(nuotit.get(moodi+sointu+4), paikka, 2, track, 70);
					paikka = paikka+2;
					lisaaNuotti(nuotit.get(moodi+sointu+2), paikka, 2, track, 70);
					paikka = paikka+2;
					lisaaNuotti(nuotit.get(moodi+sointu+4), paikka, 2, track, 70);
					paikka = paikka+2;
				}*/
			}
			
			
			paikka = 8;
			int viimeisinSavel = 0;
			
			
			//Asetetaan melodia
			for (Motiivi m : melodia) {
				paikka = paikka - 8;
				
				for (int ab : m.annaSavelet()) {
					System.out.print("ms: " + ab + " | ");
				}
				System.out.println("");
				for (int ab : m.annaRytmi()) {
					System.out.print("mr: " + ab + " | ");
				}
				System.out.println("");
				ArrayList<Integer> uudetSavelet = muunnaMotiivi(m);
				for (int ab : uudetSavelet) {
					System.out.print("ms2: " + ab + " | ");
				}
				System.out.println("");
				for (int ab : m.annaRytmi()) {
					System.out.print("mr2: " + ab + " | ");
				}
				System.out.println("");
				System.out.println("");
				System.out.println("Length: " + soinnut.length);
				System.out.println("Thing: " + ((paikka + 8) / 16));
				uudetSavelet = transponoi(uudetSavelet, soinnut[(paikka + 8)/16], viimeisinSavel);
				viimeisinSavel = uudetSavelet.get(uudetSavelet.size()-1);
				uudetSavelet.remove(uudetSavelet.size()-1);
				
				for (int ab : uudetSavelet) {
					System.out.print("ms3: " + ab + " | ");
				}
				System.out.println("");
				for (int ab : m.annaRytmi()) {
					System.out.print("mr3: " + ab + " | ");
				}
				System.out.println("");
				
				int savelLuku = 0;
				for (int r : m.annaRytmi()) {
					int savel = 0;
					if (r >= 10 && r % 10 == 0) {
						paikka = paikka + ((r/10) *2);
						continue;
					}
					else {
						savel = uudetSavelet.get(savelLuku);
						if (uudetSavelet.get(savelLuku) > 500) savel = savel - 1000;
						lisaaNuotti(nuotit.get(savel), paikka, r, track, 100);
						paikka = paikka + (r*2);
						savelLuku++;
					}
				}
				
			}
			
			System.out.println("Hei");
			
			//aloitetaan soittaminen
			s.setSequence(kappale);
			s.start();
		} 
		
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	
	//muuntaa motiivin savelet abstraktista muodosta transponoitavaan muotoon, ja palauttaa ne int[] muodossa
	public ArrayList<Integer> muunnaMotiivi(Motiivi m) {
		ArrayList<Integer> palautus = new ArrayList<Integer>();
		ArrayList<Integer> soinnunSavelet = new ArrayList<Integer>();
		
		//Asetetaan soinnun savelet
		for (int i = 0; i<8; i++) {
			soinnunSavelet.add(moodi - 13 + 0 + 7*i);
			soinnunSavelet.add(moodi - 13 + 2 + 7*i);
			soinnunSavelet.add(moodi - 13 + 4 + 7*i);
		}
		
		boolean onkoPainollinen = false;
		while (!onkoPainollinen) {
			palautus = new ArrayList<Integer>();
			int eka = apuri.arpoja(new int[] {28,1,29,1,30,1,31,1,32,1,33,1,34,1});
			palautus.add(eka);
			
			boolean onkoTonni = false;
			for (int n : m.annaSavelet()) {
				if (n == 1 || n == 10) {
					if (n==10) {
						palautus.set(palautus.size()-1, palautus.get(palautus.size()-1) + 1000);
						palautus.add(palautus.get(palautus.size()-1) - 1000);
					}
					else {
							palautus.add(palautus.get(palautus.size()-1));
					}
				}
				else if (n == 2 || n == 20){
					if (n==20) {
						palautus.set(palautus.size()-1, palautus.get(palautus.size()-1) + 1000);
						palautus.add(palautus.get(palautus.size()-1) + 1 - 1000);
					}
					else {
							palautus.add(palautus.get(palautus.size()-1) + 1);
					}
				}
				else if (n == 3 || n == 30){
					if (n==30) {
						palautus.set(palautus.size()-1, palautus.get(palautus.size()-1) + 1000);
						palautus.add(palautus.get(palautus.size()-1) - 1 - 1000);
					}
					else {
							palautus.add(palautus.get(palautus.size()-1) - 1);
					}
				}
				
				else if (n == 4 || n == 40){
					int x = 1;
					int y = 0;
					boolean onkoSoinnunSavel = false;
					while(!onkoSoinnunSavel) {
							y = palautus.get(palautus.size()-1) + x;
						
						for (int s : soinnunSavelet) {
							if (s == y) onkoSoinnunSavel = true;
						}
						
						x++;
					}
					if (n == 40) {
						palautus.set(palautus.size()-1, palautus.get(palautus.size()-1) + 1000);
					}
					palautus.add(y);
					
				}
				
				else if (n == 5 || n == 50){
					int x = 1;
					int y = 0;
					boolean onkoSoinnunSavel = false;
					while(!onkoSoinnunSavel) {
							y = palautus.get(palautus.size()-1) - x;
						
						for (int s : soinnunSavelet) {
							if (s == y) onkoSoinnunSavel = true;
						}
						
						x++;
					}
					
					if (n == 50) {
						palautus.set(palautus.size()-1, palautus.get(palautus.size()-1) + 1000);
					}
					palautus.add(y);
				}
			}
			
			for (int s : palautus) {
				if (s > 500) {
					for (int s2 : soinnunSavelet) {
						if (s2 == s - 1000) {
							onkoPainollinen = true;
						}
					}
				}
			}
		}
		
		return palautus;
	}
	
	//Transponoidaan sopimaan sointuun
	public ArrayList<Integer> transponoi(ArrayList<Integer> savelet, int sointu, int viimeisinSavel){
		
		//Asetetaan soinnun savelet
		ArrayList<Integer> soinnunSavelet = new ArrayList<Integer>();
		for (int i = 0; i<8; i++) {
			soinnunSavelet.add(moodi - 13 - 32 - 1 + sointu + 7*i);
			soinnunSavelet.add(moodi - 13 + 2 - 1 + sointu + 7*i);
			soinnunSavelet.add(moodi - 13 + 4 - 1 + sointu + 7*i);
		}
		
		ArrayList<Integer> palautus = new ArrayList<Integer>();
		int x = 0;
		boolean onkoPainollinen = false;
		
		while(!onkoPainollinen) {
			palautus = new ArrayList<Integer>();
			int erotus = viimeisinSavel - savelet.get(0);
			if (erotus < -500 ) erotus = erotus + 1000;
			
			if (viimeisinSavel == 0) erotus = 0;
			for (int s : savelet) {
				palautus.add(s + erotus + x);
			}
			
			for (int s : palautus) {
				if (s > 500) {
					for (int s2 : soinnunSavelet) {
						if (s2 == s - 1000) onkoPainollinen = true;
					}
				}
			}
			
			x++;
		}
		return palautus;
	}
	
	//Lisataan nuotin alku ja loppu sekvensseriin
	public void lisaaNuotti(int nuotti, int alku, int pituus, Track track, int v) throws InvalidMidiDataException {
		ShortMessage a = new ShortMessage();
		a.setMessage(144, 1, nuotti, v);
		MidiEvent noteOn = new MidiEvent(a, alku);
		track.add(noteOn);
	
		ShortMessage b = new ShortMessage();
		b.setMessage(128, 1, nuotti, v);
		MidiEvent noteOff = new MidiEvent(b, alku+pituus);
		track.add(noteOff);
	}

}