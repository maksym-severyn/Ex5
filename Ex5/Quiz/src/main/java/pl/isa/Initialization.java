package pl.isa;


public class Initialization {

    public static void fillBaseWithDefaultQuestions() {
        if (Main.QUESTION_SERVICE.readObjectsFromBase(Question.class, Main.QUESTIONS_BASE_PATH).size() == 0) {
            new Question(QuestionCategory.JAVA_SE, "Język Java cechuje się tym, że",
                    new Question.Answer('a', "Jest językiem stricte programowania funkcyjnego", false),
                    new Question.Answer('b', "Jest językiem stricte programowania obiektowego", false),
                    new Question.Answer('c', "Zawiera elementy programowania funkcyjnego", true)
            );
            new Question(QuestionCategory.JAVA_SE, "W jaki sposób można dostać się do zawartości HashSet",
                    new Question.Answer('a', "Za pomocą iteratora", true),
                    new Question.Answer('b', "Za pomocą metody get()", false),
                    new Question.Answer('c', "Za pomocą metody getValue()", false)
            );
            new Question(QuestionCategory.JAVA_SE, "W jaki sposób mozna dodać do istniejącej tablicy int[] i = {1,2,3} kolejną pozycję, tak, aby mieć i = {1,2,3,4}",
                    new Question.Answer('a', "Za pomocą polecenia i.add(3,4)", false),
                    new Question.Answer('b', "Najpierw utworzyć nową tablicę o większej liczbie elementów. Następnie dodać zawartość z tablicy pierwotnej, a następnie dodać element \"4\"", true),
                    new Question.Answer('c', "Za pomocą polecenia i.put(3,4)", false)
            );
            new Question(QuestionCategory.JAVA_SE, "Wybierz prawidłowe stwierdzenie",
                    new Question.Answer('a', "Lambdy zostały dodane do Java SE od wersji 1.7", false),
                    new Question.Answer('b', "TreeMap zachowuje kolejność dodawania elementów", false),
                    new Question.Answer('c', "W mapach iteruje się po klaczach mapy", true)
            );
            new Question(QuestionCategory.JAVA_SE, "Wybierz prawidłowe stwierdzenie",
                    new Question.Answer('a', "Lambdy są dostępne w Java SE wersji 11", true),
                    new Question.Answer('b', "LinkedHashMap przechowuje elementy w formie posortowanej", false),
                    new Question.Answer('c', "W celu sortowania mapy neleży uzyć metody order()", false)
            );
            new Question(QuestionCategory.JAVA_SE, "Klasę anonimową można utworzyć z",
                    new Question.Answer('a', "Interfejsu", true),
                    new Question.Answer('b', "Klasy abstrakcyjnej", true),
                    new Question.Answer('c', "Klasy publicznej", false)
            );
            new Question(QuestionCategory.JAVA_SE, "Określ sposób działania interfejsu funkcjonalnego Supplier",
                    new Question.Answer('a', "Pobiera jedną wartość, i zwraca boolean", false),
                    new Question.Answer('b', "Nic nie pobiera", true),
                    new Question.Answer('c', "Coś zwraca", true)
            );
            new Question(QuestionCategory.JAVA_SE, "Wybierz prawidłowe stwierdzenia",
                    new Question.Answer('a', "Przy standardowej serializacji można korzystać z obiektu klasy FileOutputStream", true),
                    new Question.Answer('b', "Standardowa serializacja nie serializuje ENUMy", true),
                    new Question.Answer('c', "Przy użyciu słowa transient do konkretnego pola, nie będzie ono serializowane", true)
            );
            new Question(QuestionCategory.JAVA_SE, "Wybierz prawidłowe stwierdzenia",
                    new Question.Answer('a', "Mapy implementują interfejs Iterable", false),
                    new Question.Answer('b', "Gson nie jest biblioteką, stworzoną przez Yahoo", true),
                    new Question.Answer('c', "Za pomocą biblioteki Jackson można zapisywać dane do plików xml", true)
            );
            new Question(QuestionCategory.JAVA_SE, "Wybierz prawidłowe stwierdzenia",
                    new Question.Answer('a', "Można utworzyć obiekt z klasy abstrakcyjnej", false),
                    new Question.Answer('b', "Nie można utworzyć obiektu z klasy abstrakcyjnej", true),
                    new Question.Answer('c', "Polecenie prettyLooking() z biblioteki Gson pozwala na ładne formatowanie zawartości plików json", false),
                    new Question.Answer('d', "Lambdy są elementami programowania funkcyjnego", true)
            );
            new Question(QuestionCategory.GIT, "Jaka komenda wywołuje tzw. \"niezależny kontener\", do którego trafiają zmiany, które zaszły od ostatniego commitu, oraz nowoutworzone pliki",
                    new Question.Answer('a', "$ git stash", false),
                    new Question.Answer('b', "$ git stash -u", true),
                    new Question.Answer('c', "$ git stash pop", false)
            );
            new Question(QuestionCategory.GIT, "Jaka komenda przywróci wszystkie zmiany z commita 802f4f0, tworząc nowy commit",
                    new Question.Answer('a', "$ git reset 802f4f0", false),
                    new Question.Answer('b', "$ git restore 802f4f0", false),
                    new Question.Answer('c', "$ git revert 802f4f0", true)
            );
            new Question(QuestionCategory.GIT, "Jaka komenda wycofuje próbę merge, jeżeli wystąpił konflikt",
                    new Question.Answer('a', "$ git merge --abort", true),
                    new Question.Answer('b', "$ git abort", false),
                    new Question.Answer('c', "$ git restore", false),
                    new Question.Answer('d', "$ git merge restore", false)
            );
            new Question(QuestionCategory.GIT, "Jaka komenda skopiuje commit 802f4f0 do branch, na którym jesteś zacheckoutowany",
                    new Question.Answer('a', "$ git cherry --pick 802f4f0", false),
                    new Question.Answer('b', "$ git copy 802f4f0", false),
                    new Question.Answer('c', "$ git cherry-pick 802f4f0", true)
            );
            new Question(QuestionCategory.GIT, "Jaka komenda wyświetla istniejące commity",
                    new Question.Answer('a', "$ git log", true),
                    new Question.Answer('b', "$ git list", false),
                    new Question.Answer('c', "$ git ll", false)
            );
            new Question(QuestionCategory.GIT, "Co robi komenda git restore",
                    new Question.Answer('a', "Pozwala na wycofanie aktualnie wprowadzonych zmian", true),
                    new Question.Answer('b', "Najczęściej wykorzystywana na zmianach jeszcze niezacommitowanych", true),
                    new Question.Answer('c', "Domyślnie usunie zmiany untracked (nie dodane do Staging area)", true)
            );
            new Question(QuestionCategory.GIT, "Za pomocą której komendy możemy pobierać zmiany ze zdalnego repozytorium",
                    new Question.Answer('a', "$ git fetch", true),
                    new Question.Answer('b', "$ git pull", true),
                    new Question.Answer('c', "$ git add", false)
            );
            new Question(QuestionCategory.GIT, "Jakie komendy pozwalają modyfikować historię w Gicie",
                    new Question.Answer('a', "$ git revert", true),
                    new Question.Answer('b', "$ git reload", false),
                    new Question.Answer('c', "$ git rebase", true)
            );
            new Question(QuestionCategory.GIT, "Określ właściwości tag-ów",
                    new Question.Answer('a', "Zwykłe tagi są ignorowane przez komendę push", true),
                    new Question.Answer('b', "Tag może być ustawiony na commit lub na branch", true),
                    new Question.Answer('c', "Tag może zmodyfikować zawartość commita", false)
            );
            new Question(QuestionCategory.GIT, "Wybierz prawidłowe stwierdzenie",
                    new Question.Answer('c', "Dobrą praktyką jest zrobienie całego projektu, a dopiero następnie utworzenie pierwszego commita", false),
                    new Question.Answer('a', "Git działa na repozytoriach", true),
                    new Question.Answer('b', "Repozytoria mogą być lokalne i zdalne", true)

            );
        }
    }
}
