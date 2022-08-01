package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.entidades.Departamento;
import model.entidades.Vendedor;

public class VendedorDaoJDBC implements VendedorDao {

    private Connection conn;

    public VendedorDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Vendedor vendedor) {

        PreparedStatement st = null;

        try  {
            conn = DB.getConnection();
            st = conn.prepareStatement(
                "INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
                    

            st.setString(1, vendedor.getNome());
            st.setString(2, vendedor.getEmail());
            st.setDate(3, new Date(vendedor.getBirthDate().getTime()));
            st.setDouble(4, vendedor.getBaseSalary());
            st.setInt(5, vendedor.getDepartamento().getId());

            int rows = st.executeUpdate();

            if(rows>0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    vendedor.setId(id);
                    System.out.println("Insert Efetuado !");  
                }
                DB.closeResultSet(rs);
                  
            }
            else{
                throw new DbException("Insert não executado !");
            }


        
        } 
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st); 
        }

    }

    @Override
    public void update(Vendedor vendedor) {

        PreparedStatement st = null;

        try {

            st = conn.prepareStatement(
                    "UPDATE seller "
                    + "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? " 
                    + "WHERE Id = ? ");

            st.setString(1, vendedor.getNome());
            st.setString(2, vendedor.getEmail());
            st.setDate(3, new Date(vendedor.getBirthDate().getTime()));
            st.setDouble(4, vendedor.getBaseSalary());
            st.setInt(5, vendedor.getDepartamento().getId());
            st.setInt(6, vendedor.getId());

            int rows = st.executeUpdate();

            if(rows != 0){
                System.out.println("Update Efetuado com sucesso!");
            }
            else{
                throw new DbException("Falha no Update!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement st = null;

        try {

            st = conn.prepareStatement(
                    "DELETE FROM seller WHERE Id = ? ");

            st.setInt(1, id);

            int rows = st.executeUpdate();

            if(rows == 0){
                throw new DbException("ID Inexistente !");
            }
            else{
                System.out.println("Vendedor de Id = " + id + " Deletado !");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

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

            if (rs.next()) {

                Departamento departamento = instanciarDepartamento(rs);

                Vendedor vendedor = instanciarVendedor(rs, departamento);

                return vendedor;

            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Vendedor> findByDepartment(Departamento departamento) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.Id "
                            + "WHERE DepartmentId = ? "
                            + "ORDER BY Name ");

            st.setInt(1, departamento.getId());
            rs = st.executeQuery();

            List<Vendedor> vendedores = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while (rs.next()) {

                Departamento dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null) {
                    dep = instanciarDepartamento(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Vendedor vendedor = instanciarVendedor(rs, dep);
                vendedores.add(vendedor);

            }
            return vendedores;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Vendedor> findAll() {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.Id "
                            + "ORDER BY Name");
            rs = st.executeQuery();

            List<Vendedor> vendedores = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while (rs.next()) {

                Departamento dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null) {
                    dep = instanciarDepartamento(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Vendedor vendedor = instanciarVendedor(rs, dep);
                vendedores.add(vendedor);

            }
            return vendedores;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Vendedor instanciarVendedor(ResultSet rs, Departamento departamento) throws SQLException {

        Vendedor vendedor = new Vendedor();
        vendedor.setId(rs.getInt("Id"));
        vendedor.setNome(rs.getString("Name"));
        vendedor.setEmail(rs.getString("Email"));
        vendedor.setBirthDate(rs.getDate("BirthDate"));
        vendedor.setBaseSalary(rs.getDouble("BaseSalary"));
        vendedor.setDepartamento(departamento);
        return vendedor;

    }

    private Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
        Departamento departamento = new Departamento();
        departamento.setId(rs.getInt("DepartmentId"));
        departamento.setNome(rs.getString("DepName"));
        return departamento;
    }

}