package com.test.aj.completableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Lists;

import lombok.Getter;
import lombok.Setter;

public class TestCompletableFuture {

    private static final Logger logger = LogManager.getLogger(TestCompletableFuture.class);

    @BeforeClass
    public static void setup() {
    }

    @Test
    public void completeAbleFutureSimpleTestWithoutCDL() throws InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            logger.info("From thread {}", Thread.currentThread()
                    .getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Return Response";
        });

        logger.info("Starting unit test");
        future.thenAccept((msg) -> {
            Assert.assertEquals(msg, "Return Response");
            logger.info("Message from future {}", msg);
        });
        // Code will block here, until all the futures are resolved.
        logger.info("Before calling the join on CF");
        String response = future.join();
        logger.info("Before calling the join on CF, Join response {}", response);
        logger.info("Just before finishing test");
    }

    @Test
    public void completeAbleFutureSimpleTest() throws InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            logger.info("From thread 1 {}", Thread.currentThread()
                    .getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return "Return Response";
        });

        CountDownLatch cdl = new CountDownLatch(1);
        logger.info("Starting unit test");

        future.thenAccept((msg) -> {
            Assert.assertEquals(msg, "Return Response");
            logger.info("Message from future {}", msg);

            // Inform countdown latch about the task completion.
            cdl.countDown();
        });

        logger.info("Will wait for ll threads to finish");
        // Wait till all threads are done.
        cdl.await();
        logger.info("Just before finishing test");
    }

    @Test
    public void completeAbleFutureMultipleTestExmpl() throws InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                logger.info("Thread 1 working {}", Thread.currentThread()
                        .getName());
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return "ReturneD Response";
        })
                .thenApplyAsync((input) -> {
                    try {
                        Thread.sleep(500);
                        logger.info("Thread 2 working {}", Thread.currentThread()
                                .getName());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    return input.toLowerCase();
                })
                .thenApplyAsync((input) -> {
                    try {
                        Thread.sleep(500);
                        logger.info("Thread 3 working {}", Thread.currentThread()
                                .getName());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    return input.toUpperCase();
                });

        CountDownLatch cdl = new CountDownLatch(1);
        logger.info("Starting unit test");

        future.thenAccept((msg) -> {
            Assert.assertEquals(msg, "RETURNED RESPONSE");
            logger.info("Message from future {}", msg);

            // Inform countdown latch about the task completion.
            cdl.countDown();
        });

        logger.info("Will wait for ll threads to finish");
        // Wait till all threads are done.
        cdl.await();
        logger.info("Just before finishing test");
    }

    @Test
    public void testRunningParallelTask() throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(1);
        logger.info("Starting unit test");
        CompletableFuture<List<DataHolder>> cf = createCF(Lists.newArrayList("abc", "abc2"), 2000);
        CompletableFuture<List<DataHolder>> cf2 = createCF(Lists.newArrayList("xyz", "xyz2"), 5000);
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(cf, cf2);
        allFutures.exceptionally(ex -> {
            System.out.println("Exception occurred: " + ex.getMessage());
            return null;
        })
                .thenRun(() -> {
                    // All futures completed
                    List<DataHolder> result = cf.join();
                    List<DataHolder> result2 = cf2.join();

                    System.out.println("Results occurred: " + result);
                    System.out.println("Results occurred: " + result2);
                    Assert.assertEquals(2, result.size());
                    Assert.assertEquals(2, result2.size());
                    cdl.countDown();
                });
        cdl.await();
    }

    @Test
    public void testRunningParallelTaskWithJoin() throws InterruptedException {
        logger.info("Starting unit test");
        CompletableFuture<List<DataHolder>> cf = createCF(Lists.newArrayList("abc", "abc2", "abc3"), 2000);
        CompletableFuture<List<DataHolder>> cf2 = createCF(Lists.newArrayList("xyz", "xyz2"), 5000);
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(cf, cf2);

        // Triggers execution of futures.
        allFutures.exceptionally(ex -> {
            System.out.println("Exception occurred: " + ex.getMessage());
            return null;
        })
                .thenRun(() -> {
                    Assert.assertEquals(3, cf.join()
                            .size());
                    Assert.assertEquals(2, cf2.join()
                            .size());
                });

        // Blocks threads until all futures are done.

        logger.info("Before blocking the main thread");
        allFutures.join();
        logger.info("After blocking the main thread");
    }

    @Test
    public void testRunningParallelTaskWithJoinNonBlocking() throws InterruptedException {
        logger.info("Starting unit test");
        CompletableFuture<List<DataHolder>> cf = createCF(Lists.newArrayList("abc", "abc2", "abc3"), 2000);
        CompletableFuture<List<DataHolder>> cf2 = createCF(Lists.newArrayList("xyz", "xyz2"), 5000);
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(cf, cf2);

        // Triggers execution of futures.
        allFutures.exceptionally(ex -> {
            System.out.println("Exception occurred: " + ex.getMessage());
            return null;
        })
                .thenRun(() -> {
                    logger.info("Here 1");
                    Assert.assertEquals(3, cf.join()
                            .size());
                    logger.info("Here 2");
                    Assert.assertEquals(2, cf2.join()
                            .size());
                    logger.info("Here 3");
                });
        // Blocks threads until all futures are done.
        logger.info("Before blocking the main thread");
        allFutures.join();
        logger.info("After blocking the main thread");
    }

    private List<DataHolder> getData(List<String> ids, long millis) throws InterruptedException {
        Thread.sleep(millis);
        logger.info("Thread working {}, time taken will be {} ms.", Thread.currentThread()
                .getName(), millis);

        return ids.stream()
                .map(DataHolder::new)
                .collect(Collectors.toList());
    }

    private static class DataHolder {
        @Getter
        @Setter
        private String id;

        public DataHolder(String id) {
            this.id = id;
        }

        public DataHolder() {
        }

        @Override
        public String toString() {
            return id;
        }
    }

    private CompletableFuture<List<DataHolder>> createCF(List<String> ids, long millis) {
        try {
            return CompletableFuture.supplyAsync(callExternalApiAndProduceData(ids, millis));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Supplier<List<DataHolder>> callExternalApiAndProduceData(List<String> ids, long millis)
            throws InterruptedException {
        return () -> {
            try {
                return getData(ids, millis);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

}
