package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.entidades.Departamento;

public class DepartamentoDaoJDBC implements DepartamentoDao {

    private Connection conn;

    public DepartamentoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Departamento departamento) {
        
        PreparedStatement st = null;

        try  {
            conn = DB.getConnection();
            st = conn.prepareStatement(
                "INSERT INTO department (Name) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);
                
            st.setString(1, departamento.getNome());

            int rows = st.executeUpdate();

            if(rows>0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    departamento.setId(id);
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
    public void update(Departamento departamento) {
        PreparedStatement st = null;

        try {

            st = conn.prepareStatement(
                    "UPDATE department "
                    + "SET Name = ? WHERE Id = ? ");

            st.setString(1, departamento.getNome());
            st.setInt(2, departamento.getId());
            

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
                    "DELETE FROM department WHERE Id = ? ");

            st.setInt(1, id);

            int rows = st.executeUpdate();

            if(rows == 0){
                throw new DbException("ID Inexistente !");
            }
            else{
                System.out.println("Departamento de Id = " + id + " Deletado !");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
        
    }

    @Override
    public Departamento findById(Integer id) {
        
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = conn.prepareStatement(
                    "SELECT department.* FROM department WHERE Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {

                Departamento departamento = instanciarDepartamento(rs);

                return departamento;

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
    public List<Departamento> findAll() {
        
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = conn.prepareStatement(
                    "SELECT * FROM department ORDER BY Name");
            rs = st.executeQuery();

            List<Departamento> departamentos = new ArrayList<>();

            while (rs.next()) {

                Departamento dep = new Departamento();

                dep.setId(rs.getInt("Id"));
                dep.setNome((rs.getString("Name")));
                departamentos.add(dep);
            }

            return departamentos;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
        Departamento departamento = new Departamento();
        departamento.setId(rs.getInt("Id"));
        departamento.setNome(rs.getString("Name"));
        return departamento;
    }
    
}