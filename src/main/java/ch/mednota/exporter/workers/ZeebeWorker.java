package ch.mednota.exporter.workers;

import ch.mednota.exporter.kafka.service.KafkaProducer;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/*
This class contains all the workers representing the service-tasks in workflow. Alternatively, each service-task can
have a class of its own too, resulting in one JobWorker per class.

Another, entirely different, approach is to have a single worker that is used by all service-tasks. But in this case,
if data from each service-task in zeebe is intended for different topic, then the name of the topic "HAS" to be sent
from the service-task as a variable to the worker so that it can be extracted here and used to send data to the correct topic

ActivatedJob contains variables/data coming from the service task.
This can also be mapped to POJO Or can even be extracted using @Variables or fetchVariables attribute of the annotation, if data is too big
 */
@Component
public class ZeebeWorker {

    private static final Logger LOG = LoggerFactory.getLogger(ZeebeWorker.class);

    private KafkaProducer kafkaProducer;
    private String topic1;
    private String topic2;

    @Autowired
    public ZeebeWorker(KafkaProducer kafkaProducer,
                       @Value("${kafka.topic1}") String topic1,
                       @Value("${kafka.topic2}") String topic2) {
        this.kafkaProducer = kafkaProducer;
        this.topic1 = topic1;
        this.topic2 = topic2;
    }

    @JobWorker(type = "task1-to-topic1")
    public void ingestToTopic1(final ActivatedJob job) {
        LOG.info("In ingestToTopic1 with variables {} from service-task1", job.getVariablesAsMap());

        // Business Logic goes here...

        // An example is sending data to kafka
        sendToKafka(topic1, 1L, job.getVariablesAsMap());
    }

    @JobWorker(type = "task2-to-topic2")
    public void ingestToTopic2(final ActivatedJob job) {
        LOG.info("In ingestToTopic2 with variables {} from service-task2", job.getVariablesAsMap());

        sendToKafka(topic2, 2L, job.getVariablesAsMap());
    }

    private void sendToKafka(String topic, Long key, Object message) {
        kafkaProducer.send(topic, key, message);
    }

}
