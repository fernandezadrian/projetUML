package Controleurs;

import Graphique.FenetrePrincipale;
import Metier.Catalogue;

public class ControleurProduit {
	
	public static void addProduit(String nom, double prix, int qte){
		Catalogue.getInstance().addProduit(nom, prix, qte);
	}
	
	public static void removeProduit(String nom){
		Catalogue.getInstance().removeProduit(nom);
	}
}
