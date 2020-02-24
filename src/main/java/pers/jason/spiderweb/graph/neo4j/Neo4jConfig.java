package pers.jason.spiderweb.graph.neo4j;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;

@Configuration
@EntityScan(basePackages = "pers.jason.spiderweb.graph.neo4j.pojo")
@EnableNeo4jRepositories("pers.jason.spiderweb.graph.neo4j.repositories")
public class Neo4jConfig {

  @Bean
  public Neo4jTransactionManager neo4jTransactionManager(SessionFactory sessionFactory) {
    return new Neo4jTransactionManager(sessionFactory);
  }
}
