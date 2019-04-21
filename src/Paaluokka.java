
public class Paaluokka {
	public static void main(String[] args){
		Soinnut soinnut = new Soinnut();
		soinnut.arvo();
		//for (int i = 0; i<soinnut.annaSointukierto().length; i++) {
		//	System.out.println(soinnut.annaSointukierto()[i]);
		//}
		
		Melodia melodia = new Melodia();
		melodia.luoMelodia(soinnut);
		for (int i = 0; i<melodia.osaA.size(); i++) {
			System.out.print(melodia.osaA.get(i).annaNimi() + ": " + melodia.osaA.get(i).annaPituus() + " ");
			if (melodia.osaA.get(i).annaAluke()) System.out.print("A");
			if (melodia.osaA.get(i).annaSeuraavallaAluke()) System.out.print("S");
			if (melodia.osaA.get(i).annaViive()) System.out.print("V");
			System.out.println("");
		}
		
		System.out.println("");
		
		for (int i = 0; i<melodia.osaB.size(); i++) {
			System.out.print(melodia.osaB.get(i).annaNimi() + ": " + melodia.osaB.get(i).annaPituus() + " ");
			if (melodia.osaB.get(i).annaAluke()) System.out.print("A");
			if (melodia.osaB.get(i).annaSeuraavallaAluke()) System.out.print("S");
			if (melodia.osaB.get(i).annaViive()) System.out.print("V");
			System.out.println("");
		}
		
		System.out.println("");
		
		for (int i = 0; i<melodia.osaC.size(); i++) {
			System.out.print(melodia.osaC.get(i).annaNimi() + ": " + melodia.osaC.get(i).annaPituus() + " ");
			if (melodia.osaC.get(i).annaAluke()) System.out.print("A");
			if (melodia.osaC.get(i).annaSeuraavallaAluke()) System.out.print("S");
			if (melodia.osaC.get(i).annaViive()) System.out.print("V");
			System.out.println("");
		}
	}	
}
