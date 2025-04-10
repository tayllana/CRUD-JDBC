package com.JDBC.JDBC_testing.persistence.entity;

import com.JDBC.JDBC_testing.persistence.ConectionUtil;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void insert(final UserEntity user) {
        // USO: userDao.insert(user);
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        try (
            var connection = ConectionUtil.getConection();
            // Aqui adicionamos Statement.RETURN_GENERATED_KEYS
            var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            // Define os valores para os placeholders
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());

            // Executa a atualização
            statement.executeUpdate();

            // Obtém a chave gerada automaticamente
            try (var resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    user.setId(resultSet.getLong(1)); // Define o ID gerado no objeto UserEntity
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void update(final UserEntity user) {
        // USO: userDao.update(user);
        String sql = "UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?";
        try (
            var connection = ConectionUtil.getConection();
            var statement = connection.prepareStatement(sql)
        ) {
            // Define os valores para os placeholders
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getId()); // Define o ID do usuário a ser atualizado

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void delete(final Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (
            var connection = ConectionUtil.getConection();
            var statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<UserEntity> findAll() {
        // USO: userDao.findAll().forEach(System.out::println);
        List<UserEntity> users = new ArrayList<>();

        try (
            var connection = ConectionUtil.getConection();
            var statement = connection.createStatement()
        ) {
            // Executa a atualização
            statement.executeQuery("SELECT * FROM users");

            var resultSet = statement.getResultSet();
            while (resultSet.next()) {
                var user = new UserEntity();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    public UserEntity findById(final Long id) {
        // USO: System.out.println(userDao.findById(1L));
        var user = new UserEntity();

        try (
            var connection = ConectionUtil.getConection();
            var statement = connection.createStatement()
        ) {
            // Executa a atualização
            statement.executeQuery("SELECT * FROM users WHERE id = " + id);
            var resultSet = statement.getResultSet();

            if (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
