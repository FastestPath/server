package co.fastestpath.api.persistence.mongo;

import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

//@Configuration
public class MongoConfig {

//    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
//        return new SimpleMongoDbFactory(new MongoClient(), "fastestpath");
      return null;
    }

//    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
//        return new MongoTemplate(mongoDbFactory());
      return null;
    }
}
