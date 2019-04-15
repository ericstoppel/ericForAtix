package com.eric.atix.Controllers;


import com.eric.atix.Model.MonitoringSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MonitorController {

    @Autowired
    MonitoringSystem ms;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ResponseEntity sendMessage(@RequestParam int value) {
        if (value < 0) return ResponseEntity.badRequest().build();
        ms.reciveMessage(value);
        return ResponseEntity.ok().body("Message processed");
    }
}
