import java.util.Date;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entidades.Departamento;
import model.entidades.Vendedor;

public class App {

    public static void main(String[] args) {

        VendedorDao v1Dao = DaoFactory.createVendedorDao();
        Vendedor v1 = v1Dao.findById(1);
        
        System.out.println(v1);
    }
}