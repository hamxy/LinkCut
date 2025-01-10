package pl.linkcut.service;

import org.springframework.stereotype.Service;
import pl.linkcut.entity.Link;
import pl.linkcut.helper.UrlValidator;
import pl.linkcut.repository.LinkRepository;
import java.time.LocalDate;
import java.util.Random;

@Service
public class LinkService {

    private final LinkRepository linkRepository;
    private final UrlValidator urlValidator;

    public LinkService(LinkRepository linkRepository, UrlValidator urlValidator) {
        this.linkRepository = linkRepository;
        this.urlValidator = urlValidator;
    }

    /**
     * ///
     *
     * @param originalUrl full URL to be shortened
     * @return shortened link
     */
    public String shortenUrl(String originalUrl) {
        // Validate URL
        urlValidator.validateUrl(originalUrl);

        // Generate shortened link
        String shortenedLink = generateShortenedLink();

        // Create new Link
        Link link = new Link(originalUrl, shortenedLink, LocalDate.now().plusDays(30));

        // Save link in database
        linkRepository.save(link);

        return shortenedLink;
    }

    /**
     * ///
     *
     * @param shortUrl short URL
     * @return original URL
     */
    public String getOriginalUrl(String shortUrl) {

        // Find link in database
        Link link = linkRepository.findByShortenedLink(shortUrl);

        // return originalUrl
        return link.getOriginalUrl();
    }

    /**
     * Generate unique shortened link
     *
     * @return unique shortened link
     */
    private String generateShortenedLink() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder shortened = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) { // 6 characters for MVP
            shortened.append(characters.charAt(random.nextInt(characters.length())));
        }

        // If not unique regenerate random char
        String shortenedLink = shortened.toString();
        while (linkRepository.findByShortenedLink(shortenedLink) != null) {
            shortened.setCharAt(random.nextInt(8), characters.charAt(random.nextInt(characters.length())));
            shortenedLink = shortened.toString();
        }

        return shortenedLink;
    }
}