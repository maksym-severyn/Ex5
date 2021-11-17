package pl.isa;

public enum QuestionCategory {
    GIT("Podstawy Git"),
    JAVA_SE("Podstawy Java SE");

    private String explaining;

    QuestionCategory(String explaining) {
        this.explaining = explaining;
    }

    public String getExplaining() {
        return explaining;
    }
}
