package Dollar;

public class Dollar {

    //VARIABLES

    private static final int MAX_VALUE = 9999999;
    private static final int MIN_VALUE = -9999999;

    private int cents;


    //GETTERS

    public int getCents() {
        return cents;
    }

    public static int getMaxValue() {
        return MAX_VALUE;
    }

    public static int getMinValue() {
        return MIN_VALUE;
    }


    //SETTERS

    public void setCents(int cents) {
        this.cents = cents;
    }


    //CONSTRUCTEURS

    public Dollar(int cents) {
        this.cents = cents;
    }


    //METHODES

    public static Dollar Addition(Dollar... Dollars) {

        DollarFactory dollarFactory = new DollarFactory();
        int sum = 0;

        for (Dollar d : Dollars)
            sum += d.getCents();

        return dollarFactory.getDollar(sum);
    }

    public static Dollar Soustraction(Dollar d1, Dollar d2) {
        DollarFactory dollarFactory = new DollarFactory();
        return dollarFactory.getDollar(d1.getCents() - d2.getCents());
    }

    public static Dollar Multiplication(Dollar d1, int Pourcentage) {
        DollarFactory dollarFactory = new DollarFactory();
        return dollarFactory.getDollar((d1.getCents()*Pourcentage)/100) ;
    }

    public static Dollar Maximum(Dollar... Dollars) {
        DollarFactory dollarFactory = new DollarFactory();

        Dollar dollarMax = Dollars[0];

        for (Dollar d : Dollars) {
            if (d.getCents() > dollarMax.getCents())
                dollarMax = d;
        }

        return dollarFactory.getDollar(dollarMax.getCents());
    }

    public static Dollar Minimum(Dollar... Dollars) {
        DollarFactory dollarFactory = new DollarFactory();

        Dollar dollarMin = Dollars[0];

        for (Dollar d : Dollars) {
            if (d.getCents() < dollarMin.getCents())
                dollarMin = d;
        }

        return dollarFactory.getDollar(dollarMin.getCents());
    }


    @Override
    public String toString() {
        return String.valueOf(cents/100) + '.' + String.valueOf((Math.abs(cents)%100)/10) + String.valueOf(Math.abs(cents)%10) + '$';
    }

    // -12321


}
