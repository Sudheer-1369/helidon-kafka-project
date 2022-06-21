package com.oracle.reactivestreams;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow;

public class EndSubscriber<T> implements Flow.Subscriber<T> {

    Flow.Subscription subscription;
    List<T> consumedElements = new LinkedList<>();

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("The subscription started");
        this.subscription = subscription;
        subscription.request(1);
        System.out.println("Moving on to OnNext");
    }

    @Override
    public void onNext(T item) {
        System.out.println("The item being processed is "+item);
        consumedElements.add(item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {

        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {

        System.out.println("Done");
    }
}
