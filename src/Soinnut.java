public class Soinnut {
	Apu apuri = new Apu();
	
	public int arvoRakenne() {
		//int[] a = new int[] {1, 1, 2, 1};
		int[] a = new int[] {1, 1, 2, 1, 3, 6, 4, 2};
		return apuri.arpoja(a);
	}
}
