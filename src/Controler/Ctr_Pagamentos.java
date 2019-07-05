package Controler;

import Model.Clientes;
import Model.Pagamentos;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import util.Conexao;

public class Ctr_Pagamentos {
    private Conexao mysql = new Conexao();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public int totalRegistros;
    
    public boolean inserir(Pagamentos pagamento){
        sSQL = "insert into tb_pagamentos (cliente,quarto,dias_hospedado,total_cobrado)" + 
                    "values(?,?,?,?)";
        try{
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, pagamento.getCliente());
            pst.setString(2, pagamento.getQuarto());
            pst.setInt(3, pagamento.getDias_hospedado());
            pst.setDouble(4, pagamento.getTotal_cobrado());
            
            
            int n = pst.executeUpdate();
            if(n != 0){
                return true;
            }else{
                return false;
            }
            
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    public boolean deletar(int id_pagamento){
        sSQL = "delete from tb_pagamentos where id_pagamentos=?";
        try{
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, id_pagamento);
            
            int n = pst.executeUpdate();
            if(n != 0){
                return true;
            }else{
                return false;
            }
        
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    public ArrayList<Pagamentos> selecionarTodos(){
        Conexao con = new Conexao();
        String sql = "select * from tb_pagamentos";
        ArrayList<Pagamentos> modelo = new ArrayList<Pagamentos>();
        try{
            PreparedStatement sentenca = con.conectar().prepareStatement(sql);
           
            ResultSet rs = sentenca.executeQuery();
            while (rs.next()){
                Pagamentos pagamento = new Pagamentos();
                pagamento.setId_pagamento(rs.getInt("id_pagamentos"));
                pagamento.setCliente(rs.getString("cliente"));
                pagamento.setQuarto(rs.getString("quarto"));
                pagamento.setDias_hospedado(rs.getInt("dias_hospedado"));
                pagamento.setTotal_cobrado(rs.getDouble("total_cobrado"));
                modelo.add(pagamento);
            }
        }catch(SQLException ex){
            System.out.println("Erro na sentença SQL: "+ex.getMessage());
        }
        return modelo;
    }
    
    public ArrayList<Pagamentos> buscar(String buscar) {
        Conexao con = new Conexao();
        String sql = "select * from tb_pagamentos where cliente = '" + buscar + "'";
        ArrayList<Pagamentos> modelo = new ArrayList<Pagamentos>();
        try {
            PreparedStatement sentenca = con.conectar().prepareStatement(sql);

            ResultSet rs = sentenca.executeQuery();
            while (rs.next()) {
                Pagamentos pagamento = new Pagamentos();
                pagamento.setId_pagamento(rs.getInt("id_pagamentos"));
                pagamento.setCliente(rs.getString("cliente"));
                pagamento.setQuarto(rs.getString("quarto"));
                pagamento.setDias_hospedado(rs.getInt("dias_hospedado"));
                pagamento.setTotal_cobrado(rs.getDouble("total_cobrado"));
                modelo.add(pagamento);
            }
        } catch (SQLException ex) {
            System.out.println("Erro na sentença SQL: " + ex.getMessage());
        }
        return modelo;
    }
    
    public void gerarPDF(){
        Conexao con = new Conexao();
        Document doc = new Document();
        ArrayList<Pagamentos> pagamentos = this.selecionarTodos();
        String arquivoPDF = "relatorioPagamentos.pdf";
        
        try{
            PdfWriter.getInstance(doc,new FileOutputStream(arquivoPDF));
            doc.open();
            
            Paragraph p = new Paragraph("Relatorio de Pagamentos");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph("");
            doc.add(p);
            
           PdfPTable table = new PdfPTable(5);
           
           PdfPCell cell1 = new PdfPCell(new Paragraph("id"));
           PdfPCell cell2 = new PdfPCell(new Paragraph("Cliente"));
           PdfPCell cell3 = new PdfPCell(new Paragraph("Quarto"));
           PdfPCell cell4 = new PdfPCell(new Paragraph("Dias Hospedado"));
           PdfPCell cell5 = new PdfPCell(new Paragraph("Total Cobrado"));
           
           table.addCell(cell1);
           table.addCell(cell2);
           table.addCell(cell3);
           table.addCell(cell4);
           table.addCell(cell5);
           
            for (Pagamentos pg : pagamentos) {
                cell1 = new PdfPCell(new Paragraph(pg.getId_pagamento()+""));
                cell2 = new PdfPCell(new Paragraph(pg.getCliente()));
                cell3 = new PdfPCell(new Paragraph(pg.getQuarto()));
                cell4 = new PdfPCell(new Paragraph(pg.getDias_hospedado()+""));
                cell5 = new PdfPCell(new Paragraph(pg.getTotal_cobrado()+""));

                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
            }
            doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File(arquivoPDF));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao gerar o Arquivo PDF");
        }
    }
}
