package vendingmachine;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static int getVendingMoney() {
        String str = Console.readLine();
        int vendingMoney = -1;
        try {
            vendingMoney = stringToInt(str);
            checkVendingMoneyInput(vendingMoney);
        } catch (IllegalArgumentException e) {
            throw e;
        }
        return vendingMoney;
    }

    private static void checkVendingMoneyInput(int vendingMoney) {
        if (vendingMoney < 10 || vendingMoney % 10 != 0)
            throw new IllegalArgumentException("[ERROR] 10이상의 10으로 나눠지는 숫자를 입력해주세요.");
    }

    private static int stringToInt(String str) {
        int num;
        try {
            num = Integer.parseInt(str);
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력하십시오.");
        }
        return num;
    }

    public static List<Goods> getGoods() {
        String input = Console.readLine();
        String[] goods = input.split(";");
        List<Goods> goodsList = new ArrayList<>();
        for (int i = 0; i < goods.length; i++) {
            goodsList.add(makeGoods(goods[i]));
        }
        return goodsList;
    }

    private static Goods makeGoods(String string) {
        Goods goods = null;
        try {
            String str = string.substring(1, string.length() - 1);
            String[] goodsInfo = str.split(",");
            goods = new Goods(goodsInfo[0], stringToInt(goodsInfo[1]), stringToInt(goodsInfo[2]));
            checkGoods(goods);
        } catch (IllegalArgumentException e) {
            return null;
        } catch (Exception e1) {
            throw new IllegalArgumentException("잘못된 상품 정보입니다.");
        }
        return goods;
    }

    private static void checkGoods(Goods goods) {
        if (goods.prise < 100 || goods.prise % 10 != 0)
            throw new IllegalArgumentException("[ERROR] 상품의 가격은 100원 이상, 10의 단위여야 합니다.");
    }

    public static int getInputMoney() {
        String str = Console.readLine();
        int inputMoney = -1;
        try {
            inputMoney = stringToInt(str);
            checkInputMoney(inputMoney);
        } catch (Exception e) {
            return -1;
        }
        return inputMoney;
    }

    private static void checkInputMoney(int inputMoney) {
        if (inputMoney < 100)
            throw new IllegalArgumentException("[ERROR] 최소 상품 금액은 100원 입니다. 100원 이상 입력해주세요.");
    }

    public static String getGoodsPurchase() {
        return Console.readLine();
    }

}
