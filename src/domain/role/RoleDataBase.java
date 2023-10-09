package domain.role;

import dataBaseConnection.Driver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RoleDataBase {

    public static void addRole(String name){

        try(PreparedStatement preparedStatement = Driver.connection.prepareStatement("INSERT INTO role VALUES (default, ?);")){

        preparedStatement.setString(1,name);
        preparedStatement.executeUpdate();
        } catch (Exception e){
            System.out.println("Failed to add new role");
        }
    }

    public static List<Role> getAllRoles(){
        ArrayList<Role> roles = new ArrayList<>();

        try(PreparedStatement preparedStatement = Driver.connection.prepareStatement("SELECT * FROM role;")){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                roles.add(new Role(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                ));
            }

        } catch (Exception e){
            System.out.println("Failed to get All roles");
        }
        return roles;
    }
}
