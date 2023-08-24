package controller;

import java.sql.Connection;
import java.util.List;
import javax.swing.JOptionPane;
import model.Student;
import db.JavaDb;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import view.StudentAdd;

public class StudentDAOimpl implements StudentDAO<Student>{

@Override
    public void save(Student students) {
        try{
            Connection con = JavaDb.getConnection();
            String sql = "INSERT INTO etudiant(nom, postnom, prenom, nationalite, age) VALUES(?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, students.getNom());
            ps.setString(2, students.getPostnom());
            ps.setString(3, students.getPrenom());
            ps.setString(4, students.getNationalite());
            ps.setInt(5, students.age);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Enregistré");
            
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    @Override
    public void update(Student students) {
       try{
           Connection con=JavaDb.getConnection();
           String sql="UPDATE etudiant SET nom=?, postnom=?, prenom=?, nationalite=?, age=? WHERE id=?";
           PreparedStatement ps=con.prepareStatement(sql);
           ps.setString(1, students.getNom());
           ps.setString(2, students.getPostnom());
           ps.setString(3, students.getPrenom());
           ps.setString(4, students.getNationalite());
           ps.setInt(5, students.age);
           ps.setInt(6, students.getId());
           ps.executeUpdate();
           
           JOptionPane.showMessageDialog(null, "Updated");
       }catch(Exception e){
           e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
       }
    }

    @Override
    public void delete(Student students) {
        try{
           Connection con=JavaDb.getConnection();
           String sql="DELETE from etudiant WHERE id=?";
           PreparedStatement ps=con.prepareStatement(sql);
           ps.setInt(1, students.getId());
           ps.executeUpdate();
           
           JOptionPane.showMessageDialog(null, "Deleted");
       }catch(Exception e){
           e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
       }
    }

    @Override
    public Student get(int id) {
        Student st=new Student();
        try{
            Connection con = JavaDb.getConnection();
            String sql = "SELECT * FROM etudiant WHERE id = ?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                st.setId(rs.getInt("id"));
                st.setNom(rs.getString("nom"));
                st.setPostnom(rs.getString("postnom"));
                st.setPrenom(rs.getString("prenom"));
                st.setNationalite(rs.getString("nationalite"));
                st.age= Integer.parseInt(rs.getString("age"));
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
        return st;
    }

    
    @Override
    public List<Student> list() {
    List<Student> list = new ArrayList<Student>();
    try{
        Connection con = JavaDb.getConnection();
        String sql = "SELECT * FROM etudiant";
        PreparedStatement ps=con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Student st=new Student();
            st.setId(rs.getInt("id"));
            st.setNom(rs.getString("nom"));
            st.setPostnom(rs.getString("postnom"));
            st.setPrenom(rs.getString("prenom"));
            st.setNationalite(rs.getString("nationalite"));
            st.age = rs.getInt("age");
            
            list.add(st);
        }
    }catch(Exception e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error");
    }
    return list;
    }    

   
    
}
