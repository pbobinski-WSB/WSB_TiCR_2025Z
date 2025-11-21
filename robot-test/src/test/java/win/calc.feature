Feature: windows calculator

Scenario:
* robot { window: 'Kalkulator', fork: 'calc', highlight: true, highlightDuration: 500 }
* click('Wyczyść')
* click('Jeden')
* click('Plus')
* click('Dwa')
* click('Równa się')
* match locate('#CalculatorResults').name == 'Wyświetlana wartość to 3'
* screenshot()
* click('Zamknij aplikację Kalkulator')