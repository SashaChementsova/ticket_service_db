package org.chementsova;

import org.chementsova.dao.*;
import org.chementsova.dao.daoImpl.*;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan
public class DataConfig {

    @Bean
    public DataSource dataSource() {
        PGPoolingDataSource dataSource = new PGPoolingDataSource();

        FileInputStream fileInputStream;

        Properties prop = new Properties();

        String dbURL;

        String dbPassword;

        String dbUsername;

        try {
            fileInputStream = new FileInputStream("src/main/resources/config.properties");
            prop.load(fileInputStream);
            dbURL = prop.getProperty("db.host");
            dbUsername = prop.getProperty("db.username");
            dbPassword = prop.getProperty("db.password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        dataSource.setUser(dbUsername);
        dataSource.setPassword(dbPassword);
        dataSource.setURL(dbURL);

        return dataSource;
    }

}
