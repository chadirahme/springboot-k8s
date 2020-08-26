package com.chadi.k8s.repository;

import com.chadi.k8s.model.Movie;

import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

public interface RedisRepository {

    default void newMethod(){
        System.out.println("Newly added default method");
    }

    default void newMethod1(){
        System.out.println("Newly added default method1");
        IntStream.range(1, 2).reduce((x, y) -> x + y)
                .ifPresent(s -> System.out.println(s));

       int res= IntStream.range(1, 2).reduce(0, (x, y) -> x + y);

       System.out.println("res>> " + res);


    }

    /**
     * Return all movies
     */
    Map<Object, Object> findAllMovies();

    /**
     * Add key-value pair to Redis.
     */
    void add(Movie movie);

    /**
     * Delete a key-value pair in Redis.
     */
    void delete(String id);

    /**
     * find a movie
     */
    Movie findMovie(String id);

}