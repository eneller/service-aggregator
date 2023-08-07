package com.neller.service_aggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Controller
public class MainApplication {
    private List<String> dataList = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @GetMapping("/")
    public String root(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("dataList", dataList);
        return "aggregator";
    }

    @PostMapping("/")
    /*we need to return something so our client doesn't receive an error
    test using curl -X POST "localhost:8081?data=abc"
     */

    public ResponseEntity<String> input(@RequestParam(name = "data", required = true) String data) {
        dataList.add(data);
        return new ResponseEntity<String>(data, HttpStatus.CREATED);
    }


}
