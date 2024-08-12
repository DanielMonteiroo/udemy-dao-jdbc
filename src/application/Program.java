package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {

		VendedorDao vendedorDao = DaoFactory.createVendedorDao();
		Vendedor vendedor = vendedorDao.findById(2);
		System.out.println("---- TESTE FIND BY ID ----\n");
		System.out.println(vendedor);
		
		System.out.println("\n---- TESTE FIND BY DEPARTAMENTO ----\n");
		Departamento departamento = new Departamento(2, null);
		List<Vendedor> lista = vendedorDao.findByDepartmento(departamento);
		
		for(Vendedor objVendedor: lista) {
			System.out.println(objVendedor);
		}
	}

}
