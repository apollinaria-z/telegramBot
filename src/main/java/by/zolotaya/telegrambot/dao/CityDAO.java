package by.zolotaya.telegrambot.dao;

import by.zolotaya.telegrambot.model.City;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CityDAO {
    enum SQLCity {
        GET("SELECT id, name, description FROM citytable WHERE id = (?)"),
        INSERT("INSERT INTO citytable (id, name, description) VALUES (DEFAULT, (?), (?))"),
        UPDATE("UPDATE citytable SET name=(?), description=(?) WHERE id=(?)"),
        DELETE("DELETE FROM citytable WHERE id = (?)"),
        GETLIST("SELECT * FROM citytable");
        String QUERY;
        SQLCity(String QUERY) {
            this.QUERY = QUERY;
        }
    }

    public List<City> getAllCities() throws DAOException {
        List<City> clientList = new ArrayList<>();
        Connection connection = DbConnection.getInstance();
        try(PreparedStatement statement = connection.prepareStatement(SQLCity.GETLIST.QUERY)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                City newCity = new City(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
                clientList.add(newCity);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return clientList;
    }

    public City getCityById(int id) throws DAOException {
        City city = new City();
        Connection connection = DbConnection.getInstance();
        try(PreparedStatement statement = connection.prepareStatement(SQLCity.GET.QUERY)){
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                city.setId(rs.getInt("id"));
                city.setName(rs.getString("name"));
                city.setDescription(rs.getString("description"));
                }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return city;
    }

    public void addCity(City city) throws DAOException {
        Connection connection = DbConnection.getInstance();
        try(PreparedStatement statement = connection.prepareStatement(SQLCity.INSERT.QUERY)) {
            statement.setString(1, city.getName());
            statement.setString(2, city.getDescription());
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateCity(int id, City city) throws DAOException {
        Connection connection = DbConnection.getInstance();
        try(PreparedStatement statement = connection.prepareStatement(SQLCity.UPDATE.QUERY)) {
            statement.setString(1, city.getName());
            statement.setString(2, city.getDescription());
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteCity(int id) throws DAOException {
        Connection connection = DbConnection.getInstance();
        try(PreparedStatement statement = connection.prepareStatement(SQLCity.DELETE.QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public String cityList(){
        StringBuilder stringBuilder = new StringBuilder();
        for(City city : getAllCities()){
            stringBuilder.append(city.getName() + "\n");
        }
        return stringBuilder.toString();
    }

}
