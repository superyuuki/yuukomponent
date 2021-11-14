package com.superyuuki.yuukomponent.sql;

import com.superyuuki.yuukomponent.api.addon.Addon;
import com.superyuuki.yuukomponent.api.addon.AddonContext;
import com.superyuuki.yuukomponent.api.addon.AddonDescription;
import com.superyuuki.yuukomponent.api.StartupFailure;
import com.superyuuki.yuukomponent.api.config.ConfigurationLoaderFeature;
import com.superyuuki.yuukomponent.api.transactional.TransactionSourceFeature;
import com.superyuuki.yuukomponent.dazzleconf.loader.Extractor;
import com.superyuuki.yuukomponent.sql.transaction.SQLSourceFeature;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import space.arim.jdbcaesar.JdbCaesarBuilder;


@AddonDescription(
        displayName = "yuukomponent-core-sql",
        version = "0.0.1-SNAPSHOT",
        description = "SQL binding for YuuKomponent",
        author = "Yuuki",
        exports = TransactionSourceFeature.class,
        depends = ConfigurationLoaderFeature.class
)
public class SQLAddonContext implements Addon {

    private volatile HikariDataSource source;

    @Override
    public void onStartup(AddonContext context) throws StartupFailure {
        Config config = new Extractor<>(Config.class, context.dataFolder(), "config.yml").extract();

        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(config.jdbcUrl());
        hikariConfig.setUsername(config.username());
        hikariConfig.setPassword(config.password());
        hikariConfig.addDataSourceProperty( "cachePrepStmts" , "true" );
        hikariConfig.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        hikariConfig.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );

        source = new HikariDataSource(hikariConfig);

        context.manager().register(
                TransactionSourceFeature.class,
                new SQLSourceFeature(
                        context.factory(),
                        new JdbCaesarBuilder().dataSource(source).build()
                )
        );

    }

    @Override
    public void onShutdown() {
        source.close();
    }
}
