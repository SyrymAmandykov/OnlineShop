package domain.users;

import dataBaseConnection.Driver;
import domain.role.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsersDataBase {

    //getUserByEmail, addNewUser, getAllUsers, getUserById, updateUser

    public static void addNewUser(String email, String password, String fullName, Integer phoneNumber, Long roleId) {
        try (PreparedStatement preparedStatement = Driver.connection.prepareStatement(
                "INSERT INTO users(email, password, full_name, phone_number, role_id) VALUES (?,?,?,?,?);"))
        // users название бд откуда тянем данные
        // Завернули в трай кетч получение данных из бд на свой локал,тк при коннекте могут возникнуть ошибки
        {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, fullName);
            preparedStatement.setInt(4, phoneNumber);
            preparedStatement.setLong(5, roleId);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Failed to add new user");
        }
    }

    public static Users getUserById(Long id) {
        try (PreparedStatement preparedStatement = Driver.connection.prepareStatement(
                "SELECT " +
                        "u.id as id, " +
                        "u.email as email, " +
                        "u.password as password, " +
                        "u.full_name as full_name, " +
                        "u.phone_number as phone_number, " +
                        "r.id as role_id, " +
                        "r.name as role_name" +
                        " FROM users u INNER JOIN " +
                        "online_shop.role r on r.id = u.role_id " +
                        "WHERE u.id = ? ;")) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Users(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getInt("phone_number"),
                        new Role(
                                resultSet.getLong("role_id"),
                                resultSet.getString("role_name")
                        )
                );
            }

        } catch (Exception e) {
            System.out.println("Failed to get user by id");
        }
        return null;
    }

    public static List<Users> getAllUsers() {
        ArrayList<Users> users = new ArrayList<>();

        try (PreparedStatement preparedStatement = Driver.connection.prepareStatement(
                "SELECT " +
                        "u.id as id, " +
                        "u.email as email, " +
                        "u.password as password, " +
                        "u.full_name as full_name, " +
                        "u.phone_number as phone_number, " +
                        "r.id as role_id, " +
                        "r.name as role_name" +
                        " FROM users u INNER JOIN " +
                        "online_shop.role r on r.id = u.role_id;")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new Users(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getInt("phone_number"),
                        new Role(
                                resultSet.getLong("role_id"),
                                resultSet.getString("role_name")
                        )
                        )
                );
            }

        } catch (Exception e) {
            System.out.println("Failed to get user by id");
        }
        return users;
    }

    public static void updateUser(Long id, String email, String password, String fullName, Integer phoneNumber, Long roleId) {

        try (PreparedStatement preparedStatement = Driver.connection.prepareStatement(
                "UPDATE users SET " +
                        "email = ?, " +
                        "password = ?, " +
                        "full_name = ?, " +
                        "phone_number = ?, " +
                        "id = ?," +
                        "role_id = ?;")) {

            preparedStatement.setLong(6, id);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, fullName);
            preparedStatement.setInt(4, phoneNumber);
            preparedStatement.setLong(5, roleId);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("failed to user update");
        }
    }

    public static Users getUserByEmail(String email) {
        try (PreparedStatement preparedStatement = Driver.connection.prepareStatement(
                "SELECT " +
                        "u.id as id, " +
                        "u.email as email, " +
                        "u.password as password, " +
                        "u.full_name as full_name, " +
                        "u.phone_number as phone_number, " +
                        "r.id as role_id, " +
                        "r.name as role_name" +
                        " FROM users u INNER JOIN " +
                        "online_shop.role r on r.id = u.role_id " +
                        "WHERE u.id = ? ;")) {

            preparedStatement.setString(2, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Users(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getInt("phone_number"),
                        new Role(
                                resultSet.getLong("role_id"),
                                resultSet.getString("role_name")
                        )
                );
            }

        } catch (Exception e) {
            System.out.println("Failed to get user by email");
        }
        return null;

    }
}
