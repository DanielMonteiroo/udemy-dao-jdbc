package model.dao;

import java.util.List;

import model.entities.Departamento;
import model.entities.Vendedor;

public interface VendedorDao {

	void insert(Vendedor vendedor);

	void update(Vendedor vendedor);

	void deleteById(Integer id);

	Vendedor findById(Integer id);

	List<Vendedor> findAll();
	
	List<Vendedor> findByDepartmento(Departamento departamento);

}
