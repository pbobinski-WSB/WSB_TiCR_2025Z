package wsb.ticr;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@DisplayName("Testy wyszukiwania w Wikipedii (Java) - Wersja Działająca")
class WikipediaJavaTest {

    // === SETUP / TEARDOWN (Zarządzanie cyklem życia) ===
    // Te sekcje pozostają takie same. Tworzą przeglądarkę i stronę.

    static Playwright playwright;
    static Browser browser;

    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        // Uruchamiamy w trybie z interfejsem, aby było widać, co się dzieje
        browser = playwright.chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false)
                .setChannel("chrome"));
    }

    @AfterAll
    static void closeBrowser() {
        if (playwright != null) {
            playwright.close();
        }
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        if (context != null) {
            context.close();
        }
    }


    // === TEST WŁAŚCIWY ===

    @Test
    @DisplayName("Powinien znaleźć artykuł o Pythonie")
    void shouldFindPythonArticle() {
        // Krok 1: Nawigacja
        page.navigate("https://pl.wikipedia.org/");

        // Krok 2: Lokalizacja i Wypełnienie Formularza
        // Znajdujemy unikalny formularz, aby zawęzić kontekst wyszukiwania.
        Locator searchForm = page.locator("#searchform");

        // Wewnątrz formularza znajdujemy pole po placeholderze i wpisujemy tekst.
        searchForm.getByPlaceholder("Przeszukaj Wikipedię").fill("Python");

        // Krok 3: Kliknięcie Przycisku
        // Również szukamy przycisku wewnątrz formularza dla większej stabilności.
        searchForm.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Szukaj")).click();

        // Krok 4: Asercja (z wbudowanym auto-czekaniem)
        // Playwright automatycznie poczeka, aż nowa strona się załaduje
        // i pojawi się na niej element #firstHeading z oczekiwanym tekstem.
        Locator pageTitle = page.locator("#firstHeading");

        // Sprawdzamy, czy tytuł jest dokładnie taki, jakiego oczekujemy.
        assertThat(pageTitle).hasText("Python");
    }
}