package com.codegym.city.controller;

import com.codegym.city.model.City;
import com.codegym.city.service.city.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private ICityService cityService;

    @GetMapping()
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("cities", cityService.findAll());
        return modelAndView;
    }

    @GetMapping("/findAll")
    public ResponseEntity<Iterable<City>> findAll(){
        return new ResponseEntity<>(cityService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<City> create(@RequestBody City city){
        return new ResponseEntity<>(cityService.save(city), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<City> editCity(@PathVariable Long id, @RequestBody City city){
        Optional<City> optionalCity = cityService.findById(id);
        city.setId(id);
        if(optionalCity.isPresent()) return new ResponseEntity<>(cityService.save(city), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable Long id){
        cityService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Iterable<City>> searchCity(@PathVariable String name){
        return new ResponseEntity<>(cityService.findByName(name), HttpStatus.OK);
    }
}
