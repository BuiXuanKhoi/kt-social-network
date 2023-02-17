package com.example.socialnetworkproject.services;

import java.util.List;
import java.util.UUID;

public interface BaseService<T> {

     List<T> getAll();

     T findById(UUID id);

     void deleteById(UUID id);

     void deleteAll();
}
