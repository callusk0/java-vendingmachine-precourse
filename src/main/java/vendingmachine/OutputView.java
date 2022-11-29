package vendingmachine;

public class OutputView {

    public static void printGetVendingMoney() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
    }

    public static void printGetGoods() {
        System.out.println("상품명과 가격, 수량을 입력해 주세요.");
    }

    public static void printVendingCoinList(String coinList) {
        System.out.println("자판기가 보유한 동전");

        System.out.print(coinList);
    }

    public static void printGetInputMoney() {
        System.out.println("투입 금액을 입력해 주세요.");
    }

    public static void printInputMoney(int inputMoney) {
        System.out.println("투입 금액: " + Integer.toString(inputMoney) + "원");
    }

    public static void printGetGoodsName() {
        System.out.println("구매할 상품명을 입력해 주세요.");
    }

    public static void printChanges(String changes) {
        System.out.print("잔돈\n" + changes);
    }

}
