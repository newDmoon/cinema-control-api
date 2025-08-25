package com.example.cinema.entity;

import java.util.ArrayList;
import java.util.UUID;

public class Cinema {
    private UUID id;
    private String address;
    private String city;
    private boolean isActive;

    //TODO relations
    private ArrayList<Hall> halls;
}
