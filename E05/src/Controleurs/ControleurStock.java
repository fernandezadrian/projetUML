package Controleurs;

import Graphique.FenetrePrincipale;
import Metier.Catalogue;



public class ControleurStock {

	public static String afficherStock(){
		return Catalogue.getInstance().toString();
	}

	public static String[] getNomsProduits(){
		return Catalogue.getInstance().getNomProduits();
	}
}
