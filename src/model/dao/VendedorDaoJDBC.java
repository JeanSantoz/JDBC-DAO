package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.entidades.Departamento;
import model.entidades.Vendedor;

public class VendedorDaoJDBC implements VendedorDao {

    private Connection conn;

    public VendedorDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Vendedor vendedor) {

    }

    @Override
    public void update(Vendedor vendedor) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Vendedor findById(Integer id) {
        
        PreparedStatement st = null;
        ResultSet rs = null;  

        try {

            st = conn.prepareStatement(
                "SELECT seller.*,department.Name as DepName "  
                + "FROM seller INNER JOIN department " 
                + "ON seller.DepartmentId = department.Id " 
                + "WHERE seller.Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){

                Departamento departamento = new Departamento();
                departamento.setId(rs.getInt("DepartmentId"));
                departamento.setNome(rs.getString("DepName"));

                Vendedor vendedor = new Vendedor();
                vendedor.setId(rs.getInt("Id"));
                vendedor.setName(rs.getString("Name"));
                vendedor.setEmail(rs.getString("Email"));
                vendedor.setBirthDate(rs.getDate("BirthDate"));
                vendedor.setBaseSalary(rs.getDouble("BaseSalary"));
                vendedor.setDepartamento(departamento);

                return vendedor;

            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Vendedor> findAll() {

        return null;
    }

}