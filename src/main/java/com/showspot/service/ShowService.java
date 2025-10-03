package com.showspot.service;

import com.showspot.entity.Show;
import com.showspot.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    private final ShowRepository showRepository;

    @Autowired
    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public List<Show> getShowsByMovieId(Long movieId) {
        return showRepository.findAll().stream()
                .filter(show -> show.getMovie().getId().equals(movieId))
                .toList();
    }

    public Optional<Show> getShowById(Long id) {
        return showRepository.findById(id);
    }

    public Show addShow(Show show) {
        return showRepository.save(show);
    }

    public Show updateShow(Show show) {
        return showRepository.save(show);
    }

    public void deleteShow(Long id) {
        showRepository.deleteById(id);
    }
}
