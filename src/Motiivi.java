import java.util.ArrayList;

public class Motiivi {
	private String nimi;
	private int onkoAluke = 0;
	private boolean onkoViive = false;
	private int onkoSeuraavallaAluke = 0;
	private ArrayList<Integer> rytmi;
	private ArrayList<Integer> savelet;
	private int pituus;
	
	public Motiivi(String nimi, int pituus) {
		this.nimi = nimi;
		this.pituus = pituus;
	}
	
	public Motiivi(Motiivi a) {
		this.onkoAluke = a.onkoAluke;
		this.onkoViive = a.onkoViive;
		this.pituus = a.pituus;
		this.nimi = a.nimi;
		this.onkoSeuraavallaAluke = a.onkoSeuraavallaAluke;
		if (a.rytmi != null) {
			this.rytmi = new ArrayList<Integer>(a.rytmi);
		}
		
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
	
	public void asetaNimi(String nimi) {
		this.nimi = nimi;
	}
	
	public int annaAluke() {
		return this.onkoAluke;
	}
	
	public void asetaAluke(int a) {
		this.onkoAluke = a;
	}
	
	public boolean annaViive() {
		return onkoViive;
	}
	
	public void asetaViive(boolean a) {
		this.onkoViive = a;
	}
	
	public int annaSeuraavallaAluke() {
		return this.onkoSeuraavallaAluke;
	}
	
	public void asetaSeuraavallaAluke(int a) {
		this.onkoSeuraavallaAluke = a;
	}
	
	public int annaPituus() {
		return this.pituus;
	}
	
	public ArrayList<Integer> annaRytmi(){
		return this.rytmi;
	}
	
	public void asetaRytmi(ArrayList<Integer> rytmi) {
		try {

			this.rytmi = new ArrayList<Integer>(rytmi);
		}
		catch (Exception e) {
			
		}
	}
	
	public ArrayList<Integer> annaSavelet(){
		return this.savelet;
	}
	
	public void asetaSavelet(ArrayList<Integer> savelet) {
		this.savelet = savelet;
	}
}
