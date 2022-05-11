# Aspect Oriented Programming

Programowanie apsektowe umożliwia wplatanie kodu, dzięki czemu często potwarzalne operacje można odłozyć w jedno centralen miejsce.
Przykładem takiej powtarzalnej operacji może być:
* logowanie
* transakcje

## Kiedy wplatamy?
Istenieje kilka momentów, kiedy możemy wpleść dodatkowy kod do wywołań naszego kodu.
### Compile time
Jeśli mamy dostęp do kodu źródłowego wpleść aspekty możemy już na etapie kompilacji. Służy do tego specjalny kompilator `ajc` (por. `javac`). Wplata on aspekty
w momencie kompilowania kodu. Nie wymaga to dodatkowej konfguracji przy uruchomieniu aplikacji.

Aby uruchomić kod z przykładu wywołaj:
```shell
mvn resources:resources aspectj:compile exec:java
```
Powyższe polecenie uruchomi 3 goal'e Mavena:
* Resources - skopiuje pliki z folderu `resources`. W tym wypadku jest konfiguracja Log4J z pliku `log4j2.xml`
* Kompilacja `ajc` - w tym momencie nastąpi wplecenie kodu
* Uruchomienie klasy głównej `App`

Przykładowy wynik:
```shell
20:16:03.113 [io.pw.aspect.App.main()] TRACE io.pw.aspect.Account - Withdraw called with input parameter 5
Was it successful? true
```