package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// import javax.naming.spi.DirStateFactory.Result;

import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.utilities.ConnectionUtil;

public class UserDao {
    
    public User getUserByUsername(String username){
        User user = new User();
        try(Connection connection = ConnectionUtil.createConnection()) {
        
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
				return user;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public User createUser(UsernamePasswordAuthentication registerRequest) {
        User createdUser = new User();
        try (Connection connection = ConnectionUtil.createConnection()){
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            //Tells database to return the generated ID
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, registerRequest.getUsername());
            preparedStatement.setString(2, registerRequest.getPassword());
            
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while(rs.next()){
                createdUser.setUsername(registerRequest.getUsername());
                createdUser.setPassword(registerRequest.getPassword());
                createdUser.setId(rs.getInt(1));
            }

            return createdUser;

        }catch(SQLException e){
            e.printStackTrace();
			return null;
        }
    }

    public User getUserById(int id){
        User user = new User();
        try(Connection connection = ConnectionUtil.createConnection()) {
        
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
				return user;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public boolean deleteAccount(int id){
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println("Failed to delete account.");
            e.printStackTrace();
            return false;
        }
    }
}
