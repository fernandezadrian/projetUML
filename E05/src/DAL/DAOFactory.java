package DAL;



public class DAOFactory {
	
	public static I_ProduitDAO createDAO(){
		return ProduitDAORelationnel.getInstance();
	}
	

}
