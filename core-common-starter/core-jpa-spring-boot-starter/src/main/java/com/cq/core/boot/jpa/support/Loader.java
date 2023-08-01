package com.cq.core.boot.jpa.support;

import java.util.function.Supplier;

/**
 * @author cqmike 2022/1/28 9:07 下午
 */
public interface Loader<T, ID> {

    UpdateHandler<T> loadById(ID id);

    UpdateHandler<T> load(Supplier<T> t);

}
