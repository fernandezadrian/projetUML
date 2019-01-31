package Metier;

import java.text.DecimalFormat;

public class Produit implements I_Produit{

	private int quantiteStock;
	private String nom;
	private double prixUnitaireHT;
	private static float tauxTVA = 0.2f;

	
	
	public Produit(String nom, double prix, int qte){
		this.nom = nom.trim().replace('\t', ' ');
		this.prixUnitaireHT = prix;
		this.quantiteStock = qte;
	}

	@Override
	public boolean ajouter(int qteAchetee) {
		this.quantiteStock += qteAchetee;
		return true;
	}

	@Override
	public boolean enlever(int qteVendue) {
		this.quantiteStock -= qteVendue;
		return false;
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public int getQuantite() {
		return this.quantiteStock;
	}

	@Override
	public double getPrixUnitaireHT() {
		return this.prixUnitaireHT;
	}

	@Override
	public double getPrixUnitaireTTC() {
		return this.prixUnitaireHT*(1+tauxTVA);
	}

	@Override
	public double getPrixStockTTC() {
		return this.prixUnitaireHT * this.quantiteStock * (1+tauxTVA);
	}
	
	public String toString(){
		DecimalFormat df = new DecimalFormat("0.00");
		return this.nom + " - prix HT : " + df.format(this.prixUnitaireHT) + " € - prix TTC : " + df.format(this.getPrixUnitaireTTC()) +  " € - quantité en stock : " + this.quantiteStock; 
	}

}
