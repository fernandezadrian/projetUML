package DAL;

import java.util.List;

import Metier.I_Produit;

public interface I_ProduitDAO {

	public boolean create(I_Produit produit);
	
	public boolean update(I_Produit produit);
	
	public boolean delete(I_Produit produit);
	
	public I_Produit read(String nom);

	public List<I_Produit> readAll();
		

}
