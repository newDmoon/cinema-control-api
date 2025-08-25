package com.example.cinema.entity;

import java.util.ArrayList;
import java.util.UUID;

public class Hall {
    private UUID id;
    private String name;

    //TODO relations
    private Cinema cinema;
    private ArrayList<Screening> screenings;
}
