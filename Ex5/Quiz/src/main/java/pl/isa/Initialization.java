package pl.isa;

public class Initialization {
    public static void fillBaseWithDefaultQuestions() {

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
        //wielokrotnego wyboru
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
        new Question(QuestionCategory.GIT, "Jakie komendy pozawalają modyfikować historii w Gicie",
                new Question.Answer('a', "$ git revert", true),
                new Question.Answer('b', "$ git reload", false),
                new Question.Answer('c', "$ git rebase", true)
        );
        new Question(QuestionCategory.GIT, "Określ właściwości tag-ów",
                new Question.Answer('a', "Zwykłe tagi są ignorowane prze komendę push", true),
                new Question.Answer('b', "Tag może być ustawiony na commit lub na branch", true),
                new Question.Answer('c', "Tag może zmodyfikować zawartość commita", false)
        );
    }
}
