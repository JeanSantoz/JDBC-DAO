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

    }
}