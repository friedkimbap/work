public abstract class Painter {

    protected abstract PShape loadShape(String shape, String color);

    public PShape drawing(String shape, String color) {
        PShape pshape = loadShape(shape, color);

        pshape.drawLine();;
        pshape.fillColor();
        
        return pshape;
    }
}


class PencilPainter extends Painter {
    @Override
    protected PShape loadShape(String pshape, String color) {
        switch (pshape) {
            case "circle":
                return new PencilDrawingCircle(color);
            case "rectangle":
                return new PencilDrawingRectangle(color);
            case "triangle":
                return new PencilDrawingTriangle(color);
            default:
                return null;
        }
    }

}

class CrayonPainter extends Painter {
    @Override
    protected PShape loadShape(String pshape, String color) {
        switch (pshape) {
            case "circle":
                return new CrayonDrawingCircle(color);
            case "rectangle":
                return new CrayonDrawingRectangle(color);
            case "triangle":
                return new CrayonDrawingTriangle(color);
            default:
                return null;
        }
    }

}

