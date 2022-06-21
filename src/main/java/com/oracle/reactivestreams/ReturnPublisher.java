package com.oracle.reactivestreams;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;

@ApplicationScoped
public class ReturnPublisher {

     List<String> items;

    public SubmissionPublisher<String> getPublisher(){

        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

//        items  = List.of("s","u","d","h","e","e","r");
//
//        items.forEach(publisher::submit);

        return publisher;
    }
}
