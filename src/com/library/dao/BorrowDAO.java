package com.library.dao;

import com.library.model.BorrowRecord;
import com.library.util.DBUtil;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class BorrowDAO {

    public List<BorrowRecord> getAll() throws Exception {
        List<BorrowRecord> list = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM borrow_records")) {
            while (rs.next()) {
                list.add(new BorrowRecord(
                    rs.getInt("id"),
                    rs.getInt("book_id"),
                    rs.getInt("member_id"),
                    rs.getDate("borrow_date").toLocalDate(),
                    rs.getDate("return_date") == null ? null :
                        rs.getDate("return_date").toLocalDate(),
                    rs.getBoolean("returned")
                ));
            }
        }
        return list;
    }

    public void issueBook(int bookId, int memberId) throws Exception {
        try (Connection c = DBUtil.getConnection()) {
            c.setAutoCommit(false);
            try {
                PreparedStatement p1 = c.prepareStatement(
                    "UPDATE books SET available=available-1 WHERE id=? AND available>0");
                p1.setInt(1, bookId);
                if (p1.executeUpdate() == 0)
                    throw new SQLException("No copies available");

                PreparedStatement p2 = c.prepareStatement(
                    "INSERT INTO borrow_records(book_id,member_id,borrow_date,returned) VALUES (?,?,?,false)");
                p2.setInt(1, bookId);
                p2.setInt(2, memberId);
                p2.setDate(3, Date.valueOf(LocalDate.now()));
                p2.executeUpdate();

                c.commit();
            } catch (Exception e) {
                c.rollback();
                throw e;
            }
        }
    }

    public void returnBook(int borrowId) throws Exception {
    String sql =
        "UPDATE borrow_records SET returned = 1, return_date = ? WHERE id = ?";

    try (Connection con = DBUtil.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setDate(1, java.sql.Date.valueOf(LocalDate.now())); // ✅ FIX
        ps.setInt(2, borrowId);
        ps.executeUpdate();
    }

    // increment available copies
    String updateBook =
        "UPDATE books SET available_copies = available_copies + 1 " +
        "WHERE id = (SELECT book_id FROM borrow_records WHERE id = ?)";

    try (Connection con = DBUtil.getConnection();
         PreparedStatement ps = con.prepareStatement(updateBook)) {

        ps.setInt(1, borrowId);
        ps.executeUpdate();
    }
}


    public boolean delete(int id) throws Exception {
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps =
                 c.prepareStatement("DELETE FROM borrow_records WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }
}
