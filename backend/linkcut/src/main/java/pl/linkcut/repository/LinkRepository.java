package pl.linkcut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.linkcut.entity.Link;

import java.time.LocalDate;

public interface LinkRepository extends JpaRepository<Link, Long> {

    Link findByShortenedLink(String shortenedLink);

    void deleteAllByExpirationDateBefore(LocalDate date);

}
