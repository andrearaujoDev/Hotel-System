package Controler;

import Model.Clientes;
import Model.Pagamentos;
import Model.Produtos;
import com.itextpdf.text.Document;
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
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.Conexao;

public class Ctr_Produtos {
    private Conexao mysql = new Conexao();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public int totalRegistros;
    
    
    public boolean inserir(Produtos produtos){
        sSQL = "insert into tb_produtos (nome,tipo,quantidade,preco_uni,observacao)" + 
                    "values(?,?,?,?,?)";
        try{
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, produtos.getNome());
            pst.setString(2, produtos.getTipo());
            pst.setInt(3, produtos.getQuantidade());
            pst.setDouble(4, produtos.getPreco());
            pst.setString(5, produtos.getObservacao());
            
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
    
    public boolean editar(Produtos produtos,int id_produto){
        sSQL = "update tb_produtos set nome=?,tipo=?,quantidade=?,preco_uni=?,observacao=?"
                + "where id_produtos=?";
        try{
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, produtos.getNome());
            pst.setString(2, produtos.getTipo());
            pst.setInt(3, produtos.getQuantidade());
            pst.setDouble(4, produtos.getPreco());
            pst.setString(5, produtos.getObservacao());
            pst.setInt(6, id_produto);
            
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
    
    public boolean deletar(int id_produto){
        sSQL = "delete from tb_produtos where id_produtos=?";
        try{
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, id_produto);
            
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
    
    public ArrayList<Produtos> selecionarTodos(){
        Conexao con = new Conexao();
        String sql = "select * from tb_produtos";
        ArrayList<Produtos> modelo = new ArrayList<Produtos>();
        try{
            PreparedStatement sentenca = con.conectar().prepareStatement(sql);
           
            ResultSet rs = sentenca.executeQuery();
            while (rs.next()){
                Produtos produtos = new Produtos();
                produtos.setId_produtos(rs.getInt("id_produtos"));
                produtos.setNome(rs.getString("nome"));
                produtos.setTipo(rs.getString("tipo"));
                produtos.setQuantidade(rs.getInt("quantidade"));
                produtos.setPreco(rs.getDouble("preco_uni"));           
                produtos.setObservacao(rs.getString("observacao"));
                modelo.add(produtos);
            }
        }catch(SQLException ex){
            System.out.println("Erro na sentença SQL: "+ex.getMessage());
        }
        return modelo;
    }
    
     public ArrayList<Produtos> buscar(String buscar) {
        Conexao con = new Conexao();
        String sql = "select * from tb_produtos where tipo = '" + buscar +"'";
        ArrayList<Produtos> modelo = new ArrayList<Produtos>();
        try {
            PreparedStatement sentenca = con.conectar().prepareStatement(sql);

            ResultSet rs = sentenca.executeQuery();
            while (rs.next()) {
                Produtos produtos = new Produtos();
                produtos.setId_produtos(rs.getInt("id_produtos"));
                produtos.setNome(rs.getString("nome"));
                produtos.setTipo(rs.getString("tipo"));
                produtos.setQuantidade(rs.getInt("quantidade"));
                produtos.setPreco(rs.getDouble("preco_uni"));
                produtos.setObservacao(rs.getString("observacao"));
                modelo.add(produtos);
            }
        } catch (SQLException ex) {
            System.out.println("Erro na sentença SQL: " + ex.getMessage());
        }
        return modelo;
    }
     
      public void gerarPDF(){
        Conexao con = new Conexao();
        Document doc = new Document();
        ArrayList<Produtos> produtos = this.selecionarTodos();
        String arquivoPDF = "relatorioProdutos.pdf";
        
        try{
            PdfWriter.getInstance(doc,new FileOutputStream(arquivoPDF));
            doc.open();
            
            Paragraph p = new Paragraph("Relatorio de Produtos");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph("");
            doc.add(p);
            
           PdfPTable table = new PdfPTable(6);
           
           PdfPCell cell1 = new PdfPCell(new Paragraph("id"));
           PdfPCell cell2 = new PdfPCell(new Paragraph("Nome"));
           PdfPCell cell3 = new PdfPCell(new Paragraph("Tipo"));
           PdfPCell cell4 = new PdfPCell(new Paragraph("Quantidade"));
           PdfPCell cell5 = new PdfPCell(new Paragraph("Preço"));
           PdfPCell cell6 = new PdfPCell(new Paragraph("Observação"));
           
           table.addCell(cell1);
           table.addCell(cell2);
           table.addCell(cell3);
           table.addCell(cell4);
           table.addCell(cell5);
           table.addCell(cell6);
           
            for (Produtos pr : produtos) {
                cell1 = new PdfPCell(new Paragraph(pr.getId_produtos() + ""));
                cell2 = new PdfPCell(new Paragraph(pr.getNome()));
                cell3 = new PdfPCell(new Paragraph(pr.getTipo()));
                cell4 = new PdfPCell(new Paragraph(pr.getQuantidade() + ""));
                cell5 = new PdfPCell(new Paragraph(pr.getPreco() + ""));
                cell6 = new PdfPCell(new Paragraph(pr.getObservacao()));

                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);
            }
            doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File(arquivoPDF));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao gerar o Arquivo PDF");
        }
    }
}
