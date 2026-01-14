package db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static HikariDataSource hds;

    public static Connection getConnection() {
        if(hds==null) {
            Properties props = loadProperties();
            HikariConfig hConfig = new HikariConfig();

            hConfig.setJdbcUrl(props.getProperty("dburl"));
            hConfig.setUsername(props.getProperty("user"));
            hConfig.setPassword(props.getProperty("password"));

            hConfig.setMaximumPoolSize(5);
            hConfig.setMinimumIdle(2);
            hConfig.setIdleTimeout(150000);
            hConfig.setConnectionTimeout(30000);

            hConfig.addDataSourceProperty("cachePrepStmts", "true");
            hConfig.addDataSourceProperty("prepStmtCacheSize", "250");
            hConfig.addDataSourceProperty("prepStmtCacheqlLimit", "2048");

            hds = new HikariDataSource(hConfig);
        }
        try {
            return hds.getConnection();
        } catch (SQLException e) {
            throw new DbException("Error connection: " + e.getMessage());
        }
    }

    public static void closeConnection() {
        if(hds!=null){
            hds.close();
        }
    }

    private static Properties loadProperties() {
        try (InputStream is = DB.class.getClassLoader().getResourceAsStream("db.properties")) {
            if(is==null) {
                throw new DbException("File db.properties not found in resources!");
            }
            Properties props = new Properties();
            props.load(is);
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static void closeStatement(Statement stt) {
        if(stt!=null) {
            try {
                stt.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void beginTransaction(Connection conn) {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DbException("Error start transaction: " + e.getMessage());
        }
    }

    public static void commit(Connection conn) {
        try {
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DbException("Error to confirm transaction: " + e.getMessage());
        }
    }

    public static void rollback(Connection conn) {
        try {
            conn.rollback();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DbException("Error to rollback transaction: " + e.getMessage());
        }
    }
}
