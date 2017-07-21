package config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import javax.annotation.Resource;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {


    @Override
    protected String getDatabaseName() {
        return "vetclinic";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient();
    }

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), getDefaultMongoConverter());
    }

    @Bean

    public MappingMongoConverter getDefaultMongoConverter() throws Exception {

        MappingMongoConverter converter = new MappingMongoConverter(
                new DefaultDbRefResolver(mongoDbFactory()), new MongoMappingContext()
        );

        return converter;

    }
}
