# test_wikipedia.py
# test_wikipedia.py
import re
from playwright.sync_api import Page, expect

def test_should_find_java_article(page: Page):
    # Krok 1: Przejdź na polską Wikipedię, aby mieć pewność co do języka
    page.goto("https://pl.wikipedia.org/")

    # Stosujemy łańcuchowe lokalizatory.
    # 1. Znajdź główny formularz wyszukiwania po jego ID. Jest on unikalny.
    # 2. Wewnątrz TEGO formularza znajdź element po placeholderze.
    # To gwarantuje, że nie złapiemy pola z innego, ukrytego formularza.
    search_form = page.locator("#searchform")
    search_form.get_by_placeholder("Przeszukaj Wikipedię").fill("Python")
    
    # Krok 3: Znajdź przycisk wyszukiwania po jego polskiej nazwie i go kliknij
    page.get_by_role("button", name="Szukaj").click()

    # Krok 4: Sprawdź, czy tytuł na nowej stronie jest poprawny
    page_title = page.locator("#firstHeading")

    # Asercja szuka teraz polskiego tytułu
    expect(page_title).to_have_text("Python")
