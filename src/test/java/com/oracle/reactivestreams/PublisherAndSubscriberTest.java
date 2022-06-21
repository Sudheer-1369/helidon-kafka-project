package com.oracle.reactivestreams;

import io.helidon.microprofile.tests.junit5.HelidonTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

import static org.assertj.core.api.Assertions.assertThat;

@HelidonTest
public class PublisherAndSubscriberTest {

    SubmissionPublisher<String> publisher;
    EndSubscriber<String> subscriber;

    @Test
    public void testSubscriber() {
        publisher = new SubmissionPublisher<>();
        subscriber = new EndSubscriber<>();

        publisher.subscribe(subscriber);
        List<String> list = List.of("1", "2", "sudheer", "Mounika", "Venkat", "Manoja");

        assertThat(publisher.getNumberOfSubscribers()).isEqualTo(1);

        list.forEach(s -> publisher.submit(s));


        assertThat(subscriber.consumedElements).containsExactlyElementsOf(list);
    }
}
