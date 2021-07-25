package com.libtest.library.service.crud;

public interface EntityCrudService<T> {

    void create(T entity);

    T update(Long id, T toUpdate);

    T findById(Long id);

    void deleteById(Long id);

}
