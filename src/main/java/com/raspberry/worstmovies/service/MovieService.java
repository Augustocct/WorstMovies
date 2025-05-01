package com.raspberry.worstmovies.service;

import com.raspberry.worstmovies.dto.IntervalAwardDTO;
import com.raspberry.worstmovies.model.Movie;
import com.raspberry.worstmovies.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public IntervalAwardDTO calculateAwardIntervals() {
        // Obtém todos os filmes vencedores
        List<Movie> winners = movieRepository.findByWinnerTrue();

        // Agrupa os filmes vencedores por produtor
        Map<String, List<Integer>> producerWins = new HashMap<>();

        for (Movie movie : winners) {
            String[] producers = movie.getProducers().split(",| and ");
            for (String producer : producers) {
                producer = producer.trim();
                producerWins.computeIfAbsent(producer, k -> new ArrayList<>()).add(movie.getYear());
            }
        }

        List<IntervalAwardDTO.ProducerInterval> intervals = new ArrayList<>();

        // Para cada produtor, calcula os intervalos entre vitórias
        for (Map.Entry<String, List<Integer>> entry : producerWins.entrySet()) {
            List<Integer> years = entry.getValue();
            Collections.sort(years);
            for (int i = 1; i < years.size(); i++) {
                int interval = years.get(i) - years.get(i - 1);
                intervals.add(new IntervalAwardDTO.ProducerInterval(
                        entry.getKey(),
                        interval,
                        years.get(i - 1),
                        years.get(i)
                ));
            }
        }

        // Separa os intervalos min e max
        OptionalInt minInterval = intervals.stream().mapToInt(IntervalAwardDTO.ProducerInterval::getInterval).min();
        OptionalInt maxInterval = intervals.stream().mapToInt(IntervalAwardDTO.ProducerInterval::getInterval).max();

        List<IntervalAwardDTO.ProducerInterval> minList = intervals.stream()
                .filter(pi -> pi.getInterval() == minInterval.orElse(0))
                .collect(Collectors.toList());

        List<IntervalAwardDTO.ProducerInterval> maxList = intervals.stream()
                .filter(pi -> pi.getInterval() == maxInterval.orElse(0))
                .collect(Collectors.toList());

        return new IntervalAwardDTO(minList, maxList);
    }
}
