import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entidades.Departamento;
import model.entidades.Vendedor;

public class App {

    public static void main(String[] args) {

        VendedorDao v1Dao = DaoFactory.createVendedorDao();

        System.out.println("==> Teste findById <==");
        Vendedor v1 = v1Dao.findById(1);
        System.out.println(v1);

        System.out.println("\n==> Teste findByDepartment <==");
        Departamento departamento = new Departamento(null, 2);
        List<Vendedor> vendedores = v1Dao.findByDepartment(departamento);

        for (Vendedor i : vendedores) {
            System.out.println(i);
        }

        System.out.println("\n==> Teste findAll <==");
        vendedores = v1Dao.findAll();

        for (Vendedor i : vendedores) {
            System.out.println(i);
        }

        System.out.println("\n==> Teste Insert <==");
        Vendedor v2 = new Vendedor(1, "Jo", "jo@gmail.com", new Date(), 1800.00, departamento);
        v1Dao.insert(v2);

       // Teste Update
        v2.setBaseSalary(1250.50);
        v1Dao.update(v2);

        //Teste Delete
        v1Dao.deleteById(9);
    }
}