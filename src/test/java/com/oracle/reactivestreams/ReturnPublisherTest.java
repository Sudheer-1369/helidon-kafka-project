package com.oracle.reactivestreams;

import io.helidon.microprofile.tests.junit5.HelidonTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;

@HelidonTest
public class ReturnPublisherTest {

    @Inject
    private ReturnPublisher returnPublisher;

    List<String> items;

    @Test
    public void testReturnPublisher(){

        EndSubscriber<String> subscriber = new EndSubscriber<>();

        SubmissionPublisher<String> publisher = returnPublisher.getPublisher();

        publisher.subscribe(subscriber);

        items = List.of("s","u","d","h","e","e","r");

        items.forEach(publisher::submit);

        }
    }
