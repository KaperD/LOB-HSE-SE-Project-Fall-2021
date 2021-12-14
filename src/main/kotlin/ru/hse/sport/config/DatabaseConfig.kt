package ru.hse.sport.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import javax.sql.DataSource

@Configuration
class DatabaseConfig(
    @Value("\${postgres.jdbcUrl}")
    private val jdbcUrl: String,
    @Value("\${postgres.username}")
    private val username: String,
    @Value("\${postgres.password}")
    private val password: String,
    @Value("\${postgres.initialization.fail.timeout:10000}")
    private val initializationFailTimeout: Long
) {
    @Bean
    fun dataSource(): DataSource {
        val config = HikariConfig()
        config.jdbcUrl = jdbcUrl
        config.username = username
        config.password = password
        config.initializationFailTimeout = initializationFailTimeout
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
