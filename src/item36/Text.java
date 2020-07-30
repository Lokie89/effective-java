package item36;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * @author lokie on 2020-07-30
 */
public class Text {
    public static final int STYLE_BOLD = 1 << 0;
    public static final int STYLE_ITALIC = 1 << 1;
    public static final int STYLE_UNDERLINE = 1 << 2;
    public static final int STYLE_STRIKETHROUGH = 1 << 3;

    public enum Style {
        BOLD,
        ITALIC,
        UNDERLINE,
        STRIKETHROUGH,
        ;
    }

    public static void applyStyles(Set<Style> styles) {
        System.out.println(styles);
    }

    public static void applyStyles(int styles) {
        List<Integer> styleGroup = List.of(STYLE_BOLD, STYLE_ITALIC, STYLE_UNDERLINE, STYLE_STRIKETHROUGH);
        List<Integer> applyStyles = new ArrayList<>();
        while (styles != 0) {
            for (int i = styleGroup.size() - 1; i >= 0; i--) {
                if (styles >= styleGroup.get(i)) {
                    applyStyles.add(styleGroup.get((i)));
                    styles -= styleGroup.get(i);
                }
            }
        }

        System.out.println(applyStyles);
    }

    public static void main(String[] args) {
        applyStyles(STYLE_BOLD | STYLE_ITALIC);
//        applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
    }
}
