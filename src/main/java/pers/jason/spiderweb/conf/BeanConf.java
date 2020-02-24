package pers.jason.spiderweb.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BeanConf {

  @Autowired
  private DataSource dataSource;

  @Bean
  public DataSourceTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource);
  }

  @Autowired
  @Bean(name = "multiTransactionManager")
  public PlatformTransactionManager multiTransactionManager(
      Neo4jTransactionManager neo4jTransactionManager, DataSourceTransactionManager mysqlTransactionManager) {
    return new ChainedTransactionManager(neo4jTransactionManager, mysqlTransactionManager);
  }
}
