package com.arman.crud.sercive;

import java.util.List;
import java.util.Optional;

public interface GenericService <T>{
    Optional<T> findById(Integer id);
    List<T> findAll();
    boolean deleteById(Integer id);
    T save(T t);
    T update(T t);
}
