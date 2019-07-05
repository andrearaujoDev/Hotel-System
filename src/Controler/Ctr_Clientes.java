package Controler;

import Model.Clientes;
import Model.Funcionarios;
import Model.Pagamentos;
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

public class Ctr_Clientes {
 private Conexao mysql = new Conexao();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public int totalRegistros;
    
    public boolean inserir(Clientes cliente){
        sSQL = "insert into tb_clientes (nome_cliente,cpf,telefone,email,rua,numero_casa,bairro)" + 
                    "values(?,?,?,?,?,?,?)";
        try{
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());
            pst.setString(3, cliente.getTelefone());
            pst.setString(4, cliente.getEmail());
            pst.setString(5, cliente.getRua());
            pst.setString(6, cliente.getNumero_casa());
            pst.setString(7, cliente.getBairro());
            
            
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
    
    public boolean editar(Clientes cliente,int id_cliente){
        sSQL = "update tb_clientes set nome_cliente=?,cpf=?,telefone=?,email=?,rua=?,numero_casa=?,bairro=?"
                + "where id_cliente=?";
        try{
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());
            pst.setString(3, cliente.getTelefone());
            pst.setString(4, cliente.getEmail());
            pst.setString(5, cliente.getRua());
            pst.setString(6, cliente.getNumero_casa());
            pst.setString(7, cliente.getBairro());
            pst.setInt(8, id_cliente);
            
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
    
    public boolean deletar(int id_cliente){
        sSQL = "delete from tb_clientes where id_cliente=?";
        try{
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, id_cliente);
            
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
    
    public ArrayList<Clientes> selecionarTodos(){
        Conexao con = new Conexao();
        String sql = "select * from tb_clientes";
        ArrayList<Clientes> modelo = new ArrayList<Clientes>();
        try{
            PreparedStatement sentenca = con.conectar().prepareStatement(sql);
           
            ResultSet rs = sentenca.executeQuery();
            while (rs.next()){
                Clientes cliente = new Clientes();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome_cliente"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));           
                cliente.setRua(rs.getString("rua"));
                cliente.setNumero_casa(rs.getString("numero_casa"));
                cliente.setBairro(rs.getString("bairro"));
                modelo.add(cliente);
            }
        }catch(SQLException ex){
            System.out.println("Erro na sentença SQL: "+ex.getMessage());
        }
        return modelo;
    }
    
    public ArrayList<Clientes> buscar(String buscar) {
        Conexao con = new Conexao();
        String sql = "select * from tb_clientes where cpf = " + buscar;
        ArrayList<Clientes> modelo = new ArrayList<Clientes>();
        try {
            PreparedStatement sentenca = con.conectar().prepareStatement(sql);

            ResultSet rs = sentenca.executeQuery();
            while (rs.next()) {
                Clientes cliente = new Clientes();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome_cliente"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));           
                cliente.setRua(rs.getString("rua"));
                cliente.setNumero_casa(rs.getString("numero_casa"));
                cliente.setBairro(rs.getString("bairro"));
                modelo.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println("Erro na sentença SQL: " + ex.getMessage());
        }
        return modelo;
    }
    
    public void gerarPDF(){
        Conexao con = new Conexao();
        Document doc = new Document();
        ArrayList<Clientes> clientes = this.selecionarTodos();
        String arquivoPDF = "relatorioClientes.pdf";
        
        try{
            PdfWriter.getInstance(doc,new FileOutputStream(arquivoPDF));
            doc.open();
            
            Paragraph p = new Paragraph("Relatorio de Clientes");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph("");
            doc.add(p);
            
           PdfPTable table = new PdfPTable(8);
           
           PdfPCell cell1 = new PdfPCell(new Paragraph("id"));
           PdfPCell cell2 = new PdfPCell(new Paragraph("Nome"));
           PdfPCell cell3 = new PdfPCell(new Paragraph("Cpf"));
           PdfPCell cell4 = new PdfPCell(new Paragraph("Telefone"));
           PdfPCell cell5 = new PdfPCell(new Paragraph("Email"));
           PdfPCell cell6 = new PdfPCell(new Paragraph("Rua"));
           PdfPCell cell7 = new PdfPCell(new Paragraph("Numero"));
           PdfPCell cell8 = new PdfPCell(new Paragraph("Bairro"));
           
           table.addCell(cell1);
           table.addCell(cell2);
           table.addCell(cell3);
           table.addCell(cell4);
           table.addCell(cell5);
           table.addCell(cell6);
           table.addCell(cell7);
           table.addCell(cell8);
           
            for (Clientes cl : clientes) {
                cell1 = new PdfPCell(new Paragraph(cl.getId_cliente()+""));
                cell2 = new PdfPCell(new Paragraph(cl.getNome()));
                cell3 = new PdfPCell(new Paragraph(cl.getCpf()));
                cell4 = new PdfPCell(new Paragraph(cl.getTelefone()));
                cell5 = new PdfPCell(new Paragraph(cl.getEmail()));
                cell6 = new PdfPCell(new Paragraph(cl.getRua()));
                cell7 = new PdfPCell(new Paragraph(cl.getNumero_casa()));
                cell8 = new PdfPCell(new Paragraph(cl.getBairro()));

                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);
                table.addCell(cell7);
                table.addCell(cell8);
            }
            doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File(arquivoPDF));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao gerar o Arquivo PDF");
        }
    }
}
