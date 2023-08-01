package com.cq.core.boot.jpa.support;

import java.util.function.Consumer;

/**
 * @author cqmike 2022/1/28 9:10 下午
 */
public interface UpdateHandler<T> {

    Executor<T> update(Consumer<T> consumer);

}
