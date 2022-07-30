import java.util.Date;

import model.entidades.Departamento;
import model.entidades.Vendedor;

public class App {

    public static void main(String[] args) {

        Departamento d1 = new Departamento("books", 1);

        Vendedor v1 = new Vendedor(1, "Jean", "jean@gail.com", new Date(), 3000.0, d1);
        
        System.out.println(v1);
    }
}