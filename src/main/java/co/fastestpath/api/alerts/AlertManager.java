package co.fastestpath.api.alerts;

import co.fastestpath.api.persistence.MongoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Singleton
class AlertManager {

  private static final Logger LOG = LoggerFactory.getLogger(AlertManager.class);
  private static final Instant THIRTY_DAYS_OLD = Instant.now().minus(30, ChronoUnit.DAYS);
  // springdata mongodb
  ApplicationContext ctx;
  MongoOperations mongoOperation;

  @Inject
  public AlertManager() {
    try {
      ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
      mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
    } catch(BeansException e) {
      LOG.error("Unable to create bean " + e.getMessage());
    } catch(Exception e) {
      LOG.error("Unable to instantiate AlertManager " + e.getMessage());
    }
  }

  public List<Alert> getAlerts() {
    return mongoOperation.find(new Query(), Alert.class);
  }

  public void addAlert(Alert alert) {
    mongoOperation.save(alert);
    // remove old alerts

    Query searchQuery = new Query(Criteria.where("createdOn").lt(THIRTY_DAYS_OLD));
    LOG.debug("Removing alerts older than 30 days");
    mongoOperation.remove(searchQuery, Alert.class);
  }
}
