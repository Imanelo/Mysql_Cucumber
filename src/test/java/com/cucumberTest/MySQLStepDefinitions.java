package com.cucumberTest;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;

import java.sql.*;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MySQLStepDefinitions {
    private Connection connection;
    private ResultSet resultSet;

    @Given("I connect to the MySQL database")
    public void connect_to_mysql_database() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cucumber_test_db";
        String user = "cucumber";
        String password = "CucumberTest"; // Replace with your MySQL root password
        connection = DriverManager.getConnection(url, user, password);
    }

    @When("I execute the SQL query {string}")
    public void execute_sql_query(String query) throws SQLException {
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
    }

    @Then("I should get the following users:")
    public void verify_users(DataTable dataTable) throws SQLException {
        List<Map<String, String>> userList = dataTable.asMaps(String.class, String.class);

        int rowCount = 0;
        while (resultSet.next() && rowCount < userList.size()) {
            Map<String, String> expectedUser = userList.get(rowCount);

            String expectedName = expectedUser.get("name");
            int expectedAge = Integer.parseInt(expectedUser.get("age"));

            String actualName = resultSet.getString("name");
            int actualAge = resultSet.getInt("age");

            // Use Hamcrest matchers for assertion
            assertThat(actualName, is(equalTo(expectedName)));
            assertThat(actualAge, is(equalTo(expectedAge)));

            rowCount++;
        }

        // Close the connection and release resources
        resultSet.close();
        connection.close();
    }
}
