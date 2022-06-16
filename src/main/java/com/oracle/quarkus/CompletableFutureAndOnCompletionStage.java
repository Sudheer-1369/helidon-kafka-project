package com.oracle.quarkus;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureAndOnCompletionStage {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * simple completable future and it later uses thenApply method which then returns a CompletableFuture instance after
         * applying the specified Function instance
         */
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> getString());
        completableFuture = completableFuture.thenApply(s -> s + " Mounika");

        /**
         * After the thenApply method now we use the thenAccept method which just accepts the instance from the
         * previous method and the perform any operation using the instance without returning anything
         */
        CompletableFuture<Void> completableFuture1 = completableFuture.thenAccept(s -> System.out.println(s + " Baby Bubu"));
        System.out.println(completableFuture1.get()); // The output here is null


        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "RajiniKanth is a demi god");
        CompletableFuture<Void> completableFuture3 = completableFuture2.thenRun(() -> System.out.println("RajiniKanth and Kamal both are legends"));
        System.out.println(completableFuture3.get());

        /**
         * thenCompose method is like flatMap method that uses the previous stage as the argument
         */

        CompletableFuture<CompletableFuture<String>> future = CompletableFuture.supplyAsync(() -> "Sudheer").thenApply(s -> CompletableFuture.supplyAsync(() -> s + " Mounika"));
        System.out.println("The Future is " + future.get().get());

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Sudheer").thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " Mounika"));
        System.out.println("The Future1 is " + future1.get());
        /**
         * Finally we understood that when we use thenApply for combining the result of one CompletableFuture with another CompletableFuture
         * we are getting CompletableFuture<CompletableFuture<?>> but when we use thenCompose method on the same scenario we are getting
         * a simple CompletableFuture<String>
         */

        /**
         * Running multiple instances in parallel
         */

        CompletableFuture<String> nearFuture1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> nearFuture2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> nearFuture3
                = CompletableFuture.supplyAsync(() -> "World");
        CompletableFuture<String> nearFuture4
                = CompletableFuture.supplyAsync(() -> "How are u ??");


        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(nearFuture1, nearFuture2, nearFuture3, nearFuture4);
        System.out.println(combinedFuture.get());
        System.out.println(nearFuture1.get());
        System.out.println(nearFuture2.get());
        System.out.println(nearFuture3.get());
        System.out.println(nearFuture4.get());

        var combined = Stream.of(nearFuture1, nearFuture2, nearFuture3)
                .map(CompletableFuture::join).collect(Collectors.joining(" "));
        System.out.println(combined);

    }

    static String getString() {
        return "Sudheer";
    }
}
