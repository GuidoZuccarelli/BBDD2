package BBDD2.trabajo.Persistir;

import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import BBDD2.trabajo.model.Cart;
import BBDD2.trabajo.model.Master;
import BBDD2.trabajo.model.Product;
import BBDD2.trabajo.model.Site;

public class Persistir {
	private static SessionFactory sessions;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			System.out
					.println("----------------------- Setting up Hibernate -----------------------");
			Configuration cfg = new Configuration();
			cfg.configure();

			System.out.println("Building sessions.........");

			sessions = cfg.buildSessionFactory();
			

			System.out.println("Droping schema.........");
			new SchemaExport(cfg).drop(true, true);
			System.out.println("DONE.");

			System.out.println("Generating schema.........");
			new SchemaExport(cfg).create(true, true);
			System.out.println("DONE.");

			System.out.println("---------IMPLEMENTACION <HashSet>---------");
			run();
			System.out.println("---------FIN---------");
			

		} catch (Exception e) {
			System.out
					.println("------------------------FAIL.------------------------");
			e.printStackTrace();
		}
		
		
	}
	
	private static void run(){
	    Session session = sessions.openSession();
		Transaction tx = null;
		System.out.println("\n========================RUN========================\n");	
		try {
			//Instanciacion de carts
			Cart cart1 = new Cart();
			Cart cart2 = new Cart();
			Cart cart3 = new Cart();
			//Instanciacion de master
			Master master = new Master();
			//Instanciacion de product
			Product producto1 = new Product();
			// Instanciacion de site
			Site site1 = new Site("Home");
			// Creo vinculo entre site y cart
			cart1.setSite(site1);
			// Se agregan productos
			HashSet<Product> products = new HashSet<Product>();
			products.add(producto1);
			// Se agrega una lista de products en carts
			cart1.addProducts(products);
			// Se crea una lista de carts
			HashSet<Cart> carts = new HashSet<Cart>();
			carts.add(cart1);
			carts.add(cart2);
			carts.add(cart3);
			// Se crea una lista de sites
			HashSet<Site> sites = new HashSet<Site>();
			sites.add(site1);
			// Se agrega toda la estructura a master de carts y sites
			master.addCarts(carts);
			master.addSites(sites);
			//La transaccion iniciará...
				long start = System.nanoTime(); //Inicio del timer para medir la duraccion de la consulta
			
				tx = session.beginTransaction(); 
				session.save(master);
				session.flush();
				tx.commit();
		
				//Calculo de la duraccion en nanosegundos
				long elapsedTime = System.nanoTime() - start;   
				System.out.println("\nTiempo transcurrido: " + elapsedTime + " nanosegundos");
				
		} 
			
		catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			session.close();
		}
		
		session.close();
		System.out.println("\n=========================================================\n");
	}

}
