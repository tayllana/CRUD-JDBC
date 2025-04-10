package com.JDBC.JDBC_testing;

import com.JDBC.JDBC_testing.persistence.ConectionUtil;
import com.JDBC.JDBC_testing.persistence.entity.UserDAO;
import com.JDBC.JDBC_testing.persistence.entity.UserEntity;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class JdbcTestingApplication {

	private final static UserDAO userDao = new UserDAO();

	public static void main(String[] args) {

		// O flyway verifica a existencia de migration em resources.db.migration e cria tabelas no banco
		var flyway = Flyway.configure()
				.dataSource("jdbc:mysql://localhost:3307/JDBCTEST", "root", "mysql")
				.baselineOnMigrate(true) // Cria a tabela flyway_schema_history se não existir
				.load() // Carrega as configurações
				.migrate(); // Executa as migrações

//		var user = new UserEntity();
//		user.setId(3L);
//		user.setName("Pessoa alterada");
//		user.setEmail("pessoa2@email.com");
//		user.setPassword("senha2");
//
//		userDao.findAll().forEach(System.out::println);
//		userDao.update(user);
//		userDao.findAll().forEach(System.out::println);
		userDao.delete(3L);
    }
}
