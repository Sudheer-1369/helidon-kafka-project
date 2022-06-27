package com.oracle.quarkus.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class ComplexCompletableFuture {
    public static void main(String[] args) {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> "Sudheer")
                .thenApply(s -> s + " Mounika")
                .thenApply(s -> s + " Venkat")
                .thenApply(s -> s + " Manoja")
                .thenAccept(System.out::println);

        CompletionStage<String> completionStage = getTheStrings();
        System.out.println(completionStage.thenAccept(s -> System.out.println(s)));

    }

    public static CompletionStage<String> getTheStrings(){
        return CompletableFuture.supplyAsync(()->"Sudheer").thenApply(s->s+" Mounika");
    }
}
