package view.types;

public enum InfoType {
    SUCCESS("\033[92m"),  // verde
    ERROR("\033[91m"),    // vermelho
    WARNING("\033[93m");  // amarelo

    private final String color;

    InfoType(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
