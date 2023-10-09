package domain.order;

import dataBaseConnection.Driver;
import domain.category.Category;
import domain.role.Role;
import domain.users.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDataBase {

    // getAllOrders, getOrdersByTitle, getOrdersByCategoryId, getOrderById, updateOrder, addNewOrder
    public static List<Order> getAllOrders() {

        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = Driver.connection.prepareStatement(
                "SELECT " +
                        "o.id as id, " +
                        "o.title as title, " +
                        "o.description as description, " +
                        "o.price as price, " +

                        "u.id as author_id, " +
                        "u.email as email, " +
                        "u.password as password, " +
                        "u.fullName as full_name, " +
                        "u,phoneNumber as phone_number, " +

                        "c.id as category_id, " +
                        "c.name as category_name, " +

                        "r.id as role_id, " +
                        "r.name as role_name, " +

                        "FROM order o " +

                        "INNER JOIN online_shop.users u on u.id = o.author_id " +
                        "INNER JOIN online_shop.category c on c.id = o.category_id " +
                        "INNER JOIN online_shop.role r on r.id = u.role_id;")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(new Order(
                                resultSet.getLong("id"),
                                resultSet.getString("title"),
                                resultSet.getString("description"),
                                resultSet.getDouble("price"),
                                new Category(
                                        resultSet.getLong("category_id"),
                                        resultSet.getString("category_name")
                                ),
                                new Users(
                                        resultSet.getLong("author_id"),
                                        resultSet.getString("email"),
                                        resultSet.getString("password"),
                                        resultSet.getString("full_name"),
                                        resultSet.getInt("phone_number"),
                                        new Role(
                                                resultSet.getLong("role_id"),
                                                resultSet.getString("role_name")
                                        )
                                )
                        )
                );
            }

        } catch (Exception e) {
            System.out.println("Failed to get all orders");
        }
        return orders;
    }

    public static Order getOrdersByTitle(String title) {
        try (PreparedStatement preparedStatement = Driver.connection.prepareStatement(
                "SELECT " +
                        "o.id as id, " +
                        "o.title as title, " +
                        "o.description as description, " +
                        "o.price as price, " +

                        "u.id as author_id, " +
                        "u.email as email, " +
                        "u.password as password, " +
                        "u.full_name as full_name, " +
                        "u.phone_number as phone_number, " +

                        "c.id as category_id, " +
                        "c.name as category_name, " +

                        "r.id as role_id, " +
                        "r.name as role_name " +

                        "FROM order o " +
                        "INNER JOIN online_shop.users u on u.id = o.author_id " +
                        "INNER JOIN online_shop.category c on c.id = o.category_id " +
                        "INNER JOIN online_shop.role r on r.id = u.role_id " +
                        "WHERE o.title = ? "
        )
        ) {
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Order(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        new Category(
                                resultSet.getLong("category_id"),
                                resultSet.getString("category_name")
                        ),
                        new Users(
                                resultSet.getLong("author_id"),
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
            System.out.println("Failed to get Order by title");
        }
        return null;
    }

    public static List<Order> getOrdersByCategoryId(Long id) {
        ArrayList<Order> order = new ArrayList<>();

        try (PreparedStatement preparedStatement = Driver.connection.prepareStatement(
                "SELECT " +
                        "o.id as id, " +
                        "o.title as title, " +
                        "o.description as description, " +
                        "o.price as price, " +

                        "u.id as author_id, " +
                        "u.email as email, " +
                        "u.password as password, " +
                        "u.full_name as full_name, " +
                        "u.phone_number as phone_number, " +

                        "c.id as category_id, " +
                        "c.name as category_name, " +

                        "r.id as role_id, " +
                        "r.name as role_name " +

                        "FROM order o " +
                        "INNER JOIN online_shop.users u on u.id = o.author_id " +
                        "INNER JOIN online_shop.category c on c.id = o.category_id " +
                        "INNER JOIN online_shop.role r on r.id = u.role_id " +
                        "WHERE o.category_id = ? "
        )
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order.add(new Order(
                                resultSet.getLong("id"),
                                resultSet.getString("title"),
                                resultSet.getString("description"),
                                resultSet.getDouble("price"),
                                new Category(
                                        resultSet.getLong("category_id"),
                                        resultSet.getString("category_name")
                                ),
                                new Users(
                                        resultSet.getLong("author_id"),
                                        resultSet.getString("email"),
                                        resultSet.getString("password"),
                                        resultSet.getString("full_name"),
                                        resultSet.getInt("phone_number"),
                                        new Role(
                                                resultSet.getLong("role_id"),
                                                resultSet.getString("role_name")
                                        )
                                )

                        )
                );

            }
        } catch (Exception e) {
            System.out.println("Failed to get Oreder by title");
        }
        return null;
    }

    public static Order getOrderById(Long id) {

        try (PreparedStatement preparedStatement = Driver.connection.prepareStatement(
                "SELECT " +
                        "o.id as id, " +
                        "o.title as title, " +
                        "o.description as description, " +
                        "o.price as price, " +

                        "u.id as author_id, " +
                        "u.email as email, " +
                        "u.password as password, " +
                        "u.full_name as full_name, " +
                        "u.phone_number as phone_number, " +

                        "c.id as category_id, " +
                        "c.name as category_name, " +

                        "r.id as role_id, " +
                        "r.name as role_name " +

                        "FROM order o " +
                        "INNER JOIN online_shop.users u on u.id = o.author_id " +
                        "INNER JOIN online_shop.category c on c.id = o.category_id " +
                        "INNER JOIN online_shop.role r on r.id = u.role_id " +
                        "WHERE o.id = ?;")
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Order(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        new Category(
                                resultSet.getLong("category_id"),
                                resultSet.getString("category_name")
                        ),
                        new Users(
                                resultSet.getLong("author_id"),
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
            System.out.println("Failed to get Order by id");
        }
        return null;
    }

    public static void updateOrder(
            Long id,
            String title,
            String description,
            Double price,
            Category category,
            Users author) {

        try (PreparedStatement preparedStatement = Driver.connection.prepareStatement(
                "UPDATE order SET " +
                        "id = ?, " +
                        "title = ?, " +
                        "description = ?, " +
                        "price = ?, " +
                        "category_id = ?, " +
                        "author_id = ?"
        )) {
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, description);
            preparedStatement.setDouble(4, price);
            preparedStatement.setLong(5, category.getId());
            preparedStatement.setLong(6, author.getId());

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println("failed to update order");
        }

    }

    public static void addNewOrder(String title, String description, Double price,Long authorId, Long categoryId){
        try(PreparedStatement preparedStatement = Driver.connection.prepareStatement(
                "INSERT INTO order(title, description, price, author_id, category_id) VALUES (?, ?, ?, ?, ?);")){

            preparedStatement.setString(1,title);
            preparedStatement.setString(2,description);
            preparedStatement.setDouble(3,price);
            preparedStatement.setLong(4,authorId);
            preparedStatement.setLong(5,categoryId);

            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println("Error to add new Order");
        }
    }

}
