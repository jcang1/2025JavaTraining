/**
 * Used to convert DAO to DTO and vice versa
 */
package com.example.demo.mapper;

/**
 * 
 */
import com.example.demo.DAO.Book;
import com.example.demo.DAO.User;
import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserBookMapper {

    /* =========================
       BOOK MAPPINGS
       ========================= */

    @Mapping(target = "userId", source = "user.userId")
    BookDTO toBookDTO(Book book);

    @Mapping(target = "user", ignore = true) // set in service layer
    Book toBook(BookDTO bookDTO);

    // ? List<Book> ? List<BookDTO>
    List<BookDTO> toBookDTOList(List<Book> books);

    // ? List<BookDTO> ? List<Book>
    List<Book> toBookList(List<BookDTO> bookDTOs);

    /* =========================
       USER MAPPINGS
       ========================= */

    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);

    // ? List<User> ? List<UserDTO>
    List<UserDTO> toUserDTOList(List<User> users);

    // ? List<UserDTO> ? List<User>
    List<User> toUserList(List<UserDTO> userDTOs);
}
