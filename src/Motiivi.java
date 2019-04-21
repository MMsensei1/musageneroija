
public class Motiivi {
	private String nimi;
	private boolean onkoAluke = false;
	private boolean onkoViive = false;
	private boolean onkoSeuraavallaAluke = false;
	private int[] rytmi;
	private int pituus;
	
	public Motiivi(String nimi, int pituus) {
		this.pituus = pituus;
	}
	
	public Motiivi(Motiivi a) {
		this.nimi = a.nimi;
		this.onkoAluke = a.onkoAluke;
		this.onkoViive = a.onkoViive;
		this.onkoSeuraavallaAluke = a.onkoSeuraavallaAluke;
		this.rytmi = a.rytmi;
		this.pituus = a.pituus;
	}
}
