package com.echovue.demo;

import net.conjur.api.Conjur;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
public class DemoApplication {

	@Value("conjur.authn.hostId")
	private String conjurHostId;
	@Value("conjur.authn.apiKey")
	private String conjurAPIKey;
	@Value("spring.datasource.url")
	private String datasourceUrl;
	@Value("spring.datasource.driver-class-name")
	private String datasourceDriverClass;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public DataSource dataSource() {

		Conjur conjur = new Conjur(conjurHostId, conjurAPIKey);

		String datasourceUsername = conjur.variables().retrieveSecret("db/username");
		String datasourcePassword = conjur.variables().retrieveSecret("db/password");

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUsername(datasourceUsername);
		dataSource.setPassword(datasourcePassword);
		dataSource.setUrl(datasourceUrl);
		dataSource.setDriverClassName(datasourceDriverClass);

		return dataSource;
	}
}
