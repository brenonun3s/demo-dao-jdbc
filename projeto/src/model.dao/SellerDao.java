package model.dao;

import java.util.List;

public interface SellerDao {
    
    void insert(Seller obj);
    void uptade(Seller obj);
    void deleteById(Integer id);
    Seller findById(Integer id);
    List<Seller> findAll();
    List<Seller> findByDepartment(Department department); 
}