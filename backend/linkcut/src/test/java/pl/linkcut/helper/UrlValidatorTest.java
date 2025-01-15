package pl.linkcut.helper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import pl.linkcut.exception.InvalidUrlException;
import pl.linkcut.exception.UnsafeUrlException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UrlValidatorTest {

    private GoogleSafeBrowsingService safeBrowsingServiceMock;
    private UrlValidator urlValidator;

    @BeforeEach
    void setUp() {
        // Mock the GoogleSafeBrowsingService
        safeBrowsingServiceMock = Mockito.mock(GoogleSafeBrowsingService.class);

        // Inject the mock into UrlValidator
        urlValidator = new UrlValidator(safeBrowsingServiceMock);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "https://www.example.com",
            "http://example.com",
            "https://subdomain.example.com",
            "https://www.example.org/path?query=1",
            "https://example.com:8080",
            "wp.pl",
            "www.wp.pl"
    })
    void validateUrl_shouldPassForValidSafeUrls(String validSafeUrl) {
        // Arrange
        when(safeBrowsingServiceMock.isSafeUrl(validSafeUrl)).thenReturn(true);

        // Act & Assert
        urlValidator.validateUrl(validSafeUrl); // Should not throw any exceptions
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "ftp://example.com",
            "example",
            "www",
            ".com",
            "http://",
            "://example.com",
            "http://.com",
            "http://example..com",
            "http://example#.com",
            "http://example$.com",
            "http://exam^ple.com",
            "http://example!.com",
            "http://example.c",
            "http://example.toolongtld"
    })
    void validateUrl_shouldThrowInvalidUrlExceptionForInvalidUrls(String invalidUrl) {
        // Act & Assert
        Assertions.assertThrows(
                InvalidUrlException.class,
                () -> urlValidator.validateUrl(invalidUrl)
        );
    }

    @Test
    void validateUrl_shouldThrowUnsafeUrlExceptionForUnsafeUrl() {
        // Arrange
        String unsafeUrl = "https://www.malware.com";
        when(safeBrowsingServiceMock.isSafeUrl(unsafeUrl)).thenReturn(false);

        // Act & Assert
        assertThrows(UnsafeUrlException.class, () -> urlValidator.validateUrl(unsafeUrl));
    }
}