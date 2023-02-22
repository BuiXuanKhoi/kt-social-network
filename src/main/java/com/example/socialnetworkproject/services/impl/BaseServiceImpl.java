package com.example.socialnetworkproject.services.impl;

import com.example.socialnetworkproject.repositories.BaseRepository;
import com.example.socialnetworkproject.services.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BaseServiceImpl<T> implements BaseService<T> {

    private final BaseRepository<T> baseRepository;

    public BaseServiceImpl(BaseRepository<T> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public List<T> getAll() {
        List<T> records = baseRepository.findAll();
        if (records.isEmpty()) throw new NullPointerException("Null");
        else return records;
    }

    @Override
    public T findById(UUID id) {
        return baseRepository.findById(id)
                .orElseThrow(
                        () -> new NullPointerException("Null")
                );
    }

    @Override
    public void deleteById(UUID id) {
        T record = findById(id);
        baseRepository.delete(record);
    }

    @Override
    public void deleteAll() {
        baseRepository.deleteAll();
    }
}
