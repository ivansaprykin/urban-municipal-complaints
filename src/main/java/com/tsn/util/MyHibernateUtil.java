package com.tsn.util;

import com.tsn.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class MyHibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {



        //String username = dbUri.getUserInfo().split(":")[0];
        //String password = dbUri.getUserInfo().split(":")[1];
        //String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
//String dbUrl = "jdbc:mysql://" + host + ":" + port + "/tsn?characterEncoding=UTF-8&amp;autoReconnect=true";
//        URI dbUri = null;
//        try {
//            dbUri = new URI(System.getenv("DATABASE_URL"));
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        String dbUrl = System.getenv("JDBC_DATABASE_URL");
//        String username = System.getenv("JDBC_DATABASE_USERNAME");
//        String password = System.getenv("JDBC_DATABASE_PASSWORD");

        URI dbUri = null;
        try {
            dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath() + "?characterEncoding=UTF-8";

        // TODO add auto-reconnect=true

/*
        // Build a Registry with our configuration properties
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();


        // create the session factory
        return config.buildSessionFactory(serviceRegistry);*/

        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
//                .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
//                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
                .setProperty("hibernate.connection.username", username)
                .setProperty("hibernate.connection.password", password)
                .setProperty("hibernate.connection.url", dbUrl)
                .setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext")
                .setProperty("hibernate.order_updates", "true")
              .setProperty("hibernate.hbm2ddl.auto", "update") // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//                .setProperty("hibernate.hbm2ddl.auto", "create")

                .setProperty("hibernate.c3p0.min_size", "2")
                .setProperty("hibernate.c3p0.max_size", "4")
                .setProperty("hibernate.c3p0.maxConnectionAge", "0")
                .setProperty("hibernate.c3p0.testConnectionOnCheckout", "true")
                .setProperty("hibernate.c3p0.testConnectionOnCheckin", "false")
                .setProperty("hibernate.c3p0.idleConnectionTestPeriod", "0")

                .addAnnotatedClass(Role.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Document.class)
                .addAnnotatedClass(Application.class)
                .addAnnotatedClass(Comment.class)
                .addAnnotatedClass(Article.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties());
        SessionFactory factory = configuration.buildSessionFactory(builder.build());
        return factory;

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
// JDBC driver name and database URL
    // String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
    //String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
    //String dbUrl = "jdbc:mysql://" + host + ":" + port + "/tsn?characterEncoding=UTF-8&amp;autoReconnect=true";

    //String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    //  Database credentials
    //String username = "adminDX3YQpR";
    //String password = "i9RnGpGx5SAX";
}
