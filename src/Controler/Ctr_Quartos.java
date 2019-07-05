package Controler;

import Model.Pagamentos;
import Model.Quartos;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.List;
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

public class Ctr_Quartos {

    private Conexao mysql = new Conexao();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public int totalRegistros;

   public boolean inserir(Quartos quartos) {
        sSQL = "insert into tb_quartos (numero,andar,descricao,caracteristicas,preco_diaria,estado,tipo_quarto)"
                + "values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, quartos.getNumero());
            pst.setString(2, quartos.getAndar());
            pst.setString(3, quartos.getDescricao());
            pst.setString(4, quartos.getCaracteristicas());
            pst.setDouble(5, quartos.getValorDiaria());
            pst.setString(6, quartos.getEstado());
            pst.setString(7, quartos.getTipoQuarto());

            int n = pst.executeUpdate();
            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean editar(Quartos quartos,int id_quarto) {
        sSQL = "update tb_quartos set numero=?,andar=?,descricao=?,caracteristicas=?,preco_diaria=?,estado=?,tipo_quarto=?"
                + "where id_quartos=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, quartos.getNumero());
            pst.setString(2, quartos.getAndar());
            pst.setString(3, quartos.getDescricao());
            pst.setString(4, quartos.getCaracteristicas());
            pst.setDouble(5, quartos.getValorDiaria());
            pst.setString(6, quartos.getEstado());
            pst.setString(7, quartos.getTipoQuarto());
            pst.setInt(8, id_quarto);

            int n = pst.executeUpdate();
            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean deletar(int id_quarto) {
        sSQL = "delete from tb_quartos where id_quartos=?";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, id_quarto);

            int n = pst.executeUpdate();
            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public ArrayList<Quartos> selecionarTodos() {
        Conexao con = new Conexao();
        String sql = "select * from tb_quartos";
        ArrayList<Quartos> modelo = new ArrayList<Quartos>();
        try {
            PreparedStatement sentenca = con.conectar().prepareStatement(sql);

            ResultSet rs = sentenca.executeQuery();
            while (rs.next()) {
                Quartos quartos = new Quartos();  
                quartos.setIdquartos(rs.getInt("id_quartos"));
                quartos.setNumero(rs.getString("numero"));
                quartos.setAndar(rs.getString("andar"));
                quartos.setDescricao(rs.getString("descricao"));
                quartos.setCaracteristicas(rs.getString("caracteristicas"));
                quartos.setValorDiaria(rs.getDouble("preco_diaria"));
                quartos.setEstado(rs.getString("estado"));
                quartos.setTipoQuarto(rs.getString("tipo_quarto"));
                modelo.add(quartos);
            }
        } catch (SQLException ex) {
            System.out.println("Erro na sentença SQL: " + ex.getMessage());
        }
        return modelo;
    }
    
    public ArrayList<Quartos> selecionarDisponiveis() {
        Conexao con = new Conexao();
        String sql = "select * from tb_quartos where estado = 'Disponivel'";
        ArrayList<Quartos> modelo = new ArrayList<Quartos>();
        try {
            PreparedStatement sentenca = con.conectar().prepareStatement(sql);

            ResultSet rs = sentenca.executeQuery();
            while (rs.next()) {
                Quartos quartos = new Quartos();  
                quartos.setIdquartos(rs.getInt("id_quartos"));
                quartos.setNumero(rs.getString("numero"));
                quartos.setAndar(rs.getString("andar"));
                quartos.setDescricao(rs.getString("descricao"));
                quartos.setCaracteristicas(rs.getString("caracteristicas"));
                quartos.setValorDiaria(rs.getDouble("preco_diaria"));
                quartos.setEstado(rs.getString("estado"));
                quartos.setTipoQuarto(rs.getString("tipo_quarto"));
                modelo.add(quartos);
            }
        } catch (SQLException ex) {
            System.out.println("Erro na sentença SQL: " + ex.getMessage());
        }
        return modelo;
    }
    
     public ArrayList<Quartos> buscar(String buscar) {
        Conexao con = new Conexao();
        String sql = "select * from tb_quartos where numero = " + buscar;
        ArrayList<Quartos> modelo = new ArrayList<Quartos>();
        try {
            PreparedStatement sentenca = con.conectar().prepareStatement(sql);

            ResultSet rs = sentenca.executeQuery();
            while (rs.next()) {
                Quartos quartos = new Quartos();  
                quartos.setIdquartos(rs.getInt("id_quartos"));
                quartos.setNumero(rs.getString("numero"));
                quartos.setAndar(rs.getString("andar"));
                quartos.setDescricao(rs.getString("descricao"));
                quartos.setCaracteristicas(rs.getString("caracteristicas"));
                quartos.setValorDiaria(rs.getDouble("preco_diaria"));
                quartos.setEstado(rs.getString("estado"));
                quartos.setTipoQuarto(rs.getString("tipo_quarto"));
                modelo.add(quartos);
            }
        } catch (SQLException ex) {
            System.out.println("Erro na sentença SQL: " + ex.getMessage());
        }
        return modelo;
    }
     
     public void gerarPDF(){
        Conexao con = new Conexao();
        Document doc = new Document();
        ArrayList<Quartos> quartos = this.selecionarTodos();
        String arquivoPDF = "relatorioQuartos.pdf";
        
        try{
            PdfWriter.getInstance(doc,new FileOutputStream(arquivoPDF));
            doc.open();
            
            Paragraph p = new Paragraph("Relatorio de Quartos");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph("");
            doc.add(p);
            
           PdfPTable table = new PdfPTable(8);
           
           PdfPCell cell1 = new PdfPCell(new Paragraph("id"));
           PdfPCell cell2 = new PdfPCell(new Paragraph("Numero"));
           PdfPCell cell3 = new PdfPCell(new Paragraph("Andar"));
           PdfPCell cell4 = new PdfPCell(new Paragraph("Descrição"));
           PdfPCell cell5 = new PdfPCell(new Paragraph("Caracteristicas"));
           PdfPCell cell6 = new PdfPCell(new Paragraph("Diaria"));
           PdfPCell cell7 = new PdfPCell(new Paragraph("Estado"));
           PdfPCell cell8 = new PdfPCell(new Paragraph("Tipo"));
           
           
           table.addCell(cell1);
           table.addCell(cell2);
           table.addCell(cell3);
           table.addCell(cell4);
           table.addCell(cell5);
           table.addCell(cell6);
           table.addCell(cell7);
           table.addCell(cell8);
           
            for (Quartos qu : quartos) {
                cell1 = new PdfPCell(new Paragraph(qu.getIdquartos()+""));
                cell2 = new PdfPCell(new Paragraph(qu.getNumero()));
                cell3 = new PdfPCell(new Paragraph(qu.getAndar()));
                cell4 = new PdfPCell(new Paragraph(qu.getDescricao()));
                cell5 = new PdfPCell(new Paragraph(qu.getCaracteristicas()));
                cell6 = new PdfPCell(new Paragraph(qu.getValorDiaria()+""));
                cell7 = new PdfPCell(new Paragraph(qu.getEstado()));
                cell8 = new PdfPCell(new Paragraph(qu.getTipoQuarto()));

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
