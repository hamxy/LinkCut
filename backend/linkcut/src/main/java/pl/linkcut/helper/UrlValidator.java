package pl.linkcut.helper;

import org.springframework.stereotype.Service;
import pl.linkcut.exception.InvalidUrlException;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URI;


@Service
public class UrlValidator {

    private GoogleSafeBrowsingService safeBrowsingService;

    public UrlValidator() {
    }

    public UrlValidator(GoogleSafeBrowsingService safeBrowsingService) {
        this.safeBrowsingService = safeBrowsingService;
    }

    private boolean isValidUrl(String url) {
        try {
            // Parse url
            URI uri = new URI(url);

            // Check if protocol is https
            return "https".equalsIgnoreCase(uri.getScheme());
        } catch (URISyntaxException e) {
            // Invalid url
            return false;
        }
    }

    private boolean isSafeUrl(String url) {
        return safeBrowsingService.isSafeUrl(url);
    }

    public void validateUrl(String url) {
        if (!isValidUrl(url)) {
            throw new InvalidUrlException("Provided String is not valid url address");
        }

        if (!isSafeUrl(url)) {
            throw new InvalidUrlException("Provided url is not safe");
        }
    }
}
