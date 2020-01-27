package com.redhat.developer.api;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.redhat.developer.IKafkaConsumerService;

@Path("/monitor")
public class Monitor {
    @Inject
    IKafkaConsumerService kafkaConsumer;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String monitor() {
        return kafkaConsumer.getEventCount();
    }
}