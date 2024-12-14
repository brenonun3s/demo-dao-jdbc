import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import java.util.HashMap;
import java.util.Map;
import model.dao.SellerDao;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;

    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void uptade(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {
        return null;

    }

    @Override
    public Seller findById(Integer id){
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
            "SELECT seller.*,department.Name as DepName "
            + "FROM seller INNER JOIN department "
            + "ON seller.DepartmentId = department.Id "
            + "WHERE seller.Id = ?");

            st.setInt(1,id);
            rs = st.executeQuery();
            if (rs.next()) {
                Department dep = instantiateDepartment(rs);
                Seller obj = instantiateDepartment(rs, dep);
                return obj;
            }
            return null;
            
        } catch (SQLException e) {
            thrown new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;

    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setDepartment(dep);
        return obj;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

    @Override
    public List<Seller> findByDepartment() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                "SELECT seller.*,department.Name as DepName"
                + "FROM seller INNER JOIN department" 
                + "ON seller.DepartmentId = department.Id"
                + "WHERE DepartmentId = ?"
                + "ORDER BY Name");

            st.setInt(1, department.getId();)
            
            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = HashMap<>();

            while(rs.next()) {

                Department dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null){
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Seller obj = instantiateDepartment(rs, dep);
                
            }
            return list;
            
        } catch (SQLException e) {
            thrown new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

}