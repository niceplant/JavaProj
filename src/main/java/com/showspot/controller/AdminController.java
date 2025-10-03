package com.showspot.controller;

import com.showspot.entity.Movie;
import com.showspot.entity.Show;
import com.showspot.service.BookingService;
import com.showspot.service.MovieService;
import com.showspot.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final MovieService movieService;
    private final ShowService showService;
    private final BookingService bookingService;

    @Autowired
    public AdminController(MovieService movieService, ShowService showService, BookingService bookingService) {
        this.movieService = movieService;
        this.showService = showService;
        this.bookingService = bookingService;
    }

    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("shows", showService.getShowsByMovieId(null));
        return "admin-dashboard";
    }

    @GetMapping("/movies/add")
    public String addMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "admin-movie-form";
    }

    @PostMapping("/movies/add")
    public String addMovie(@ModelAttribute Movie movie) {
        movieService.addMovie(movie);
        return "redirect:/admin";
    }

    @GetMapping("/movies/{id}/edit")
    public String editMovieForm(@PathVariable Long id, Model model) {
        model.addAttribute("movie", movieService.getMovieById(id).orElse(null));
        return "admin-movie-form";
    }

    @PostMapping("/movies/{id}/edit")
    public String editMovie(@PathVariable Long id, @ModelAttribute Movie movie) {
        movie.setId(id);
        movieService.updateMovie(movie);
        return "redirect:/admin";
    }

    @PostMapping("/movies/{id}/delete")
    public String deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return "redirect:/admin";
    }

    @GetMapping("/shows/add")
    public String addShowForm(Model model) {
        model.addAttribute("show", new Show());
        model.addAttribute("movies", movieService.getAllMovies());
        return "admin-show-form";
    }

    @PostMapping("/shows/add")
    public String addShow(@ModelAttribute Show show) {
        showService.addShow(show);
        return "redirect:/admin";
    }

    @GetMapping("/shows/{id}/edit")
    public String editShowForm(@PathVariable Long id, Model model) {
        model.addAttribute("show", showService.getShowById(id).orElse(null));
        model.addAttribute("movies", movieService.getAllMovies());
        return "admin-show-form";
    }

    @PostMapping("/shows/{id}/edit")
    public String editShow(@PathVariable Long id, @ModelAttribute Show show) {
        show.setId(id);
        showService.updateShow(show);
        return "redirect:/admin";
    }

    @PostMapping("/shows/{id}/delete")
    public String deleteShow(@PathVariable Long id) {
        showService.deleteShow(id);
        return "redirect:/admin";
    }

    @GetMapping("/bookings")
    public String viewBookings(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "admin-bookings";
    }
}
