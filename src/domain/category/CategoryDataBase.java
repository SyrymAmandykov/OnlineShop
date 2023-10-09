package domain.category;

import dataBaseConnection.Driver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CategoryDataBase {

    //getCategoryById, getCategoryByName, addNewCategory, updateCategory

    public static Category getCategoryById(Long id) {
        try (PreparedStatement preparedStatement = Driver.connection.prepareStatement("SELECT c.id as id, c.name as name FROM category WHERE c.id = ?;")) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Category(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                );
            }
        } catch (Exception e) {
            System.out.println("Failed to get Category by id");
        }
        return null;
    }

    public static Category getCategoryByName(String name){
        try (PreparedStatement preparedStatement = Driver.connection.prepareStatement("SELECT c.id as id, c.name as name FROM category WHERE c.name = ?;")) {

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Category(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                );
            }
        } catch (Exception e) {
            System.out.println("Failed to get Category by name");
        }
        return null;
    }

    public static void updateCategory(Long id, String name){
        try(PreparedStatement preparedStatement = Driver.connection.prepareStatement("UPDATE categoty SET id = ?, name = ?;")){

            preparedStatement.setLong(1,id);
            preparedStatement.setString(2,name);

            preparedStatement.executeUpdate();

        } catch (Exception e){
            System.out.println("Error when updating categories by id");
        }
    }


    public static void addNewCategory(Long id, String name){
        try(PreparedStatement preparedStatement = Driver.connection.prepareStatement(
                "INSERT INTO order(id, name) VALUES (default, ?);")){

            preparedStatement.setLong(1,id);
            preparedStatement.setString(2,name);

            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println("Error to add new Category");
        }
    }

//    public static void deleteCategory(Long id, String name){
//
//        try(PreparedStatement preparedStatement = Driver.connection.prepareStatement("DELETE categoty SET id = ?, name = ?;")){
//
//            preparedStatement.setLong(1,id);
//            preparedStatement.setString(2,name);
//
//            preparedStatement.execute(deleteCategory(id,name));
//
//        } catch (Exception e){
//            System.out.println("Error when updating categories by id");
//        }
//    }

}
