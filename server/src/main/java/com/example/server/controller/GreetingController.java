package com.example.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController // 컨트롤러 클래스
@RequestMapping("/greeting")
public class GreetingController {
    @GetMapping
    public Greeting hello(@RequestParam(name = "name") String _name){
        Greeting tmp = new Greeting();
        System.out.println(_name);
        if(_name.equals("je"))
        {
            tmp.setId(1);
            tmp.setContent("257");
            return tmp;

        }
        else if(_name.equals("su"))
        {
            tmp.setId(2);
            tmp.setContent("258");
            return tmp;
        }
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST
        );
    }
}
/*

D


_____
___X_
_____
 */
