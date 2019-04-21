
public class Motiivi {
	private String nimi;
	private boolean onkoAluke = false;
	private boolean onkoViive = false;
	private boolean onkoSeuraavallaAluke = false;
	private int[] rytmi;
	private int pituus;
	
	public Motiivi(String nimi, int pituus) {
		this.nimi = nimi;
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
	
	public boolean equals(Motiivi a) {
		if (this.nimi == a.nimi &&
			this.onkoAluke == a.onkoAluke &&
			this.onkoViive == a.onkoViive &&
			this.onkoSeuraavallaAluke == a.onkoSeuraavallaAluke &&
			this.rytmi == a.rytmi &&
			this.pituus == a.pituus) return true;
		
		else return false;
	};
	
	public String annaNimi() {
		return this.nimi;
	}
	
	public boolean annaAluke() {
		return this.onkoAluke;
	}
	
	public void asetaAluke(boolean a) {
		this.onkoAluke = a;
	}
	
	public boolean annaViive() {
		return onkoViive;
	}
	
	public void asetaViive(boolean a) {
		this.onkoViive = a;
	}
	
	public boolean annaSeuraavallaAluke() {
		return this.onkoSeuraavallaAluke;
	}
	
	public void asetaSeuraavallaAluke(boolean a) {
		this.onkoSeuraavallaAluke = a;
	}
	
	public int annaPituus() {
		return this.pituus;
	}
}
