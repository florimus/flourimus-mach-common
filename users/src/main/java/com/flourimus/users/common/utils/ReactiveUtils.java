package com.flourimus.users.common.utils;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Callable;

public class ReactiveUtils {

    public static <T> Mono<T> executeOnBoundedElastic(Callable<T> callable) {
        return Mono.fromCallable(callable)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
