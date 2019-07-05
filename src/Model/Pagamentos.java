package Model;

public class Pagamentos {
     private int id_pagamento;
    private String cliente;
    private String quarto;
    private int dias_hospedado;
    private double diaria;
    private double total_cobrado;

    public Pagamentos(String cliente, String quarto, int dias_hospedado, double total_cobrado,double diaria) {
        this.id_pagamento = id_pagamento;
        this.cliente = cliente;
        this.quarto = quarto;
        this.dias_hospedado = dias_hospedado;
        this.total_cobrado = total_cobrado;
    }
    public Pagamentos(int dias_hospedado,double total_cobrado){
        this.dias_hospedado = dias_hospedado;
        this.total_cobrado = total_cobrado;
    }
    
    public Pagamentos(){
        
    }
    public int getId_pagamento() {
        return id_pagamento;
    }

    public void setId_pagamento(int id_pagamento) {
        this.id_pagamento = id_pagamento;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getQuarto() {
        return quarto;
    }

    public void setQuarto(String quarto) {
        this.quarto = quarto;
    }

    public int getDias_hospedado() {
        return dias_hospedado;
    }

    public void setDias_hospedado(int dias_hospedado) {
        this.dias_hospedado = dias_hospedado;
    }

    public double getTotal_cobrado() {
        return total_cobrado;
    }

    public void setTotal_cobrado(double total_cobrado) {
        this.total_cobrado = total_cobrado;
    }

    public double getDiaria() {
        return diaria;
    }

    public void setDiaria(double diaria) {
        this.diaria = diaria;
    }
    
    
}
