package model.dao;

public class DaoFactory {
    
    public static VendedorDao createVendedorDao(){
        return new VendedorDaoJDBC();
    }


}