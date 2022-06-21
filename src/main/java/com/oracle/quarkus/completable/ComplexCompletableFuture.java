package com.oracle.quarkus.completable;

import java.util.concurrent.CompletableFuture;

public class ComplexCompletableFuture {
    public static void main(String[] args) {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> "Sudheer")
                .thenApply(s -> s + " Mounika")
                .thenApply(s -> s + " Venkat")
                .thenApply(s -> s + " Manoja")
                .thenAccept(System.out::println);

    }

}
