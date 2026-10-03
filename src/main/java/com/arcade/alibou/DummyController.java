package com.arcade.alibou;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users")
public class DummyController {

    public String runningTest(){
        return "SUCCUSS";
    }
}
