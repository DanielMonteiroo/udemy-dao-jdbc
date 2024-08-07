package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {

		Departamento obj = new Departamento(1,"Livros");
		Vendedor vendedor = new Vendedor(1, "Daniel Monteiro","daniel.ios90@gmail.com", new Date(), 3750, obj);

		VendedorDao vendedorDao = DaoFactory.createVendedorDao();
		
		System.out.println(obj);
		System.out.println(vendedor);
	}

}
