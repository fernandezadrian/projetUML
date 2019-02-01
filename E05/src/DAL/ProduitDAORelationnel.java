package DAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Metier.I_Produit;
import Metier.ProduitFactory;

public class ProduitDAORelationnel implements I_ProduitDAO {
	
	private Connection cn;
	private PreparedStatement ps;
	private ResultSet rs;
	private static ProduitDAORelationnel instance = new ProduitDAORelationnel();
	
	private ProduitDAORelationnel(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			this.cn = DriverManager.getConnection("jdbc:oracle:thin:@gloin:1521:iut", "fernandeza", "aulas");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver introuvable");
			e.printStackTrace();
		} catch (SQLException e) {
			this.deconnexion();
			e.printStackTrace();
		}
	}
	
	public boolean create(I_Produit p){
		try {
			ps = cn.prepareStatement("{call newProduit(?,?,?)}");
			ps.setString(1, p.getNom());
			ps.setDouble(2, p.getPrixUnitaireHT());
			ps.setInt(3, p.getQuantite());
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(I_Produit p){
		try {
			ps = cn.prepareStatement("delete from Produits where nomProduit = ?");
			ps.setString(1, p.getNom());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public I_Produit read(String nom){
		try {
			ps = cn.prepareStatement("select * from Produits where nomProduit = ? ");
			ps.setString(1, nom);
			rs = ps.executeQuery();
			return ProduitFactory.createProduit(rs.getString(1), rs.getDouble(2), rs.getInt(3));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean update(I_Produit p ){
		return true;
	}
	
//	public boolean deleteAll(){
//		try {
//			ps = cn.prepareStatement("delete from Produits");
//			return ps.execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
	
	public static ProduitDAORelationnel getInstance(){
		return instance;
	}
	
	public void deconnexion(){
		try {
			this.cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<I_Produit> readAll() {
		try {
			List<I_Produit> list = new ArrayList<I_Produit>();
			ps = cn.prepareStatement("select * from Produits");
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(ProduitFactory.createProduit(rs.getString(1), rs.getDouble(2), rs.getInt(3)));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
}
