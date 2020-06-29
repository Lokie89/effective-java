package item2;

import java.util.Objects;

/**
 * @author lokie on 2020/06/29
 */
public class NyPizza extends Pizza {
    public enum Size {
        SMALL,
        MEDIUM,
        LARGE,
        ;
    }

    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {

        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        NyPizza build() {
            return new NyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private NyPizza(Builder builder) {
        super(builder);
        size = builder.size;
    }

}
