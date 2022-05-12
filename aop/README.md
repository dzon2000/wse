# Aspect Oriented Programming

Programowanie apsektowe umożliwia wplatanie kodu, dzięki czemu często powtarzalne operacje można odłożyć w jedno centralen miejsce.
Przykładem takiej powtarzalnej operacji może być:
* logowanie
* transakcje

## Kiedy wplatamy?
Istnieje kilka momentów, kiedy możemy wpleść dodatkowy kod do wywołań naszego kodu.
### Compile-time weaving
Jeśli mamy dostęp do kodu źródłowego wpleść aspekty możemy już na etapie kompilacji. Służy do tego specjalny kompilator `ajc` (por. `javac`). Wplata on aspekty
w momencie kompilowania kodu. Nie wymaga to dodatkowej konfiguracji przy uruchomieniu aplikacji.

Aby uruchomić kod z przykładu wywołaj:

```shell
mvn clean resources:resources aspectj:compile exec:java@compile-time
```
Powyższe polecenie uruchomi 4 goal'e Mavena:
* clean - usunie poprzednie wyniki kompilacji
* Resources - skopiuje pliki z folderu `resources`. W tym wypadku jest konfiguracja Log4J z pliku `log4j2.xml`
* Kompilacja `ajc` - w tym momencie nastąpi wplecenie kodu
* Uruchomienie klasy głównej `App`

Przykładowy wynik:
```shell
20:16:03.113 [io.pw.aspect.App.main()] TRACE io.pw.aspect.Account - Withdraw called with input parameter 5
Was it successful? true
```
**Dlaczego**?

Metoda `withdraw` zwraca `boolean` i wygląda następująco:
```java
public boolean withdraw(int amount) {
    if (balance < amount) {
        return false;
    }
    balance = balance - amount;
    return true;
}
```
Aspekt natomiast uruchamiany jest przed każdym wywołaniem metody `withdraw` i loguje parametr wejściowy `amount`:
```java
@Before("execution (boolean withdraw(int))")
public void callWithdraw(JoinPoint jp) {
    logger.trace("Withdraw called with input parameter: {}", jp.getArgs()[0]);
}
```
### Load-time weaving
Jeśli nie mamy dostępu do kodu źródłowego, a chcemy dodać aspekty do wywołań poszczególnych metod musimy użyć binarnego podejścia.
Jest to tzw. Load-time weaving. W tym wypadku aspekty zostaną dodane w trakcie działania programu. Aby umożliwić laod-time weaving
potrzebujemy pliku `aop.xml`, który powinien znaleźć się w classpath w folderze `META-INF`.

Przykład pliku `aop.xml`, który definiuje aspekt oaz dodaje przełączniki do wywołania, które pokażą dodatkowe informacje (`-verbose`).
```xml
<aspectj>
    <aspects>
        <aspect name="io.pw.aspect.AccountAspect"/>
    </aspects>
    <weaver options="-verbose -showWeaveInfo">
        <include within="io.pw.aspect.*"/>
    </weaver>
</aspectj>
```
Tym razem przy uruchomieniu potrzebujemy specjalnego agenta, który zajmie się dodaniem aspektów w czasie ładowania. Robimy to dodając następujący argument do JVM:
`-javaagent:"ŚCIEŻKA_DO_aspectjweaver.jar`.

Uruchom program za pomocą polecenia:
```shell
mvn clean resources:resources compiler:compile exec:exec@load-time
```
Zwróć uwagę na zmiany w poleceniu:
* Używamy `compiler:compile`, czyli zwykłego kompilatora `javac`
* Używamy `exec:exec`, aby dodać odpowiednią ścieżkę do wspomnianego wcześniej `-javaagent`

Przykładowy wynik:
```shell
[AppClassLoader@3cd1a2f1] info AspectJ Weaver Version 1.9.9.1 built on Thursday Mar 31, 2022 at 05:00:07 PDT
[AppClassLoader@3cd1a2f1] info register classloader jdk.internal.loader.ClassLoaders$AppClassLoader@3cd1a2f1
[AppClassLoader@3cd1a2f1] info using configuration /D:/repo/dzon2000/wse/aop/target/classes/META-INF/aop.xml
[AppClassLoader@3cd1a2f1] info register aspect io.pw.aspect.AccountAspect
[AppClassLoader@3cd1a2f1] weaveinfo Join point 'method-execution(boolean io.pw.aspect.Account.withdraw(int))' in Type 'io.pw.aspect.Account' (Account.java:7) advised by before advice from 'io.pw.aspect.AccountAspect' (AccountAspect.java)
10:09:38.322 [main] TRACE io.pw.aspect.Account - Withdraw called with input parameter: 5
Was it successful? true
```
Zwróć uwagę na informacje wypisane przez AspectJ: ścieżka do pliku konfiguracyjnego `aop.xml`, oraz informacje o aspekcie, który został dodany. Tym razem
klasa została skompilowana zwykłym kompilatorem `javac`, a aspekt dodany w czasie działania programu.

Aby lepiej zrozumieć istotę tych 2 podejść sprawdźmy jak wyglądają skopilowane metody `withdraw`.

**ajc**
```java
public boolean withdraw(int amount) {
    JoinPoint var2 = Factory.makeJP(ajc$tjp_0, this, this, Conversions.intObject(amount));
    AccountAspect.aspectOf().callWithdraw(var2);
    if (this.balance < amount) {
        return false;
    } else {
        this.balance -= amount;
        return true;
    }
}

static {
    ajc$preClinit();
}
```
**javac**
```java
public boolean withdraw(int amount) {
    if (this.balance < amount) {
        return false;
    } else {
        this.balance -= amount;
        return true;
    }
}
```
Jak widać, kompilator `ajc` dodał dodatkowy kod do wynikowych plików `*.class`. Dzięki temu nie musimy się martwić o uruchomienie aplikacji z dodatkowym `javaagent`em. Z drugiej jednak strony
`javaagent` pozwala na dodanie kodu do kodu, do którego nie posiadamy źródeł np. bibliotek i plików JAR, które używamy.