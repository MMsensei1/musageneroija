public class Soinnut {
	Apu apuri = new Apu();
	private int[] sointukierto = new int[16];
	private int[] soinnutA = new int[4];
	private int[] soinnutB = new int[4];
	private int[] soinnutC = new int[4];
	
	//Arpoo onko rakenne abac(1) vai aabc(2)
	public int arvoRakenne() {
		int[] a = new int[] {1, 1, 2, 1};
		return apuri.arpoja(a);
	}
	
	//Arpoo A-soinnut
	public int[] arvoA() {
		soinnutA[0] = 1;
		
		int[] a = new int[] {1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1};
		soinnutA[1] = apuri.arpoja(a);
		
		
		
		
		return new int[1];
	}
	
	//Arpoo todennakoisyyksien avulla soinnun
	public int apuArpoja() {
		int[] a;
		
		if (soinnutA[1] == 1) a = apuri.sointuListaaja("546321");
		else if (soinnutA[1] == 2) a = new int[] {563241};
		else if (soinnutA[1] == 3) a = new int[] {643215};
		else if (soinnutA[1] == 4) a = new int[] {546312};
		else if (soinnutA[1] == 5) a = new int[] {241365};
		else a = new int[] {};
		
		return apuri.arpoja(a);
	}
	
	public int[] annaSointukierto() {
		return sointukierto;
	}
}
