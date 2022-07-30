package model.dao;

import db.DB;

public class DaoFactory {
    
    public static VendedorDao createVendedorDao(){
        return new VendedorDaoJDBC(DB.getConnection());
    }


}