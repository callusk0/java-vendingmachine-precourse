package vendingmachine;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    // 추가 기능 구현

    public static Coin valueOf(int amount) {
        for(int i = 0;i<Coin.values().length;i++) {
            if(Coin.values()[i].amount == amount)
                return Coin.values()[i];
        }
        return null;
    }
    
    public int moneyExchange(int money) {
        return money - amount;
    }

    public String toString(Integer integer) {
        String howMany = Integer.toString(amount) + "원 - " + integer.toString() + "개";
        return howMany;
    }

}
