package pl.isa;

public enum QuestionCategories {
    GIT("Podstawy Git"),
    JAVA_SE("Podstawy Java SE"),
    SQL("Podstawy Microsoft SQL Server");

    private String explaining;

    QuestionCategories(String explaining) {
        this.explaining = explaining;
    }

    public String getExplaining() {
        return explaining;
    }
}
