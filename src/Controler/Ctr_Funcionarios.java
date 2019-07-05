package Controler;

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

public class Ctr_Funcionarios {
    private Conexao mysql = new Conexao();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public int totalRegistros;
    
    public boolean inserir(Funcionarios funcionario){
        sSQL = "insert into tb_funcionarios (nome,cpf,rua,numero_casa,bairro,num_car_trab,data_nascimento,salario,data_entrada)" + 
                    "values(?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, funcionario.getNome());
            pst.setString(2, funcionario.getCpf());
            pst.setString(3, funcionario.getRua());
            pst.setString(4, funcionario.getNumero_casa());
            pst.setString(5, funcionario.getBairro());
            pst.setString(6, funcionario.getNum_car_trab());
            pst.setString(7, funcionario.getData_nascimento());
            pst.setDouble(8, funcionario.getSalario());
            pst.setString(9, funcionario.getData_entrada());
            
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
    
    public boolean editar(Funcionarios funcionario,int id_funcionario){
        sSQL = "update tb_funcionarios set nome=?,cpf=?,rua=?,numero_casa=?,bairro=?,num_car_trab=?,data_nascimento=?,salario=?,data_entrada=?"
                + "where id_funcionario=?";
        try{
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, funcionario.getNome());
            pst.setString(2, funcionario.getCpf());
            pst.setString(3, funcionario.getRua());
            pst.setString(4, funcionario.getNumero_casa());
            pst.setString(5, funcionario.getBairro());
            pst.setString(6, funcionario.getNum_car_trab());
            pst.setString(7, funcionario.getData_nascimento());
            pst.setDouble(8, funcionario.getSalario());
            pst.setString(9, funcionario.getData_entrada());
            pst.setInt(10, id_funcionario);
            
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
    
    public boolean deletar(int id_funcionario){
        sSQL = "delete from tb_funcionarios where id_funcionario=?";
        try{
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, id_funcionario);
            
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
    
    public ArrayList<Funcionarios> selecionarTodos(){
        Conexao con = new Conexao();
        String sql = "select * from tb_funcionarios";
        ArrayList<Funcionarios> modelo = new ArrayList<Funcionarios>();
        try{
            PreparedStatement sentenca = con.conectar().prepareStatement(sql);
           
            ResultSet rs = sentenca.executeQuery();
            while (rs.next()){
                Funcionarios funcionario = new Funcionarios();
                funcionario.setId_funcionario(rs.getInt("id_funcionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setRua(rs.getString("rua"));
                funcionario.setNumero_casa(rs.getString("numero_casa"));
                funcionario.setBairro(rs.getString("bairro"));
                funcionario.setNum_car_trab(rs.getString("num_car_trab"));
                funcionario.setData_nascimento(rs.getString("data_nascimento"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setData_entrada(rs.getString("data_entrada"));
                modelo.add(funcionario);
            }
        }catch(SQLException ex){
            System.out.println("Erro na sentença SQL: "+ex.getMessage());
        }
        return modelo;
    }
    
     public ArrayList<Funcionarios> buscar(String buscar) {
        Conexao con = new Conexao();
        String sql = "select * from tb_funcionarios where cpf = " + buscar;
        ArrayList<Funcionarios> modelo = new ArrayList<Funcionarios>();
        try {
            PreparedStatement sentenca = con.conectar().prepareStatement(sql);

            ResultSet rs = sentenca.executeQuery();
            while (rs.next()) {
                Funcionarios funcionario = new Funcionarios();
                funcionario.setId_funcionario(rs.getInt("id_funcionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setRua(rs.getString("rua"));
                funcionario.setNumero_casa(rs.getString("numero_casa"));
                funcionario.setBairro(rs.getString("bairro"));
                funcionario.setNum_car_trab(rs.getString("num_car_trab"));
                funcionario.setData_nascimento(rs.getString("data_nascimento"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setData_entrada(rs.getString("data_entrada"));
                modelo.add(funcionario);
            }
        } catch (SQLException ex) {
            System.out.println("Erro na sentença SQL: " + ex.getMessage());
        }
        return modelo;
    }
    
     public void gerarPDF(){
        Conexao con = new Conexao();
        Document doc = new Document();
        ArrayList<Funcionarios> funcionarios = this.selecionarTodos();
        String arquivoPDF = "relatorioFuncionarios.pdf";
        
        try{
            PdfWriter.getInstance(doc,new FileOutputStream(arquivoPDF));
            doc.open();
            
            Paragraph p = new Paragraph("Relatorio de Funcionarios");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph("");
            doc.add(p);
            
           PdfPTable table = new PdfPTable(10);
           
           PdfPCell cell1 = new PdfPCell(new Paragraph("id"));
           PdfPCell cell2 = new PdfPCell(new Paragraph("Nome"));
           PdfPCell cell3 = new PdfPCell(new Paragraph("Rua"));
           PdfPCell cell4 = new PdfPCell(new Paragraph("Numero"));
           PdfPCell cell5 = new PdfPCell(new Paragraph("Bairro"));
           PdfPCell cell6 = new PdfPCell(new Paragraph("Cpf"));
           PdfPCell cell7 = new PdfPCell(new Paragraph("Cart.de.Trab"));
           PdfPCell cell8 = new PdfPCell(new Paragraph("Data Nascimento"));
           PdfPCell cell9 = new PdfPCell(new Paragraph("Data Entrada"));
           PdfPCell cell10 = new PdfPCell(new Paragraph("Salario"));
           
           table.addCell(cell1);
           table.addCell(cell2);
           table.addCell(cell3);
           table.addCell(cell4);
           table.addCell(cell5);
           table.addCell(cell6);
           table.addCell(cell7);
           table.addCell(cell8);
           table.addCell(cell9);
           table.addCell(cell10);
           
            for (Funcionarios fu : funcionarios) {
                cell1 = new PdfPCell(new Paragraph(fu.getId_funcionario()+""));
                cell2 = new PdfPCell(new Paragraph(fu.getNome()));
                cell3 = new PdfPCell(new Paragraph(fu.getRua()));
                cell4 = new PdfPCell(new Paragraph(fu.getNumero_casa()));
                cell5 = new PdfPCell(new Paragraph(fu.getBairro()));
                cell6 = new PdfPCell(new Paragraph(fu.getCpf()));
                cell7 = new PdfPCell(new Paragraph(fu.getNum_car_trab()));
                cell8 = new PdfPCell(new Paragraph(fu.getData_nascimento()));
                cell9 = new PdfPCell(new Paragraph(fu.getData_entrada()));
                cell10 = new PdfPCell(new Paragraph(fu.getSalario()+""));

                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);
                table.addCell(cell7);
                table.addCell(cell8);
                table.addCell(cell9);
                table.addCell(cell10);
            }
            doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File(arquivoPDF));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao gerar o Arquivo PDF");
        }
    }
    
}
