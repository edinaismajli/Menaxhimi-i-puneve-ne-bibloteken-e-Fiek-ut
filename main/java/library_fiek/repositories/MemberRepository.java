
package library_fiek.repositories;

import library_fiek.models.Member;
import library_fiek.services.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    public List<Member> findAll() {
        String sql = "SELECT * FROM members ORDER BY id DESC";
        List<Member> members = new ArrayList<>();

        try (
                Connection connection = DatabaseService.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                members.add(mapMember(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Find members failed: " + e.getMessage());
        }

        return members;
    }

    public List<Member> search(String keyword) {
        String sql = """
                SELECT * FROM members
                WHERE full_name LIKE ? OR email LIKE ? OR phone LIKE ? OR member_type LIKE ?
                ORDER BY id DESC
                """;

        List<Member> members = new ArrayList<>();

        try (
                Connection connection = DatabaseService.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            String value = "%" + keyword + "%";

            statement.setString(1, value);
            statement.setString(2, value);
            statement.setString(3, value);
            statement.setString(4, value);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    members.add(mapMember(resultSet));
                }
            }
        } catch (SQLException e) {
            System.out.println("Search members failed: " + e.getMessage());
        }

        return members;
    }

    public void insert(Member member) {
        String sql = """
                INSERT INTO members(full_name, email, phone, member_type)
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection connection = DatabaseService.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, member.getFullName());
            statement.setString(2, member.getEmail());
            statement.setString(3, member.getPhone());
            statement.setString(4, member.getMemberType());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert member failed: " + e.getMessage());
        }
    }

    public void update(Member member) {
        String sql = """
                UPDATE members
                SET full_name = ?, email = ?, phone = ?, member_type = ?
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseService.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, member.getFullName());
            statement.setString(2, member.getEmail());
            statement.setString(3, member.getPhone());
            statement.setString(4, member.getMemberType());
            statement.setInt(5, member.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update member failed: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM members WHERE id = ?";

        try (
                Connection connection = DatabaseService.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete member failed: " + e.getMessage());
        }
    }

    private Member mapMember(ResultSet resultSet) throws SQLException {
        return new Member(
                resultSet.getInt("id"),
                resultSet.getString("full_name"),
                resultSet.getString("email"),
                resultSet.getString("phone"),
                resultSet.getString("member_type")
        );
    }
}

