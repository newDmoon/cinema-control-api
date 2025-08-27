package com.example.movie.dto;

import java.util.ArrayList;

public record MovieShortListItem(String title, ArrayList<String> genres, short durationInMinutes,
                                 short ageRestriction) {
}
