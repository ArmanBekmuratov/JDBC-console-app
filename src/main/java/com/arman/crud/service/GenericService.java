package com.arman.crud.service;

import java.util.List;

public interface GenericService <T, ID>{
    List<T> findAll();

    T findById(ID id);

    T save(T t);

    boolean update(T t);

    boolean deleteById(ID id);
}
