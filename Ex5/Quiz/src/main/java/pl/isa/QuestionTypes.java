package pl.isa;

public enum QuestionTypes {
    SINGLE_CHOICE("Test jednokrotnego wyboru"),
    MULTIPLE_CHOICE("Test wielokrotnego wyboru");

    private String explaining;

    QuestionTypes(String explaining) {
    this.explaining = explaining;
    }

    public String getExplaining() {
        return explaining;
    }
}
