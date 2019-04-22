import java.util.ArrayList;
import javax.sound.midi.*;
 
public class Soittaja {
	public void soita(ArrayList<Integer> soinnut) {
		
		int soitin = 5;
		int tempo = 150;
		
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
			
			int paikka = 0;
			
			for (int i = 0; i<20; i++) {
				//lisaaNuotti(40+x, i*2 + 1, 2, track);
				//x++;
			}
			
			//aloitetaan soittaminen
			s.setSequence(kappale);
			s.start();
		} 
		
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	
	//Lisataan nuotin alku ja loppu sekvensseriin
	public void lisaaNuotti(int nuotti, int alku, int pituus, Track track) throws InvalidMidiDataException {
		ShortMessage a = new ShortMessage();
		a.setMessage(144, 1, nuotti, 100);
		MidiEvent noteOn = new MidiEvent(a, alku);
		track.add(noteOn);
	
		ShortMessage b = new ShortMessage();
		b.setMessage(128, 1, nuotti, 100);
		MidiEvent noteOff = new MidiEvent(b, alku+pituus);
		track.add(noteOff);
	}

}