package com.chadi.k8s.controller;

import com.chadi.k8s.model.Movie;
import com.chadi.k8s.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    InetAddress ip;
    String hostname;

    @Autowired
    private RedisRepository redisRepository;

    @GetMapping("/")
    public String getUsers() {
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            System.out.println("Your current IP address : " + ip);
            System.out.println("Your current Hostname : " + hostname);
            String result="Version:1 >> " + " Your current IP address : " + ip;
            result +=  "<br>" +" Your current Hostname : " + hostname;
            return result;

        } catch (UnknownHostException e) {

            e.printStackTrace();
        }
        return "Welcome";
    }

    @RequestMapping("/keys")
    public Map<Object, Object> keys() {
        return redisRepository.findAllMovies();
    }

    @RequestMapping("/values")
    public @ResponseBody Map<String, String> findAll() {
        Map<Object, Object> aa = redisRepository.findAllMovies();
        Map<String, String> map = new HashMap<String, String>();
        for(Map.Entry<Object, Object> entry : aa.entrySet()){
            String key = (String) entry.getKey();
            map.put(key, aa.get(key).toString());
        }
        return map;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ResponseEntity<String> add(
            @RequestParam String key,
            @RequestParam String value) {

        Movie movie = new Movie(key, value);

        redisRepository.add(movie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
