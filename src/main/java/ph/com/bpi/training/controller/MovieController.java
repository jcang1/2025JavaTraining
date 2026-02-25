package ph.com.bpi.training.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ph.com.bpi.training.dto.MovieDTO;
import ph.com.bpi.training.dto.ResponseDTO;
import ph.com.bpi.training.model.Movie;
import ph.com.bpi.training.service.MovieService;
import ph.com.bpi.training.util.JsonUtil;

public class MovieController {
	private final MovieService movieService;

	// initialize dependencies
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	public void registerRoutes() {
		get("/check-connection", (req, res) -> {
			res.type("application/json");// define the MIME type of the response
			// we're telling the client that the response
			// is of JSON format
			// return a map (A map is basically a JSON formatted object)
			Map<String, String> response = new HashMap<>();
			response.put("status", "Server is running");
			return JsonUtil.toJson(response);
		});

		get("/movies", (req, res) -> {
			ResponseDTO<List<MovieDTO>> response = new ResponseDTO<>();
			res.type("application/json");
			response.setStatus(ResponseStatus.SUCCESS);
			response.setData(this.movieService.listMovies());
			return JsonUtil.toJson(response);
		});

		post("/movies", (req, res) -> {
			ResponseDTO<MovieDTO> response = new ResponseDTO<>();
			if (req.body() == null || req.body().isBlank()) {
				response.setStatus(ResponseStatus.ERROR);
				response.setMessage("Payload cannot be blank!");
				return JsonUtil.toJson(response);
			}
			MovieDTO movieDTO = JsonUtil.fromJson(req.body(), MovieDTO.class);
			MovieDTO savedMovieDTO = this.movieService.addMovie(movieDTO);
			response.setStatus(ResponseStatus.SUCCESS);
			response.setData(savedMovieDTO);
			return JsonUtil.toJson(response);
		});

	}
}
