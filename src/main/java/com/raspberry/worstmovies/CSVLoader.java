package com.raspberry.worstmovies;

import com.raspberry.worstmovies.model.Movie;
import com.raspberry.worstmovies.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class CSVLoader {

    @Bean
    CommandLineRunner loadData(MovieRepository movieRepository) {
        return args -> {
            try (
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getClass().getResourceAsStream("/movielist.csv"), StandardCharsets.UTF_8))
            ) {
                String line;
                boolean isFirst = true;
                while ((line = reader.readLine()) != null) {
                    if (isFirst) { // pular o cabeçalho
                        isFirst = false;
                        continue;
                    }

                    String[] fields = line.split(";");
                    Movie movie = new Movie();
                    movie.setYear(Integer.parseInt(fields[0].trim()));
                    movie.setTitle(fields[1].trim());
                    movie.setStudios(fields[2].trim());
                    movie.setProducers(fields[3].trim());
                    movie.setWinner(fields.length > 4 && "yes".equalsIgnoreCase(fields[4].trim()));

                    movieRepository.save(movie);
                }

                System.out.println("Filmes carregados do CSV com sucesso!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
