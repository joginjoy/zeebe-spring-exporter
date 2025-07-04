package ch.mednota.exporter.workers;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/*
This class contains all the workers representing the service-tasks in workflow. Alternatively, each service-task can
have a class of its own too, resulting in one JobWorker per class.
 */
@Component
public class ZeebeWorker {

    private static final Logger LOG = LoggerFactory.getLogger(ZeebeWorker.class);

    @JobWorker(type = "task1-to-topic1")
    public void ingestToTopic1(final ActivatedJob job) {
        LOG.info("In ingestToTopic1");

        // Business Logic goes here

        // An example is sending data to kafka
        log(job);
        // Logic to send to Kafka
        sendToKafka(job);
    }

    @JobWorker(type = "task2-to-topic2")
    public void ingestToTopic2(final ActivatedJob job) {
        LOG.info("In ingestToTopic2");
        log(job);
        sendToKafka(job);
    }

    private void log(final ActivatedJob job) {
        // These are variables or data coming from the service tasks. Can also be mapped to POJO
        // Or we can also extract just what we want using @Variables or fetchVariables, if data is too big
        Map<String, Object> variables = job.getVariablesAsMap();
        LOG.info("Received variables: {} for jobId {}", variables, job.getKey());
    }

    private void sendToKafka(ActivatedJob job) {
        LOG.info("TODO: Kafka Producer can now send data to kafka from here...");
    }

}
