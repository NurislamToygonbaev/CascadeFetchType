package org.example.config;

import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Address;
import org.example.entities.Company;
import org.example.entities.Programmer;
import org.example.entities.Project;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class ConfigUtil {
    public static SessionFactory getSessionFactory(){
        Properties properties = new Properties();
        properties.put(Environment.JAKARTA_JDBC_DRIVER, "org.postgresql.Driver");
        properties.put(Environment.JAKARTA_JDBC_URL, "jdbc:postgresql://localhost:5432/cascadefetchtype");
        properties.put(Environment.JAKARTA_JDBC_USER, "postgres");
        properties.put(Environment.JAKARTA_JDBC_PASSWORD, "rootroot");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.FORMAT_SQL, "true");

        Configuration configuration = new Configuration();
        configuration.addProperties(properties);
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(Project.class);
        configuration.addAnnotatedClass(Programmer.class);
        return configuration.buildSessionFactory();
    }
    public static EntityManagerFactory getEntityManagerFactory(){
        return getSessionFactory().unwrap(EntityManagerFactory.class);
    }
}
