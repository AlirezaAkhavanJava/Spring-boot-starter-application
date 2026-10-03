package com.arcade.alibou;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users")
public class DummyController {

    @GetMapping(value = "/test1")
    @ResponseStatus
    public String runningTest() {
        return "SUCCUSS";
    }

    @GetMapping(value = "/test2")
    @ResponseStatus(HttpStatus.OK)
    public String runningTest2() {
        return "SUCCUSS TEST 2";
    }
}
