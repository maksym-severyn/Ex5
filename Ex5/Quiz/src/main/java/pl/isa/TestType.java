package pl.isa;

public enum TestType {
    SINGLE_CHOICE("Test jednokrotnego wyboru"),
    MULTIPLE_CHOICE("Test wielokrotnego wyboru");

    private String explaining;

    TestType(String explaining) {
    this.explaining = explaining;
    }

    public String getExplaining() {
        return explaining;
    }
}
