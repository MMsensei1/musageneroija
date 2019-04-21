
public class Paaluokka {
	public static void main(String[] args){
		Apu apuri = new Apu();
		
		int[] a = apuri.sointuListaaja2("24");
		for(int i = 0; i<12; i=i+2) {
			System.out.print(a[i] + " ");
			System.out.println(a[i+1]);
		}
	}	
}
