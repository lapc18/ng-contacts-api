package io.inab.contacts.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Basic persistence config class using SpringBoot Annotations.
 *
 * @see Configuration
 * @see EnableTransactionManagement
 * @see EnableJpaRepositories
 * @see EnableJpaAuditing
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"io.inab.contacts.infrastructure.repositories"})
@EnableJpaAuditing
public class PersistenceConfig {

}
