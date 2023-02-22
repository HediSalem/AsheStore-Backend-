package tn.enig.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import tn.enig.project.Dao.ICart;
import tn.enig.project.Dao.IPaymentHistory;
import tn.enig.project.Dao.IPerson;
import tn.enig.project.Dao.IProduct;
import tn.enig.project.Entities.Cart;
import tn.enig.project.Entities.PaymentHistory;
import tn.enig.project.Entities.Person;
import tn.enig.project.Entities.Product;
import tn.enig.project.services.PersonService;

@Configuration
class LoadDatabase {

  private static final Logger log = (Logger) LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(IProduct productDao, IPerson personDao, IPaymentHistory paymentDao, ICart cartDao) {

    return args -> {
    	ArrayList<String> sizes= new ArrayList<String>();
    	sizes.add("small");
    	sizes.add("meduim");
    	sizes.add("large");
    	sizes.add("xlarge");
    	Product prod = new Product(null, "Shoes", 50.99f, "Red heels for woman ", "fashion-high-heels-pumps-66856.jpg", "shoes",12,"gray",sizes,"Tranding","Gucci",30) ;
    	 log.info("Preloading " +productDao.save(prod));
    	 
    	 List<Product> products = new ArrayList<>();
     	products.add(prod);
     	
    	 Cart cart= new Cart("shoes",prod.getPrice() * 1, null, null);
		 log.info("Preloading " +cartDao.save(cart));
		 
		 
    	 
    	 
		PaymentHistory pay = new PaymentHistory(new Date());
		pay.setCart(cart);
		 log.info("Preloading " +paymentDao.save(pay));
		PaymentHistory pay1 = new PaymentHistory(new Date());
		pay1.setCart(cart);
		 log.info("Preloading " +paymentDao.save(pay1));
		List<PaymentHistory> payments = new ArrayList<PaymentHistory>();
		payments.add(pay);
		payments.add(pay1);
		
		Person person = new Person("asma", "salem","asma@gmail.com", "123", "Gabes", "25000000",
				"client", "cerif", cart);
		
      log.info("Preloading " + personDao.save(person));
      log.info("Preloading " + personDao.save(new Person("ali", "ben salah","ali@gmail.com", "25e4afd937fdd13e81466118d72e112f", "Gabes", "25000000",
				"client", "cerif", cart)));
      
      cart.setProducts(products);
      cart.setPerson(person);
      cartDao.save(cart);

	  Person admin = new Person("admin", "admin","admin@gmail.com", "123", "Gabes", "25000000",
				"admin", "cerif", cart);
				personDao.save(admin);
      
    };
  }
}

//	@Override
//	public void run(String... args) throws Exception {
//		Product prod = new Product("Shoes", 50, 1, "Red heels for woman ", "jpg", "shoes");
//		this.productDao.save(prod);
//		PaymentHistory pay = new PaymentHistory(new Date());
//		List<PaymentHistory> payments = new ArrayList<PaymentHistory>();
//		payments.add(pay);
//		List<Product> products = new ArrayList<>();
//		products.add(prod);
//		this.cartDao.save(new Cart("shoes", prod.getPrice() * prod.getQuantity(), products, payments));
//		Person person = new Person("ali", "ali@gmail.com", "25e4afd937fdd13e81466118d72e112f", "Gabes", 25000000,
//				"client", "cerif", null);
//		PersonService.addPerson(person);
//
//	}

