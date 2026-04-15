package com.example.websocketdemo.model;

import lombok.Data;

@Data
public class GuessMessage {
    private String player;
    private int number;
}
