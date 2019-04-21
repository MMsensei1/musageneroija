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
		int x = 0;
		return x;
	}
	public int[] annaSointukierto() {
		return sointukierto;
	}
}
