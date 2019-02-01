package Metier;

import DAL.DAOFactory;


public class ProduitFactory {

	public static I_Produit createProduit(String nom, double prix, int qte){
		 Produit produit = new Produit(nom, prix, qte);
		 return produit;
	}
	
}
