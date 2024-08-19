package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
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
		
		
		//Inserção de novos dados
		System.out.println("\n---- TESTE FIND ALL----\n");
		Vendedor newVendedor = new Vendedor(null,"Joana", "joaninha@gmail.com", new Date(), 3105.0, departamento);
		vendedorDao.insert(newVendedor);
		System.out.println("Dados inseridos com sucesso! ID = "+newVendedor.getId());
		
		
		//Atualização de dados
	    System.out.println("\n---- TESTE UPDATE DATA----\n");
	    newVendedor = vendedorDao.findById(1);
	    newVendedor.setName("Domingas Araujo");
	    newVendedor.setEmail("domingasam@gmail.com");
	    newVendedor.setSalarioBase(15500.0);
        vendedorDao.update(newVendedor);
        System.out.println("Dados Atualizados com sucesso! ID = " + newVendedor.getId());
        
        //Deletar por ID
        System.out.println("\n---- TESTE DELETE POR ID----\n");
        System.out.println("Digite um ID: ");
        int id = sc.nextInt();
        vendedorDao.deleteById(id);
        System.out.println("Usuario deletado com sucesso!");
    
	}

}
