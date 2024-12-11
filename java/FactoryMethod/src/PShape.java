public abstract class PShape {
    String tool;
    String shape;
    String color;

    public void drawLine() {
        System.out.println(tool + "(으)로 " + shape + "의 윤곽선을 그립니다.");
    }

    public void fillColor() {
        System.out.println(color + "(으)로 채웁니다.");
    }
}


class PencilDrawingCircle extends PShape {
    public PencilDrawingCircle(String color) {
        tool = "연필";
        shape = "원";
        this.color = color;
    }
}

class PencilDrawingRectangle extends PShape {
    public PencilDrawingRectangle(String color) {
        tool = "연필";
        shape = "사각형";
        this.color = color;
    }
}

class PencilDrawingTriangle extends PShape {
    public PencilDrawingTriangle(String color) {
        tool = "연필";
        shape = "사각형";
        this.color = color;
    }
}

class CrayonDrawingCircle extends PShape {
    public CrayonDrawingCircle(String color) {
        tool = "크레용";
        shape = "원";
        this.color = color;
    }
}

class CrayonDrawingRectangle extends PShape {
    public CrayonDrawingRectangle(String color) {
        tool = "크레용";
        shape = "사각형";
        this.color = color;
    }
}

class CrayonDrawingTriangle extends PShape {
    public CrayonDrawingTriangle(String color) {
        tool = "크레용";
        shape = "사각형";
        this.color = color;
    }
}