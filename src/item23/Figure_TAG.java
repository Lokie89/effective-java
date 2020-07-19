package item23;

public class Figure_TAG {
    enum Shape {
        RECTANGLE,
        CIRCLE,
        ;
    }

    final Shape shape;

    // RECTANGLE 일 때만 사용
    double length;
    double width;

    // CIRCLE 일 때만 사용
    double radius;

    Figure_TAG(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    Figure_TAG(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    double area() {
        switch (shape) {
            case RECTANGLE:
                return length * width;
            case CIRCLE:
                return Math.PI * (radius * radius);
            default:
                throw new AssertionError(shape);
        }
    }

}
