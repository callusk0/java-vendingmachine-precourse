package vendingmachine;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        inputVendingMoney(vendingMachine);

        printVendingCoinList(vendingMachine);

        addGoods(vendingMachine);

        inputMoney(vendingMachine);

        while(vendingMachine.CanPurchase())
            purchaseGoods(vendingMachine);

        printExchange(vendingMachine);
    }

    private static void printExchange(VendingMachine vendingMachine) {
        OutputView.printInputMoney(vendingMachine.getInputMoney());
        OutputView.printChanges(vendingMachine.getChanges());
    }

    private static void purchaseGoods(VendingMachine vendingMachine) {
        try {
            OutputView.printInputMoney(vendingMachine.getInputMoney());
            OutputView.printGetGoodsName();
            String buyingGoods = InputView.getGoodsPurchase();
            vendingMachine.purchaseGoods(buyingGoods);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            purchaseGoods(vendingMachine);
        }
    }

    private static void inputMoney(VendingMachine vendingMachine) {
        try {
            OutputView.printGetInputMoney();
            int inputMoney = InputView.getInputMoney();
            vendingMachine.setInputMoney(inputMoney);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputMoney(vendingMachine);
        }
    }

    private static void addGoods(VendingMachine vendingMachine) {
        try {
            OutputView.printGetGoods();
            List<Goods> goodsList = InputView.getGoods();
            vendingMachine.addGoods(goodsList);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            addGoods(vendingMachine);
        }
    }

    private static void printVendingCoinList(VendingMachine vendingMachine) {
        String coinList = vendingMachine.getCoinString();
        OutputView.printVendingCoinList(coinList);
    }

    private static void inputVendingMoney(VendingMachine vendingMachine) {
        int inputMoney = 0;
        try {
            OutputView.printGetVendingMoney();
            inputMoney = InputView.getVendingMoney();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputVendingMoney(vendingMachine);
        }
        vendingMachine.generateCoin(inputMoney);
    }

}
