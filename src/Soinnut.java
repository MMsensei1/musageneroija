public class Soinnut {
	Apu apuri = new Apu();
	private int[] sointukierto = new int[16];
	public int[] soinnutA = new int[4];
	public int[] soinnutB = new int[4];
	public int[] soinnutC = new int[4];
	public int[] soinnutD = new int[4];
	
	//Arpoo onko rakenne abac(1) vai aabc(2)
	public int arvoRakenne() {
		int[] a = new int[] {1, 1, 2, 1};
		return apuri.arpoja(a);
	}
	
	//Arpoo A-soinnut
	public void arvoA() {
		soinnutA[0] = 1;
		int[] a = new int[] {1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1};
		soinnutA[1] = apuri.arpoja(a);
		soinnutA[2] = apuArpoja1();
		soinnutA[3] = apuArpoja2();
	}
	
	//Arpoo B-soinnut
	public void arvoB() {
		int[] a;
		
		soinnutB[0] = apuArpojaB();
		if (soinnutB[0] == 1) a = new int[] {1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1};
		else a = new int[] {1, 1, 6, 1};
		soinnutB[1] = apuri.arpoja(a);
		while (true) {
			soinnutB[2] = apuArpoja1();
			if (soinnutB[2] == 2 || soinnutB[2] == 4 || soinnutB[2] == 5) break;
		}
		soinnutB[3] = 5;
	}
	
	//Arpoo C-soinnut
	public void arvoC() {
		int[] a = new int[] {1, 1, 4, 1, 6, 1};
		soinnutC[0] = apuri.arpoja(a);
		a = new int[] {2, 3, 4, 3, 5, 1};
		soinnutC[1] = apuri.arpoja(a);
		soinnutC[2] = 5;
		soinnutC[3] = 1;
	}	
		
	//Arpoo D-soinnut
	public void arvoD() {
		int[] a;
		
		soinnutD[0] = apuArpojaB();
		if (soinnutD[0] == 1) a = new int[] {1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1};
		else a = new int[] {1, 1, 6, 1};
		soinnutD[1] = apuri.arpoja(a);
		soinnutD[2] = apuArpoja1();
		soinnutD[3] = apuArpoja2();
	}
	
	
	//Arpoo todennakoisyyksien avulla soinnun
	public int apuArpoja1() {
		int[] a;
		
		if (soinnutA[1] == 1) a = apuri.sointuListaaja("546321");
		else if (soinnutA[1] == 2) a = apuri.sointuListaaja("563241");
		else if (soinnutA[1] == 3) a = apuri.sointuListaaja("643215");
		else if (soinnutA[1] == 4) a = apuri.sointuListaaja("253146");
		else if (soinnutA[1] == 5) a = apuri.sointuListaaja("546312");
		else a = apuri.sointuListaaja("241365");
		
		return apuri.arpoja(a);
	}
	
	//Arpoo todennakoisyyksien avulla soinnun, kayttaen eri metodia kuin apuArpoja1
	public int apuArpoja2() {
		int[] a;
		
		if (soinnutA[1] == 1) a = apuri.sointuListaaja2("45");
		else if (soinnutA[1] == 2) a = apuri.sointuListaaja2("54");
		else if (soinnutA[1] == 3) a = apuri.sointuListaaja2("64");
		else if (soinnutA[1] == 4) a = apuri.sointuListaaja2("15");
		else if (soinnutA[1] == 5) a = apuri.sointuListaaja2("15");
		else a = apuri.sointuListaaja("43");
		
		return apuri.arpoja(a);
	}
	
	//Arpoo todennakoisyyksien avulla B-sointujen ensimmäisen soinnun
	public int apuArpojaB() {
		int[] a;
		
		if (soinnutA[3] == 1) a = new int[] {4, 1, 5, 1, 1, 1, 6, 1};
		else if (soinnutA[3] == 2) a = new int[] {5, 1, 6, 1};
		else if (soinnutA[3] == 3) a = new int[] {4, 1, 6, 1};
		else if (soinnutA[3] == 4) a = new int[] {1, 1, 6, 1};
		else if (soinnutA[3] == 5) a = new int[] {1, 1, 4, 1, 5, 1};
		else a = new int[] {4, 1};
		
		return apuri.arpoja(a);
	}
	
	public int[] annaSointukierto() {
		return sointukierto;
	}
}
