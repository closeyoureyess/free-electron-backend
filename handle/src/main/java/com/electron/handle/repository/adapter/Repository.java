package com.electron.handle.repository.adapter;

import java.util.List;
import java.util.Optional;

public interface Repository<S, T> {

    T save(T entity);

    List<T> saveAllAndFlush(Iterable<T> entities);

    Optional<T> findById(S id);
}
