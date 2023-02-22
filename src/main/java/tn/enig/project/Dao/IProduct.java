package tn.enig.project.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.enig.project.Entities.Product;

@Repository
public interface IProduct extends CrudRepository<Product, Long> {

}
