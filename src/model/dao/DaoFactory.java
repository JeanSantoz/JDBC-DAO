package model.dao;

import db.DB;

public class DaoFactory {
    
    public static VendedorDao createVendedorDao(){
        return new VendedorDaoJDBC(DB.getConnection());
    }

    public static DepartamentoDaoJDBC createDepartamentoDao(){
        return new DepartamentoDaoJDBC(DB.getConnection());
    }

    



}