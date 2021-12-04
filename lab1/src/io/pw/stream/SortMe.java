package io.pw.stream;

import io.pw.data.User;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SortMe {

    // https://github.com/dzon2000/wse

    /*

        * Zlicz użytkowników, którzy mają więcej niż 30 lat
        * Zlicz użytkowników, którzy mają hasło dłuższe niż 5 znaków
        * Utwórz mapę, gdzie kluczem jest nazwa użytkownika, a wartością jego hasło (w postaci String)
        * Znajdź użytkownika, który posiada email: jinks@ol.com

     */

    static List<User> users = List.of(
            new User("John", "john@gmail.com", 22, "asdf".getBytes(StandardCharsets.UTF_8)),
            new User("Jack", "jack@wp.com", 34, "qwerty".getBytes(StandardCharsets.UTF_8)),
            new User("Anna", "anna@fb.com", 31, "123fsdf".getBytes(StandardCharsets.UTF_8)),
            new User("Alice", "alice@interia.com", 28, "asgrg3df".getBytes(StandardCharsets.UTF_8)),
            new User("Chris", "chris@interia.com", 44, "fwefefw".getBytes(StandardCharsets.UTF_8)),
            new User("Jinks", "jinks@ol.com", 53, "f3fq23f".getBytes(StandardCharsets.UTF_8)),
            new User("Heather", "heather@interia.com", 27, "frqq3fdsa".getBytes(StandardCharsets.UTF_8)),
            new User("Bob", "bob@gmail.com", 26, "a3242fwsdf".getBytes(StandardCharsets.UTF_8))
    );

    public static void main(String[] args) {
        final long count = users.stream()
                .filter(user -> user.getAge() > 30)
                .count();
        System.out.println(">>" + count);

        users.stream()
                .filter(user -> user.getPassword().length > 5)
                .map(User::getName)
                .forEach(System.out::println);

        final Map<String, byte[]> collect = users.stream()
                .collect(Collectors.toMap(User::getName, User::getPassword));

        collect.forEach((s, bytes) -> System.out.println(s + " = " + new String(bytes)));

        final Optional<User> first = users.stream()
                .filter(user -> user.getEmail().equals("jinks@ol.com"))
                .findFirst();

        System.out.println(first.orElseThrow());


    }

}
