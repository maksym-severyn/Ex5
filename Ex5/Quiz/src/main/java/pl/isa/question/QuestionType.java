package pl.isa.question;

public enum QuestionType {
    SINGLE_CHOICE("Test jednokrotnego wyboru"),
    MULTIPLE_CHOICE("Test wielokrotnego wyboru");

    private String explaining;

    QuestionType(String explaining) {
        this.explaining = explaining;
    }

    public String getSequentialNumber() {
        return String.valueOf(this.ordinal() + 1);
    }

    public String getExplaining() {
        return explaining;
    }
}
