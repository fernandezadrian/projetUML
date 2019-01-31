package DAL;



public class DAOFactory {
	
	public static I_ProduitDAO createDAO(){
		return ProduitDAO_XMLAdap.getInstance();
	}
	

}
