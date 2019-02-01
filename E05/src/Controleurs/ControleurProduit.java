package Controleurs;

import DAL.DAOFactory;
import Graphique.FenetrePrincipale;
import Metier.Catalogue;
import Metier.ProduitFactory;

public class ControleurProduit {
	
	public static void addProduit(String nom, double prix, int qte){
		Catalogue.getInstance().addProduit(nom, prix, qte);
		DAOFactory.createDAO().create(ProduitFactory.createProduit(nom, prix, qte));
	}
	
	public static void removeProduit(String nom){
		Catalogue.getInstance().removeProduit(nom);
	}
}
