package Model;

public class Produtos {
    private int id_produtos;
    private String nome;
    private String tipo;
    private int quantidade;
    private double preco;
    private String observacao;
    
    public Produtos(String nome, String tipo, int quantidade, double preco,String observacao) {
       this.id_produtos = id_produtos;
       this.nome = nome;
       this.tipo = tipo;
       this.quantidade = quantidade;
       this.preco = preco;
       this.observacao = observacao;
    }
    
    public Produtos(){
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getId_produtos() {
        return id_produtos;
    }

    public void setId_produtos(int id_produtos) {
        this.id_produtos = id_produtos;
    }
    
    
    
}
