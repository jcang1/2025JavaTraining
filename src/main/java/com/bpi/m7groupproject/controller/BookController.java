package com.bpi.m7groupproject.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bpi.m7groupproject.dto.BookDTO;
import com.bpi.m7groupproject.dto.LoanDTO;
import com.bpi.m7groupproject.dto.ResponseDTO;
import com.bpi.m7groupproject.service.*;
import com.bpi.m7groupproject.util.JsonUtil;

public class BookController {

	private final BookServiceImplAPI bookService;

	public BookController(BookServiceImplAPI bookService) {
		this.bookService = bookService;
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

		get("/displayAllBooks", (req, res) -> {
			ResponseDTO<List<BookDTO>> response = new ResponseDTO<>();
			res.type("application/json");
			response.setStatus(ResponseStatus.SUCCESS);
			response.setData(this.bookService.listAllBooks());
			return JsonUtil.toJson(response);
		});

		get("/displayAllAvailableBooks", (req, res) -> {
			ResponseDTO<List<BookDTO>> response = new ResponseDTO<>();
			res.type("application/json");
			response.setStatus(ResponseStatus.SUCCESS);
			response.setData(this.bookService.listAllAvailableBooks());
			return JsonUtil.toJson(response);
		});

		get("/displayAllBorrowedBooks", (req, res) -> {
			ResponseDTO<List<BookDTO>> response = new ResponseDTO<>();
			res.type("application/json");
			response.setStatus(ResponseStatus.SUCCESS);
			response.setData(this.bookService.listAllBorrowedBooks());
			return JsonUtil.toJson(response);
		});

		// TODO
		// post("/borrowBook"
		post("/borrowBook", (req, res) -> {
			ResponseDTO<List<LoanDTO>> response = new ResponseDTO<>();
			if (req.body() == null || req.body().isBlank()) {
				response.setStatus(ResponseStatus.ERROR);
				response.setMessage("Payload cannot be blank!");
				return JsonUtil.toJson(response);
			}
			LoanDTO loanDTO = JsonUtil.fromJson(req.body(), LoanDTO.class);

			res.type("application/json");
			response.setStatus(ResponseStatus.SUCCESS);
			response.setData(this.bookService.borrowBook(loanDTO));
			return JsonUtil.toJson(response);
		});
		// post("/returnBook"
		post("/returnBook", (req, res) -> {
			ResponseDTO<List<LoanDTO>> response = new ResponseDTO<>();
			if (req.body() == null || req.body().isBlank()) {
				response.setStatus(ResponseStatus.ERROR);
				response.setMessage("Payload cannot be blank!");
				return JsonUtil.toJson(response);
			}
			LoanDTO loanDTO = JsonUtil.fromJson(req.body(), LoanDTO.class);

			res.type("application/json");
			response.setStatus(ResponseStatus.SUCCESS);
			response.setData(this.bookService.returnBook(loanDTO));
			return JsonUtil.toJson(response);
		});
		// put("/addBook"
		put("/addBook", (req, res) -> {
			ResponseDTO<List<BookDTO>> response = new ResponseDTO<>();
			if (req.body() == null || req.body().isBlank()) {
				response.setStatus(ResponseStatus.ERROR);
				response.setMessage("Payload cannot be blank!");
				return JsonUtil.toJson(response);
			}
			res.type("application/json");
			BookDTO bookDTO = JsonUtil.fromJson(req.body(), BookDTO.class);
			bookDTO.setId(null);
			response.setStatus(ResponseStatus.SUCCESS);
			response.setData(this.bookService.addBook(bookDTO));
			return JsonUtil.toJson(response);
		});

		// delete("/removeBook"
		delete("/removeBook", (req, res) -> {
			ResponseDTO<List<Long>> response = new ResponseDTO<>();
			if (req.body() == null || req.body().isBlank()) {
				response.setStatus(ResponseStatus.ERROR);
				response.setMessage("Payload cannot be blank!");
				return JsonUtil.toJson(response);
			}
			res.type("application/json");
			Long id = JsonUtil.fromJson(req.body(), Long.class);
			response.setStatus(ResponseStatus.SUCCESS);
			response.setData(this.bookService.removeBook(id));
			return JsonUtil.toJson(response);
		});
		// patch("/updateBook
		patch("/updateBook", (req, res) -> {
			ResponseDTO<List<BookDTO>> response = new ResponseDTO<>();
			if (req.body() == null || req.body().isBlank()) {
				response.setStatus(ResponseStatus.ERROR);
				response.setMessage("Payload cannot be blank!");
				return JsonUtil.toJson(response);
			}
			res.type("application/json");
			BookDTO bookDTO = JsonUtil.fromJson(req.body(), BookDTO.class);
			response.setStatus(ResponseStatus.SUCCESS);
			if (this.bookService.updateBook(bookDTO) == null) {
				response.setData("Update did not push. Book is loaned or non-existing.");
			} else {
				response.setData(this.bookService.updateBook(bookDTO));
			}			
			return JsonUtil.toJson(response);
		});
//		post("/movies", (req, res) -> {
//			ResponseDTO<MovieDTO> response = new ResponseDTO<>();
//			if (req.body() == null || req.body().isBlank()) {
//				response.setStatus(ResponseStatus.ERROR);
//				response.setMessage("Payload cannot be blank!");
//				return JsonUtil.toJson(response);
//			}
//			MovieDTO movieDTO = JsonUtil.fromJson(req.body(), MovieDTO.class);
//			MovieDTO savedMovieDTO = this.movieService.addMovie(movieDTO);
//			response.setStatus(ResponseStatus.SUCCESS);
//			response.setData(savedMovieDTO);
//			return JsonUtil.toJson(response);
//		});

	}
}
