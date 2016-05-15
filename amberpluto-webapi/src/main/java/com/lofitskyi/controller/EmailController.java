package com.lofitskyi.controller;

import com.lofitskyi.entity.Message;
import com.lofitskyi.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    MessageService service;

    @RequestMapping(value = "/question", method = RequestMethod.POST)
    public void sendQuestion(@RequestBody Message message){
        service.sendToAdmin(message);
        System.out.println("success");
    }
}
