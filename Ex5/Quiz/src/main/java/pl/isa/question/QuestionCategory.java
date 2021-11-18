package pl.isa.question;

public enum QuestionCategory {
    GIT("Podstawy Git"),
    JAVA_SE("Podstawy Java SE");

    private String explaining;

    QuestionCategory(String explaining) {
        this.explaining = explaining;
    }

    public String getSequentialNumber() {
        return String.valueOf(this.ordinal() + 1);
    }

    public String getExplaining() {
        return explaining;
    }
}
