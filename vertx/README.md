# Programowanie reaktywne Javie

Uruchom kod z repozytorium Github (folder vertx). Użyj następującego polecenia: 
```bash
mvn compile exec:java@start
```
Po wyświetleniu komunikatu, że serwer nasłuchuje na porcie 8080 wejdź na stronę http://localhost:8080. Powinien się wyświetlić formularz HTML. W pole tekstowe wpisz swoje imię i nazwisko w postaci: imie.nazwisko (małymi literami). Po kliknięciu Submit w folderze głównym (tym, z którego wywołane zostało polecenie mvn) powinien stworzyć się plik secret.txt. Jako odpowowiedź do zadania podaj zawartość tego pliku.

## Słownik pojęć

### fluent API

Sposób pisania kody poprzez łączenie wywołań metod w łańcuchy.  Daje to możliwość łatwej implementacji,  czy tworzenia skomplikowanej logiki/obiektów. Dobrym przykładem jest 
Stream API w Javie.

### operacja blokująca

Operacja, którą musi wykonać program, a która ze względu na jej charakterystykę powoduje oczekiwanie na zasób. Przykładem jest odczyt danych z dysku: w porównaniu do szybkości procesora, odczyt z dysku jest bardzo wolny, a wątek, który czeka na odczyt zostaje uśpiony - efektywnie nie robi nic. Z angielskiego nazywa się to **blocking I/O**.

### operacja nieblokująca

W przeciwieństwie do operacji blokujących, w tym przypadku wątek zwraca wynik, jeśli jest on dostępny w trakcie wywołania, w przeciwnym razie zwraca tzw. handler, który pozwala na reakcję (wykonanie kodu), gdy tenże wynik będzie dostępny.