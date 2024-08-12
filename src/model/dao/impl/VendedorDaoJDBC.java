package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class VendedorDaoJDBC implements VendedorDao {
	
	
	private Connection conn;
	
	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	
	@Override
	public void insert(Vendedor vendedor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Vendedor vendedor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vendedor findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name AS DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
					
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()){
				Departamento dep = instanciaDepartamento(rs);
				Vendedor vendedor = instanciaVendedor(rs,dep);
			
				return vendedor;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	//função de chamada para vendedor
	private Vendedor instanciaVendedor(ResultSet rs, Departamento dep) throws SQLException {
		Vendedor vendedor = new Vendedor();
		vendedor.setId(rs.getInt("Id"));
		vendedor.setName(rs.getString("Name"));
		vendedor.setEmail(rs.getString("Email"));
		vendedor.setSalarioBase(rs.getDouble("BaseSalary"));
		vendedor.setDataNascimento(rs.getDate("BirthDate"));
		vendedor.setDepartamento(dep);
		return vendedor;
	}


	//função de chamada para departamento
	private Departamento instanciaDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNome(rs.getString("DepName"));
		return dep;
	}


	@Override
	public List<Vendedor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	//Metodo busca por departamento
	@Override
	public List<Vendedor> findByDepartmento(Departamento departamento) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name AS DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name");
					
			st.setInt(1, departamento.getId());
			rs = st.executeQuery();
			
			List<Vendedor> lista = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while(rs.next()){
				
				//Salvando departamento para não repetição na busca
				Departamento dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instanciaDepartamento(rs);
					dep = map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Vendedor vendedor = instanciaVendedor(rs,dep);
			    lista.add(vendedor);
			}
			return lista;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	
	
}
