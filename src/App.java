import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.dao.VendedorDao;
import model.entidades.Departamento;
import model.entidades.Vendedor;



public class App {

    public static void main(String[] args) throws IOException, ParseException {

        Scanner sc = new Scanner(System.in);

        Integer op = 1;

        while (op != 3) {
            System.out.println("==> DAO CRUD OPTIONS <==");

            System.out.println("\n1 - Opções do Vendedor");
            System.out.println("2 - Opções do Departamento");
            System.out.println("3 - Sair");

            System.out.print("\nInforme o número da opção que deseja: ");
            op = sc.nextInt();
            
            switch (op) {

                case 1:
                    menuVendedor();
                    break;

                case 2:
                    menuDepartamento();
                    break;


                case 3:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção Inválida !");
                    break;
            }
        }

        sc.close();

    }

    public static void menuVendedor() throws ParseException {

        VendedorDao vendedorDao = DaoFactory.createVendedorDao();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        Integer op;
        do {

            System.out.println("==> OPÇÕES DO VENDEDOR <==");

            System.out.println("\n1 - Buscar Vendedor (ID)");
            System.out.println("2 - Listar Vendedores por Departamento");
            System.out.println("3 - Listar Todos os vendedores");
            System.out.println("4 - cadastrar vendedor");
            System.out.println("5 - Atualizar Dados do vendedor");
            System.out.println("6 - Deletar vendedor");
            System.out.println("7 - Voltar");

            System.out.print("\nInforme o número da opção que deseja: ");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.print("Informe o ID do vendedor: ");
                    int id = sc.nextInt();
                    Vendedor v1 = vendedorDao.findById(id);
                    System.out.println();
                    System.out.println("\n" + v1);
                    break;

                case 2:
                    System.out.print("Informe o ID do Departamento: ");
                    id = sc.nextInt();
                    Departamento departamento = new Departamento(null, id);
                    List<Vendedor> vendedores = vendedorDao.findByDepartment(departamento);
                    for (Vendedor i : vendedores) {
                        System.out.println(i);
                    }
                    break;

                case 3:
                    vendedores = vendedorDao.findAll();
                    for (Vendedor i : vendedores) {
                        System.out.println(i);
                    }
                    break;

                case 4:
                    v1 = new Vendedor();
                    System.out.print("Nome do Vendedor: ");
                    v1.setNome(sc.next());
                    System.out.print("Email do Vendedor: ");
                    v1.setEmail(sc.next());
                    System.out.print("Aniversário do Vendedor: ");
                    v1.setBirthDate(sdf.parse(sc.next()));
                    System.out.print("Salário Base do Vendedor: ");
                    v1.setBaseSalary(sc.nextDouble());
                    System.out.print("Código do Departamento: ");
                    Departamento d1 = new Departamento(null, sc.nextInt());
                    v1.setDepartamento(d1);
                    vendedorDao.insert(v1);
                    break;

                case 5:
                    System.out.print("Informe o ID do Vendedor que deseja modificar: ");
                    v1 = vendedorDao.findById(sc.nextInt());
                    System.out.print("Nome do Vendedor: ");
                    v1.setNome(sc.next());
                    System.out.print("Email do Vendedor: ");
                    v1.setEmail(sc.next());
                    System.out.print("Aniversário do Vendedor: ");
                    v1.setBirthDate(sdf.parse(sc.next()));
                    System.out.print("Salário Base do Vendedor: ");
                    v1.setBaseSalary(sc.nextDouble());
                    System.out.print("Código do Departamento: ");
                    d1 = new Departamento(null, sc.nextInt());
                    v1.setDepartamento(d1);
                    vendedorDao.update(v1);
                    break;

                case 6:
                    System.out.print("Informe o Id do Vendedor que deseja deletar: ");
                    id = sc.nextInt();
                    vendedorDao.deleteById(id);
                    break;

                case 7:
                    break;


                default:
                    System.out.println("Insira uma opção válida!");
                    break;

            }

        } while (op != 7);

    }

    public static void menuDepartamento() {

        DepartamentoDao departamentoDao = DaoFactory.createDepartamentoDao();
        Scanner sc = new Scanner(System.in);
        Integer op;

        do {

            System.out.println("==> OPÇÕES DO VENDEDOR <==");

            System.out.println("\n1 - Buscar Departamento (ID)");
            System.out.println("2 - Listar Todos os Departammentos");
            System.out.println("3 - Cadastrar Departaento");
            System.out.println("4 - Atualizar Dados do Departamento");
            System.out.println("5 - Deletar Departamento");
            System.out.println("6 - Voltar");

            System.out.print("\nInforme o número da opção que deseja: ");
            op = sc.nextInt();

            switch (op) {
                case 1:
                
                    Departamento d1 = new Departamento();
                    System.out.print("Informe o Id do departamento que deseja Pesquisar: ");
                    d1 = departamentoDao.findById(sc.nextInt());
                    System.out.println(d1);
                    break;

            
                case 2:
                    List<Departamento> departamentos = departamentoDao.findAll();

                    for(Departamento i : departamentos){
                        System.out.println(i); 
                    }
                    break;

                case 3:
                    System.out.print("Nome do Departamento: ");
                    d1 = new Departamento(sc.next(), 0);
                    departamentoDao.insert(d1);
                    break;

                case 4:
                    System.out.print("Informe o ID do departmento que deseja modiicar: ");
                    d1 = departamentoDao.findById(sc.nextInt());
                    System.out.print("Nome do departamento: ");
                    d1.setNome(sc.next());
                    departamentoDao.update(d1);
                    break;

                case 5:
                    System.out.print("Informe o Id do departamento que deseja deletar: ");
                    departamentoDao.deleteById(sc.nextInt());
                    break;

                case 6:

                    break;

                default:
                    System.out.println("Insira uma opção válida!");
                    break;

            }
            
        } while (op != 6);
        
    }
}