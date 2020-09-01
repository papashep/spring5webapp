package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private final BookRepository bookRepository;    // We want to bring in an instance of the templates.templates repository.
                                                    // What we are asking Spring Framework to do is inject the
                                                    // bookRepository.

    public BookController (BookRepository bookRepository) {         // This is a Spring managed component, when Spring
                                                                    // instantiates this it will inject an instance of
                                                                    // the bookRepository into our controller.
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books")                       // Mapping URL's to controller methods
    public String getBooks(Model model) {           // The model is what is returned to the view

        model.addAttribute ("books",bookRepository.findAll ());
        // At runtime when Spring gets a request to the URL "\templates" it is going to execute the getBooks() method
        // and it is going to provide that method a model, a model object. Our code is saying for that model we are
        // going to add the attribute called templates and it is going to execute the booksRepository and give us a list of
        // templates out of the database.
        // Now this model is going to be returned back to our view layer and its going to have an attribute
        // templates. We are return back a string called "templates" and that is going to tell Spring MVC we want to apply
        // the view templates and the templates component view.

        return  "books/list";                            // View name
    }
}
