package co.com.s4n.semillero.ejercicio.otherTest;

import io.vavr.collection.List;
import io.vavr.concurrent.Future;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TestFuture {

    @Test
    public void test1() {
        List<Integer> integers = List.of(1, 2, 3, 4);

        ExecutorService es = Executors.newFixedThreadPool(3);
        Future<List<Integer>> of = Future.of(es, () -> integers);
        /*of
                .onComplete(v -> v.
                        forEach(integers1 -> integers1
                                .forEach(integer ->  System.out.println(Thread.currentThread().getName()))));
        */
    }


    @Test
    public void test2() {
        List<Future<Integer>> integers = List.of(
                Future.of(() -> 1),
                Future.of(() -> 2),
                Future.of(() -> 3),
                Future.of(() -> 4));

        ExecutorService es = Executors.newFixedThreadPool(3);

        integers
                .forEach(integers1 ->
                        integers1.onComplete(integers2 ->
                                 System.out.println(Thread.currentThread().getName())));
    }
}
