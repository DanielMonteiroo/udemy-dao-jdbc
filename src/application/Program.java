package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {

		//Listar por ID
		VendedorDao vendedorDao = DaoFactory.createVendedorDao();
		Vendedor vendedor = vendedorDao.findById(2);
		System.out.println("---- TESTE FIND BY ID ----\n");
		System.out.println(vendedor);
		
		//Listar por Departamento
		System.out.println("\n---- TESTE FIND BY DEPARTAMENTO ----\n");
		Departamento departamento = new Departamento(2, null);
		List<Vendedor> lista = vendedorDao.findByDepartmento(departamento);
		
		for(Vendedor objVendedor: lista) {
			System.out.println(objVendedor);
		}
		
		//Listar Todos
		System.out.println("\n---- TESTE FIND ALL----\n");
		List<Vendedor> listaTodos = vendedorDao.findAll();
		
		for(Vendedor todos: listaTodos) {
			System.out.println(todos);
		}
	}

}
