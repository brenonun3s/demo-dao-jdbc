package model.dao;

import java.util.List;

public interface DepartmentDao {
    
    void insert(Department obj);
    void uptade(Department obj);
    void deleteById(Integer id);
    Department findById(Integer id);
    List<Department> findAll(); 
}
