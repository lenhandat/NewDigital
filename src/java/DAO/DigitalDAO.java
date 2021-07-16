/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author ADMIN
 */
import DBcontext.DBContext;
import entity.Digital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DigitalDAO {

//    public List<Digital> getTop1(int numberArticle) throws Exception {
//        DBContext db = new DBContext();
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            String query = "select top (?) * from digital\n"
//                    + "order by timePost desc";
//            conn = db.getConnection();
//            ps = conn.prepareStatement(query);
//            ps.setInt(1, numberArticle);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Digital d = new Digital(rs.getInt("ID"),
//                        rs.getString("title"),
//                        rs.getString("description"),
//                        rs.getString("image"),
//                        rs.getString("author"),
//                        rs.getDate("timePost"),
//                        rs.getString("shortDes"));
//                return d;
//            }
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            db.closeConnection(rs, ps, conn);
//        }
//        return null;
//    }

    public Digital getOne(int id) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from digital where id = ?";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital d = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                return d;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
        return null;
    }

    public List<Digital> getTop5(int numberArticle) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            List<Digital> list = new ArrayList<>();
            String query = "select top (?) * from digital\n"
                    + "order by timePost desc";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, numberArticle);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital d = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                list.add(d);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
    }

    public List<Digital> search(String txt, int pageIndex, int pageSize) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            List<Digital> list = new ArrayList<>();
            String query = "select *from("
                    + "select ROW_NUMBER() over (order by ID ASC) as rn,*\n"
                    + "from digital where title \n"
                    + "like ?"
                    + ")as x\n"
                    + "where rn between ?*?-2"
                    + "and ?*?";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, "%" + txt + "%");
            ps.setInt(2, pageIndex);
            ps.setInt(3, pageSize);
            ps.setInt(4, pageIndex);
            ps.setInt(5, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital d = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                list.add(d);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
    }

    public int count(String txt) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select count(*) from digital \n"
                    + "where title like ?";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txt + "%");
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
    }
}
