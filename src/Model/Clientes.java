package Model;

public class Clientes {
   private int id_cliente;
   private String nome;
   private String cpf;
   private String telefone;
    private String email;
   private String rua;
   private String numero_casa;
   private String bairro;
   
  public Clientes(String nome, String cpf,String telefone, String email, String rua, String numero_casa, String bairro) {
        this.id_cliente = id_cliente;
        this.nome = nome;
        this.cpf = cpf;
        this.rua = rua;
        this.numero_casa = numero_casa;
        this.bairro = bairro;
        this.telefone = telefone;
        this.email = email;
    }
    
    public Clientes(){
        
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
