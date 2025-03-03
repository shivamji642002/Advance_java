//package com.training.config;
//
//import java.util.Properties;
//
//import javax.sql.DataSource;
//
//import org.hibernate.SessionFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//@ComponentScan(basePackages = "com.training") // It will accept base package
//public class HibernateConfig {
//
//	@Bean
//	public LocalSessionFactoryBean sessionFactory() {
//		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
//		// Database specific method
//		sessionFactoryBean.setDataSource(postgreDataSource());
//		sessionFactoryBean.setPackagesToScan(new String[] { "com.training.model" });
//		// Hibernate Specific method
//		sessionFactoryBean.setHibernateProperties(hibernatePostgreProperties());
//		return sessionFactoryBean;
//	}
//	@Bean
//	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//		HibernateTransactionManager txManager = new HibernateTransactionManager();
//		txManager.setSessionFactory(sessionFactory);
//		return txManager;
//	}
//
////	private Properties hibernateMySQLProperties() {
////		Properties properties = new Properties();
////		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
////		properties.put("hibernate.show_sql", true);
////		properties.put("hibernate.format_sql", true);
////		properties.put("hibernate.hbm2ddl.auto", "update");
////		return properties;
////	}
////	private DataSource mySqlDataSource() {
////		DriverManagerDataSource dataSource = new DriverManagerDataSource();
////		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
////		dataSource.setUrl("jdbc:mysql://localhost:3306/mydb");
////		dataSource.setUsername("root");
////		dataSource.setPassword("1234");
////		return dataSource;
////	}
//
//
//	 // Configure DataSource for PostgreSQL
//    @Bean
//    public DataSource postgreDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver"); // PostgreSQL Driver
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/mydb"); // PostgreSQL URL
//        dataSource.setUsername("postgres"); // Change to your PostgreSQL username
//        dataSource.setPassword("1234"); // Change to your PostgreSQL password
//        return dataSource;
//    }
//
//    // Configure Hibernate Properties for PostgreSQL
//    @Bean
//    public Properties hibernatePostgreProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"); // Correct Dialect
//        properties.put("hibernate.show_sql", true);
//        properties.put("hibernate.format_sql", true);
//        properties.put("hibernate.hbm2ddl.auto", "update"); // Change to "validate" or "none" in production
//        return properties;
//    }
//}
