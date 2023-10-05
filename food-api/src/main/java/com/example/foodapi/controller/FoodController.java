package com.example.foodapi.controller;

import com.example.foodapi.data.FoodRepository;
import com.example.foodapi.dto.FoodRequest;
import com.example.foodapi.dto.FoodResponse;
import com.example.foodapi.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    private FoodRepository repository;

    @GetMapping
    public List<FoodResponse> getAll() {
        return repository.findAll().stream()
                .map((Food) -> new FoodResponse(Food.getName(), Food.getId())).toList();
    }

    @PostMapping
    public ResponseEntity<Object> saveFood(@RequestBody FoodRequest foodRequest){
        Food food = new Food(foodRequest.name());
        repository.save(food);
        return ResponseEntity.status(201).build();
    }
}

