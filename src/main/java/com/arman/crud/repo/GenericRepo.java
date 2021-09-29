package com.arman.crud.repo;

import java.util.List;
import java.util.Optional;

public interface GenericRepo <T, I>{
    Optional<T> findById(I id );
    List<T> findAll();
    T save(T object);
    boolean update(T object);
    boolean deleteById(I id);
}
