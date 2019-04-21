import java.util.Random;
import java.util.ArrayList;

public class Apu {
	Random random = new Random();
	
	public int arpoja(int[] a) {
		ArrayList<Integer> arvottavat = new ArrayList<Integer>();
		
		for (int i = 0; i < a.length; i = i+2) {
			for (int j = 0; j<a[i+1]; j++) {
				arvottavat.add(a[i]);
			}
		}
		
		int x = random.nextInt(arvottavat.size());
		return arvottavat.get(x);
	}
}
