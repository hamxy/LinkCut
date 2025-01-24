package pl.linkcut.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import pl.linkcut.entity.Link;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2) // Use H2 as the embedded database for testing
@Transactional // Ensure each test runs in a transactional context and rolls back after completion
public class LinkRepositoryTest {

    @Autowired
    LinkRepository linkRepository; // Autowire the repository to test its methods

    @Test
    public void findByShortenedLink_returnsOriginalUrl() {
        // Arrange: Create a Link entity and save it in the database
        Link link = new Link(
                "https://www.example.com", // Original URL
                "abc123",                 // Shortened link
                LocalDate.now().plusDays(30) // Expiration date: 30 days from now
        );
        linkRepository.save(link); // Save the link to the database

        // Act: Retrieve the Link entity using the shortened link
        Link savedLink = linkRepository.findByShortenedLink(link.getShortenedLink());

        // Assert: Verify that the retrieved Link entity matches the saved one
        assertThat(savedLink).isNotNull(); // Ensure the retrieved entity is not null
        assertThat(savedLink.getId()).isGreaterThan(0); // Verify that the ID was generated
        assertThat(savedLink.getOriginalUrl()).isEqualTo(link.getOriginalUrl()); // Confirm the original URL matches
        assertThat(savedLink.getExpirationDate()).isEqualTo(link.getExpirationDate()); // Confirm the expiration date matches
    }

    @Test
    void deleteAllByExpirationDateBefore_removesExpiredLinks() {
        // Arrange: Add multiple Link entities with different expiration dates
        Link link1 = new Link("https://example1.com", "short1", LocalDate.now().minusDays(1)); // Expired yesterday
        Link link2 = new Link("https://example2.com", "short2", LocalDate.now());             // Expires today
        Link link3 = new Link("https://example3.com", "short3", LocalDate.now().plusDays(1)); // Expires tomorrow
        linkRepository.saveAll(List.of(link1, link2, link3)); // Save all links to the database

        // Act: Delete all links with an expiration date before today
        linkRepository.deleteAllByExpirationDateBefore(LocalDate.now());

        // Assert: Verify that only links not expired are left
        List<Link> remainingLinks = linkRepository.findAll(); // Retrieve remaining links from the database
        assertThat(remainingLinks).hasSize(2); // Ensure only 2 links remain in the database
        assertThat(remainingLinks).extracting(Link::getOriginalUrl) // Extract the original URLs of the remaining links
                .containsExactlyInAnyOrder("https://example2.com", "https://example3.com"); // Verify the remaining URLs
    }
}