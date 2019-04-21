import java.util.ArrayList;

public class Melodia {
	Apu apuri = new Apu();
	
	public void luoMelodiaA() {
		ArrayList<Motiivi> melodiaA = new ArrayList<Motiivi>();
		
		//Arpoo A:n rakenteen: aa(1), ab(2), a + pitka(3)
		int rakenne = apuri.arpoja(new int[] {1, 1, 2, 1, 3, 1});
		if (rakenne == 1) {
			//Motiivilista1 + Motiivilista1
			ArrayList<Motiivi> a = luoMotiiviLista();
			for (int j = 0; j<2; j++) {
				for (int i = 0; i<a.size(); i++) {
					melodiaA.add(a.get(i));
				}
			}
		}
		else if (rakenne == 2) {
			//Motiivilista1 + Motiivilista2
		}
		else {
			//Motiivilista1 + SuperPitkäMotiivi
		}
	
	}
	
	public ArrayList<Motiivi> luoMotiiviLista(){
		ArrayList<Motiivi> palautus = new ArrayList<Motiivi>();
		
		//Arpoo motiivilistan rakenteen: aa(1), ab(2), aaaa(3), a+pitka(4), pitka+a(5)
		int rakenne = apuri.arpoja(new int[] {1, 1, 2, 1, 3, 1, 4, 1, 5, 1});
		if (rakenne == 1) {
			//Motiivi1 + Motiivi1
			palautus.add(new Motiivi("a", 4));
			palautus.add(new Motiivi("a", 4));
		}
		else if (rakenne == 2) {
			//Motiivi1 + Motiivi2
			palautus.add(new Motiivi("a", 4));
			palautus.add(new Motiivi("b", 4));
		}
		else if (rakenne == 3) {
			//LyhytMotiivi * 4
			palautus.add(new Motiivi("a", 2));
			palautus.add(new Motiivi("a", 2));
			palautus.add(new Motiivi("a", 2));
			palautus.add(new Motiivi("a", 2));
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
}
