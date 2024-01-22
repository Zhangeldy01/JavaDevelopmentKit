package homeWork3;

/*
 * 1. Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы: sum(), multiply(),
 * divide(), subtract(). Параметры этих методов – два числа разного типа, над которыми должна быть произведена операция.
 */
public class Calculator {

    public static <T extends Number, V extends Number> Number sum(T a, V b) {
        return new Number() {
            @Override
            public int intValue() {
                return a.intValue() + byteValue();
            }

            @Override
            public long longValue() {
                return a.longValue() + b.longValue();
            }

            @Override
            public float floatValue() {
                return a.floatValue() + b.floatValue();
            }

            @Override
            public double doubleValue() {
                return a.doubleValue() + b.doubleValue();
            }
        };
    }
    public static <T extends Number, V extends Number> Number subtract(T a, V b){
        return new Number() {
            @Override
            public int intValue() {
                return a.intValue() - b.intValue();
            }

            @Override
            public long longValue() {
                return a.longValue() - b.longValue();
            }

            @Override
            public float floatValue() {
                return a.floatValue() - b.floatValue();
            }

            @Override
            public double doubleValue() {
                return a.doubleValue() - b.doubleValue();
            }
        };
    }
    public static <T extends Number, V extends Number> Number multiply(T a, V b) {
        return new Number() {
            @Override
            public int intValue() {
                return a.intValue() * b.intValue();
            }

            @Override
            public long longValue() {
                return a.longValue() * b.longValue();
            }

            @Override
            public float floatValue() {
                return floatValue() * b.floatValue();
            }

            @Override
            public double doubleValue() {
                return a.doubleValue() * b.doubleValue();
            }
        };
    }

    public static <T extends Number, V extends Number> Number divide(T a, V b){
        if(b.doubleValue() == 0) {
            throw new ArithmeticException(("Деление на ноль не допускается"));
        }
        return new Number() {
            @Override
            public int intValue() {
                return a.intValue() / b.intValue();
            }

            @Override
            public long longValue() {
                return a.longValue() / b.longValue();
            }

            @Override
            public float floatValue() {
                return a.floatValue() / b.floatValue();
            }

            @Override
            public double doubleValue() {
                return a.doubleValue() / b.doubleValue();
            }
        };
    }
}
