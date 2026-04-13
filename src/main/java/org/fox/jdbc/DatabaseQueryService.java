package org.fox.jdbc;

import org.fox.jdbc.dto.Client;
import org.fox.jdbc.dto.MaxProjectCountClient;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> result = new ArrayList<>();

        try {
            String sql = new String(
                    Files.readAllBytes(Paths.get("sql/find_max_projects_client.sql"))
            );

            Connection conn = Database.getInstance().getConnection();

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    String name = rs.getString("name");
                    int count = rs.getInt("project_count");

                    result.add(new MaxProjectCountClient(name, count));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Client> findAllClients() {
        List<Client> result = new ArrayList<>();

        try {
            String sql = new String(
                    Files.readAllBytes(Paths.get("sql/find_all_clients.sql"))
            );

            Connection conn = Database.getInstance().getConnection();

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");

                    result.add(new Client(id, name));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}