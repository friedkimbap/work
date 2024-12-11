public abstract class Paint {

    protected abstract Pshape drawShape(String type);

    public PShape drawing(String type) {
        PShape shape = drawShape(type);

        shape.pill();
        shape.line();

        return shape;
    }
}
