package com.fruitnvegie.fruitnvegieapi;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FruitNVegieApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FruitNVegieApiApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}




	/*@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws URISyntaxException {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		//entityManagerFactoryBean.setDataSource("fruit_n_vegie_shopping_cart");
		entityManagerFactoryBean.setPackagesToScan("com.fruitnvegie.fruitnvegieapi.dao");
		//additional config of factory

		return entityManagerFactoryBean;
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) throws URISyntaxException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}




	@PersistenceUnit
	EntityManagerFactory emf;

	@Bean
	public SessionFactory sessionFactory() {
		return emf.unwrap(SessionFactory.class);
	}


	 */










}

