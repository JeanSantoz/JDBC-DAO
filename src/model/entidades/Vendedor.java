package model.entidades;
import java.io.Serializable;
import java.util.Date;

public class Vendedor implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int id;
    private String nome;
    private String email;
    private Date birthDate;
    private Double baseSalary;
    private Departamento departamento;

    public Vendedor(int id, String nome, String email, Date birthDate, Double baseSalary, Departamento departamento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.birthDate = birthDate;
        this.baseSalary = baseSalary;
        this.departamento = departamento;
    }

    public Vendedor() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Departamento getDepartamento(){
        return departamento;
    }

    public void setDepartamento(Departamento departamento){
        this.departamento = departamento;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vendedor other = (Vendedor) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Vendedor [baseSalary=" + baseSalary + ", birthDate=" + birthDate + ", departamento=" + departamento
                + ", email=" + email + ", id=" + id + ", name=" + nome + "]";
    }


    
   

    

}
