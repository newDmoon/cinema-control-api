package com.example.cinema.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Screening {
    private UUID id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String type;
    private BigDecimal basePrice;
    private ScreeningStatus status;

    //TODO relations
    private UUID movieId;
    private Hall hall;
}
