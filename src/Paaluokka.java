
public class Paaluokka {
	public static void main(String[] args){
		Apu apuri = new Apu();
		Soinnut soinnut = new Soinnut();
		soinnut.arvo();
		//for (int i = 0; i<soinnut.annaSointukierto().length; i++) {
		//	System.out.println(soinnut.annaSointukierto()[i]);
		//}
		
		Melodia melodia = new Melodia();
		melodia.luoMelodia(soinnut);
		for (int i = 0; i<melodia.osaA.size(); i++) {
			System.out.print(melodia.osaA.get(i).annaNimi() + ": " + melodia.osaA.get(i).annaPituus() + " ");
			if (melodia.osaA.get(i).annaAluke() != 0) System.out.print("A" + melodia.osaA.get(i).annaAluke());
			if (melodia.osaA.get(i).annaSeuraavallaAluke() != 0) System.out.print("S" + melodia.osaA.get(i).annaSeuraavallaAluke());
			if (melodia.osaA.get(i).annaViive()) System.out.print("V");
			System.out.print(" | ");
			for (int x : melodia.osaA.get(i).annaRytmi()) {
				System.out.print(x + " ");
			}
			System.out.print(" | ");
			for (int x : melodia.osaA.get(i).annaSavelet()) {
				System.out.print(x + " ");
			}
			System.out.println("");
		}
		
		System.out.println("");
		
		for (int i = 0; i<melodia.osaB.size(); i++) {
			System.out.print(melodia.osaB.get(i).annaNimi() + ": " + melodia.osaB.get(i).annaPituus() + " ");
			if (melodia.osaB.get(i).annaAluke() != 0) System.out.print("A" + melodia.osaB.get(i).annaAluke());
			if (melodia.osaB.get(i).annaSeuraavallaAluke() != 0) System.out.print("S" + melodia.osaB.get(i).annaSeuraavallaAluke());
			if (melodia.osaB.get(i).annaViive()) System.out.print("V");
			System.out.print(" | ");
			for (int x : melodia.osaB.get(i).annaRytmi()) {
				System.out.print(x + " ");
			}
			System.out.print(" | ");
			for (int x : melodia.osaB.get(i).annaSavelet()) {
				System.out.print(x + " ");
			}
			System.out.println("");
		}
		
		System.out.println("");
		
		for (int i = 0; i<melodia.osaC.size(); i++) {
			System.out.print(melodia.osaC.get(i).annaNimi() + ": " + melodia.osaC.get(i).annaPituus() + " ");
			if (melodia.osaC.get(i).annaAluke() != 0) System.out.print("A" + melodia.osaC.get(i).annaAluke());
			if (melodia.osaC.get(i).annaSeuraavallaAluke() != 0) System.out.print("S" + melodia.osaC.get(i).annaSeuraavallaAluke());
			if (melodia.osaC.get(i).annaViive()) System.out.print("V");
			System.out.print(" | ");
			for (int x : melodia.osaC.get(i).annaRytmi()) {
				System.out.print(x + " ");
			}
			System.out.print(" | ");
			for (int x : melodia.osaC.get(i).annaSavelet()) {
				System.out.print(x + " ");
			}
			System.out.println("");
		}
		
		System.out.println("");
		
		System.out.println(apuri.onkoSama(melodia.osaA));
		System.out.println(apuri.onkoSama(melodia.osaB));
		System.out.println(apuri.onkoSama(melodia.osaC));
	}	
}
