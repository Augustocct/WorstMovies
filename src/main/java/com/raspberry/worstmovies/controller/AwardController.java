package com.raspberry.worstmovies.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raspberry.worstmovies.dto.IntervalAwardDTO;
import com.raspberry.worstmovies.service.MovieService;

@RestController
@RequestMapping("/api/awards")
public class AwardController {
    private final MovieService movieService;

    public AwardController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/intervals")
    public ResponseEntity<IntervalAwardDTO> getAwardIntervals() {
        IntervalAwardDTO intervalAwardDTO = movieService.calculateAwardIntervals();
        return ResponseEntity.ok(intervalAwardDTO);
    }
}
