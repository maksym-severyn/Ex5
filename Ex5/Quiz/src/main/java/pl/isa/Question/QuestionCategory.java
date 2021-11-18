package pl.isa.Question;

public enum QuestionCategory {
    GIT("1","Podstawy Git"),
    JAVA_SE("2","Podstawy Java SE");

    private String sequentialNumber;
    private String explaining;

    QuestionCategory(String sequentialNumber, String explaining) {
        this.sequentialNumber = sequentialNumber;
        this.explaining = explaining;
    }

    public String getSequentialNumber() {
        return sequentialNumber;
    }

    public String getExplaining() {
        return explaining;
    }
}
