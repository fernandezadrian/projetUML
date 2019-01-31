package Metier;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DAL.DAOFactory;
import Graphique.FenetrePrincipale;

public class Catalogue implements I_Catalogue {



	private List<I_Produit> liste;
	private static Catalogue instance = new Catalogue();

	private Catalogue() {
		this.liste = DAOFactory.createDAO().readAll();
	}

	@Override
	public boolean addProduit(I_Produit produit) {
		boolean test = false;
		if(produit == null) return test;
		if (produit.getQuantite() >= 0 && produit.getPrixUnitaireHT() > 0
				&& PasProduitAvecMemeNom(produit)) {
			this.liste.add(produit);
			test = true;
		}
		return test;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		return this.addProduit(ProduitFactory.createProduit(nom, prix, qte));
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		int i = 0;
		if(l != null){
			i = 0;
			for (I_Produit p : l) {

				if (this.addProduit(p)) {
					i++;
				}
			}
		}
		return i;
	}

	private boolean PasProduitAvecMemeNom(I_Produit p) {
		boolean test = true;
		String[] noms = this.getNomProduits();
		for (String s : noms) {
			if (p.getNom().trim().equals(s)) {
				test = false;
			}
		}
		return test;
	}

	@Override
	public boolean removeProduit(String nom) {
		boolean test = false;
		if(this.produitExiste(nom)){
			this.liste.remove(this.getProduitParNom(nom));
			test = true;
		}
		return test;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		boolean test = false;
		if(this.produitExiste(nomProduit)){
			I_Produit produit = this.getProduitParNom(nomProduit);
			if(qteAchetee <= 0)return false;
			produit.ajouter(qteAchetee);
			test = true;
		}
		return test;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		boolean test = true;
		if(this.produitExiste(nomProduit)){
			I_Produit produit = this.getProduitParNom(nomProduit);
			if (produit.getQuantite() == 0 || produit.getQuantite() < qteVendue || qteVendue < 0){
				test = false;
			} else {
				produit.enlever(qteVendue);
			}
		} else {
			test = false;
		}
		return test;
	}

	@Override
	public String[] getNomProduits() {
		String[] listeNomsProduits = new String[this.liste.size()];
		for(int i = 0; i < this.liste.size(); i++){
			listeNomsProduits[i] = this.liste.get(i).getNom().trim();
		}
		Arrays.sort(listeNomsProduits);
		return listeNomsProduits;
	}
	@Override
	public double getMontantTotalTTC() {
		double montantTotal = 0;
		for (I_Produit p : this.liste) {
			montantTotal += p.getPrixStockTTC();
		}
		DecimalFormat df = new DecimalFormat("0.00");
		String s = df.format(montantTotal).replaceFirst(",", ".");
		return Double.parseDouble(s);
	}

	public I_Produit getProduitParNom(String nom) {
		int i = 0;
		while (this.liste.get(i).getNom() != nom) {
			i++;
		}
		return this.liste.get(i);
	}

	@Override
	public void clear() {
		this.liste.removeAll(this.liste);
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00");
		String s = "";
		for (I_Produit p : this.liste) {
			s += p.toString() + "\n";
		}
		s += "\nMontant total TTC du stock : " + df.format(this.getMontantTotalTTC()).replace('.', ',') + " €";
		return s;
	}

	private boolean produitExiste(String nom){
		boolean test = false;
		for(I_Produit p : this.liste){
			if(p.getNom().equals(nom)){
				test = true;
			}
		}
		return test;
	}
	
	public static I_Catalogue getInstance(){
		return instance;
	}

}