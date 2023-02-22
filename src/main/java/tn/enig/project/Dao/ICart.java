package tn.enig.project.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.enig.project.Entities.Cart;

@Repository
public interface ICart extends CrudRepository<Cart, Long> {

}
