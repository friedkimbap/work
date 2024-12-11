public class Main {
    public static void main(String[] args) {
        Painter crayonPainter = new CrayonPainter();
        Painter pencilPainter = new PencilPainter();

        crayonPainter.drawing("circle", "파랑색");
        crayonPainter.drawing("rectangle", "검정색");

        pencilPainter.drawing("triangle", "붉은색");
        pencilPainter.drawing("circle", "흰색");
    }
}