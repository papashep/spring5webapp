package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
/*
    This is a spring managed component, we are implementing the run method which takes a string of arguments
    which we are not using.
    The Spring managed components has two properties, the authorRepository and the bookRepository and their final
    variables, so we are initialising those inside a constructor which tells the SpringFramework when it constructs
    this it has to inject an instance of authorRepository and bookRepository. SO when it starts up the run method bean
    is configured by the SpringFramework and then we are creating our two author and templates.templates objects using the repository
    methods to same them. Underneath the covers SpringData JPA is used to utilize and hybernate and save the objects
    in the memory h2 database.
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData (AuthorRepository authorRepository, BookRepository bookRepository,
                          PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run (String... args) throws Exception {

        System.out.println ("Started in Bootstrap");

        Publisher publisher = new Publisher ();

        publisher.setName ("SFG Publishing");
        publisher.setAddressLine1 ("St Petersburg Avenue");
        publisher.setCity ("St Petersburg");
        publisher.setState ("FL");
        publisher.setZip ("898998");

        publisherRepository.save (publisher);

        System.out.println ("Publisher: " + publisherRepository.count ());

        Author eric = new Author ("Eric","Evans");
        Book ddd = new Book ("Domain Driven Design","123123");
        eric.getBooks ().add (ddd);
        ddd.getAuthors ().add(eric);
        ddd.setPublisher (publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save (ddd);
        publisherRepository.save (publisher);

        Author rod = new Author ("Rod","Johnson");
        Book noEJB = new Book (" J2EE Development without EJB","3939459459");
        rod.getBooks ().add (noEJB);
        noEJB.getAuthors ().add(rod);
        noEJB.setPublisher (publisher);
        publisher.getBooks().add(noEJB);
        authorRepository.save(rod);
        bookRepository.save (noEJB);
        publisherRepository.save (publisher);

        System.out.println ("Number of Books: " + bookRepository.count ());
        System.out.println ("Publisher Number of Books: " + publisher.getBooks ().size ());


    }
}
