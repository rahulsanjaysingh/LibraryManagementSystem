package com.library.dao;

import com.library.model.Member;
import com.library.util.DBUtil;
import java.sql.*;
import java.util.*;

public class MemberDAO {

    public List<Member> getAll() throws Exception {
        List<Member> list = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM members")) {
            while (rs.next()) {
                list.add(new Member(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("mobile")
                ));
            }
        }
        return list;
    }

    public Member findById(int id) throws Exception {
        String sql = "SELECT * FROM members WHERE id=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Member(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("mobile")
                );
            }
        }
        return null;
    }

    public void create(Member m) throws Exception {
        String sql = "INSERT INTO members(name,email,mobile) VALUES (?,?,?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, m.getName());
            ps.setString(2, m.getEmail());
            ps.setString(3, m.getMobile());
            ps.executeUpdate();
        }
    }

    public void update(Member m) throws Exception {
        String sql = "UPDATE members SET name=?,email=?,mobile=? WHERE id=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, m.getName());
            ps.setString(2, m.getEmail());
            ps.setString(3, m.getMobile());
            ps.setInt(4, m.getId());
            ps.executeUpdate();
        }
    }

    public boolean delete(int id) throws Exception {
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps =
                 c.prepareStatement("DELETE FROM members WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }
}
