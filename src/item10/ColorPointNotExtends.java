package item10;

import java.awt.*;

public class ColorPointNotExtends {

    private Point point;
    private Color color;

    public ColorPointNotExtends(int x, int y, Color color) {
        point = new Point(x, y);
        this.color = color;
    }

    public Point asPoint() {
        return point;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ColorPointNotExtends)) {
            return false;
        }
        ColorPointNotExtends cp = (ColorPointNotExtends) obj;
        return cp.point.equals(point) && cp.color.equals(color);
    }
}
