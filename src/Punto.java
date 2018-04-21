import java.text.DecimalFormat;

public class Punto {

	//Riduce il numero di cifre decimali significative visibili a 1 (0.0)
	
	DecimalFormat cifre= new DecimalFormat("#,###,##0.0");
	
	private double ascissa;
	private double ordinata;
	private String nome;
	
	public Punto(double c1, double c2, String n1) {
		ascissa=c1;
		ordinata=c2;
		nome=n1;
	}
	
	public String coordinate() {
		return "(" + ascissa + "; " + ordinata + ")";
	}
	
	//Calcola la distanza tra due punti
	
	public void distanza(Punto p1) {
		double differenzaQuadrati = Math.pow((p1.ascissa-ascissa),2)+Math.pow((p1.ordinata-ordinata),2);
		double risultato = Math.sqrt(differenzaQuadrati);	
		System.out.println("La distanza tra il " + nome + " e il " + p1.nome + " è: " + cifre.format(risultato));
	}
	
	public double getAscissa() {
		return ascissa;
	}

	public String getNome() {
		return nome;
	}
	
	public void setAscissa(double ascissa) {
		this.ascissa = ascissa;
	}

	public double getOrdinata() {
		return ordinata;
	}

	public void setOrdinata(double ordinata) {
		this.ordinata = ordinata;
	}
}
