package pl.isa.Question;

public enum QuestionType {
    SINGLE_CHOICE("1","Test jednokrotnego wyboru"),
    MULTIPLE_CHOICE("2","Test wielokrotnego wyboru");

    private String sequentialNumber;
    private String explaining;

    QuestionType(String sequentialNumber, String explaining) {
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
