package DAL;

import java.util.List;

import Metier.I_Produit;

public class ProduitDAO_XMLAdap implements I_ProduitDAO{
	
	private ProduitDAO_XML dao = new ProduitDAO_XML();
	private static ProduitDAO_XMLAdap instance = new ProduitDAO_XMLAdap();

	

	@Override
	public boolean create(I_Produit produit) {
		return dao.creer(produit);
	}

	@Override
	public boolean update(I_Produit produit) {
		return dao.maj(produit);
	}

	@Override
	public boolean delete(I_Produit produit) {
		return dao.supprimer(produit);
	}

	@Override
	public I_Produit read(String nom) {
		return dao.lire(nom);
	}
	
	@Override
	public List<I_Produit> readAll() {
		return dao.lireTous();
	}
	

	public static ProduitDAO_XMLAdap getInstance(){
		return instance;
	}



}
