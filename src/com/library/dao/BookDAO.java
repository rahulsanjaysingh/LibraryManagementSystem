package com.library.dao;

import com.library.model.Book;
import com.library.util.DBUtil;
import java.sql.*;
import java.util.*;

public class BookDAO {

    public List<Book> getAll() throws Exception {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection c = DBUtil.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("available_copies")   // ✅ FIX
                ));
            }
        }
        return list;
    }

    public Book findById(int id) throws Exception {
        String sql = "SELECT * FROM books WHERE id=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("available_copies") // ✅ FIX
                );
            }
        }
        return null;
    }

    public void create(Book b) throws Exception {
        String sql =
            "INSERT INTO books(title,author,available_copies) VALUES (?,?,?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAuthor());
            ps.setInt(3, b.getAvailable());
            ps.executeUpdate();
        }
    }

    public void update(Book b) throws Exception {
        String sql =
            "UPDATE books SET title=?,author=?,available_copies=? WHERE id=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAuthor());
            ps.setInt(3, b.getAvailable());
            ps.setInt(4, b.getId());
            ps.executeUpdate();
        }
    }

    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM books WHERE id=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }
}
