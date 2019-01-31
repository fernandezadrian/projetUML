package Controleurs;

import Graphique.FenetrePrincipale;
import Metier.Catalogue;

public class ControleurAchatVente {
    
    public static void acheterStock(String nomProduit, int qteAchetee){
        Catalogue.getInstance().acheterStock(nomProduit, qteAchetee);
    }
    
    public static void vendreStock(String nomProduit, int qteVendue){
    	Catalogue.getInstance().vendreStock(nomProduit, qteVendue);
    }
}