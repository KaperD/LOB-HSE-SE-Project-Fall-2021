package ru.hse.sport.football.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.testcontainers.containers.PostgreSQLContainer
import javax.sql.DataSource

class MyPostgreSQLContainer(imageName: String) : PostgreSQLContainer<MyPostgreSQLContainer>(imageName)

@Configuration
class DatabaseConfig {
    companion object {
        val postgreSQLContainer = MyPostgreSQLContainer("postgres:13.4")
            .withDatabaseName("postgres")
            .withUsername("postgres")
            .withPassword("postgres")
            .apply { start() }
    }

    @Bean
    fun dataSource(): DataSource {
        val config = HikariConfig()
        config.jdbcUrl = postgreSQLContainer.jdbcUrl
        config.username = postgreSQLContainer.username
        config.password = postgreSQLContainer.password
        config.addDataSourceProperty("cachePrepStmts", "true")
        config.addDataSourceProperty("prepStmtCacheSize", "250")
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")

        return HikariDataSource(config)
    }

    @Bean
    fun namedParameterJdbcTemplate(dataSource: DataSource): NamedParameterJdbcTemplate {
        return NamedParameterJdbcTemplate(dataSource)
    }
}
