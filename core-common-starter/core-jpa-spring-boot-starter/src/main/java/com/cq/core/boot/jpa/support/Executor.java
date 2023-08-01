package com.cq.core.boot.jpa.support;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author cqmike 2022/1/28 9:07 下午
 */
public interface Executor<T> {

    Optional<T> execute();

    Executor<T> successHook(Consumer<T> consumer);

    Executor<T> errorHook(Consumer<? super Throwable> consumer);

}
