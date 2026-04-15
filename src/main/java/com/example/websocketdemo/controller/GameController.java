package com.example.websocketdemo.controller;

import com.example.websocketdemo.model.GuessMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {
    private final int secret = 7;

    @MessageMapping("/guess")
    @SendTo("/topic/game")
    public String handleGuess(GuessMessage msg){
        if (msg.getNumber() == secret){
            return msg.getPlayer() + " guessed CORRECT!";
        }

        return msg.getPlayer() + " guessed " + msg.getNumber();
    }
}
