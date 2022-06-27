package com.oracle.reactivestreams;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;

@ApplicationScoped
public class ReturnPublisher {

    List<String> items;

    public SubmissionPublisher<String> getPublisher() {

        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        /**
         * This list of items wont be used by the subscriber,
         * the subscriber gets all the items that are available after subscription
         */
        items = List.of("m", "o", "u", "n", "i", "k", "a");
        items.forEach(publisher::submit);

        return publisher;
    }
}
