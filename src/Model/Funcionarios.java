package Model;

public class Funcionarios {
    private int id_funcionario;
   private String nome;
   private String cpf;
   private String rua;
   private String numero_casa;
   private String bairro;
   private String num_car_trab;
   private String data_nascimento;
   private double salario;
   private String data_entrada;

    public Funcionarios(String nome, String cpf, String rua, String numero_casa, String bairro, String num_car_trab, String data_nascimento, double salario, String data_entrada) {
        this.id_funcionario = id_funcionario;
        this.nome = nome;
        this.cpf = cpf;
        this.rua = rua;
        this.numero_casa = numero_casa;
        this.bairro = bairro;
        this.num_car_trab = num_car_trab;
        this.data_nascimento = data_nascimento;
        this.salario = salario;
        this.data_entrada = data_entrada;
    } 
    
    public Funcionarios(){
        
    }
    
    

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero_casa() {
        return numero_casa;
    }

    public void setNumero_casa(String numero_casa) {
        this.numero_casa = numero_casa;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNum_car_trab() {
        return num_car_trab;
    }

    public void setNum_car_trab(String num_car_trab) {
        this.num_car_trab = num_car_trab;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(String data_entrada) {
        this.data_entrada = data_entrada;
    }
   
   
}
