package com.showspot.controller;

import com.showspot.service.MovieService;
import com.showspot.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MovieController {
    private final MovieService movieService;
    private final ShowService showService;

    @Autowired
    public MovieController(MovieService movieService, ShowService showService) {
        this.movieService = movieService;
        this.showService = showService;
    }

    @GetMapping("/movies")
    public String listMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "movies";
    }

    @GetMapping("/movies/{id}")
    public String movieDetails(@PathVariable Long id, Model model) {
        model.addAttribute("movie", movieService.getMovieById(id).orElse(null));
        model.addAttribute("shows", showService.getShowsByMovieId(id));
        return "movie-details";
    }

    @GetMapping("/movies/{id}/shows")
    public String showtimes(@PathVariable Long id, Model model) {
        model.addAttribute("shows", showService.getShowsByMovieId(id));
        return "showtimes";
    }
}
