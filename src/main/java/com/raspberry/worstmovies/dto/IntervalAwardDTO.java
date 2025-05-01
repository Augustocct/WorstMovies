package com.raspberry.worstmovies.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntervalAwardDTO {
    private List<ProducerInterval> min;
    private List<ProducerInterval> max;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProducerInterval {
        private String producer;
        private int interval;
        private int previousWin;
        private int followingWin;
    }
}
