package vendingmachine;

import java.util.Arrays;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;

public class VendingMachine {
    private List<Integer> coinList = Arrays.asList(0, 0, 0, 0);
    private int inputMoney = 0;
    private List<Goods> goodsList;
    private int cheapest = Integer.MAX_VALUE;

    public int getInputMoney() {
        return inputMoney;
    }

    public void generateCoin(int inputVendingMoney) {
        int money = inputVendingMoney;
        while (true) {
            int randomNum = genRandomCoin(money);
            if (randomNum <= 0)
                break;
            Coin genCoin = Coin.valueOf(randomNum);
            money = genCoin.moneyExchange(money);
            coinList.set(genCoin.ordinal(), coinList.get(genCoin.ordinal()) + 1);
        }
    }

    private int genRandomCoin(int vendigMoney) {
        if (vendigMoney == 0)
            return 0;
        int randomVal = Randoms.pickNumberInList(Arrays.asList(500, 100, 50, 10));
        if (vendigMoney - randomVal < 0)
            return genRandomCoin(vendigMoney);
        return randomVal;
    }

    public String getCoinString() {
        String coinListString = "";
        for (int i = 0; i < Coin.values().length; i++) {
            coinListString += Coin.values()[i].toString(coinList.get(i));
            coinListString += "\n";
        }
        return coinListString;
    }

    public void addGoods(List<Goods> goodsList) {
        this.goodsList = goodsList;
        setCheapest(goodsList);
    }

    private void setCheapest(List<Goods> goodsList) {
        for(int index = 0;index<goodsList.size();index++) {
            if(goodsList.get(index).amount < 0)
                continue;
            int currentGoodsPrise = goodsList.get(index).prise;
            if(currentGoodsPrise < cheapest)
                cheapest = currentGoodsPrise;
        }
    }

    public void setInputMoney(int inputMoney) {
        if (inputMoney < 0)
            throw new IllegalArgumentException("[ERROR] 돈이 부족합니다.");
        this.inputMoney = inputMoney;
    }

    public void purchaseGoods(String buyingGoods) {
        Goods goods = getGoodsInList(buyingGoods);
        if (goods == null)
            throw new IllegalArgumentException("[ERROR] 상품 이름을 정확히 입력해주세요.");
        try {
            setInputMoney(inputMoney - goods.prise);
        } catch (IllegalArgumentException e) {
            return;
        }
        goods.amount--;
    }

    private Goods getGoodsInList(String goods) {
        for (int i = 0; i < goodsList.size(); i++) {
            Goods indexGoods = goodsList.get(i);
            if (indexGoods.name.equals(goods)) {
                checkGoodsAmount(indexGoods);
                return indexGoods;
            }
        }
        return null;
    }

    private void checkGoodsAmount(Goods indexGoods) {
        if(indexGoods.amount <=0)
            throw new IllegalArgumentException("[ERROR] 상품 수량이 0 이하입니다.");
    }

    public String getChanges() {
        List<Integer> exchangeList = Arrays.asList(0, 0, 0, 0);
        for (int index = 0; index < coinList.size(); index++) {
            if (coinList.get(index) > 0) {
                exchangeCoin(exchangeList, index);
            }
        }

        return getExchangeString(exchangeList);
    }

    private void exchangeCoin(List<Integer> exchangeList, int index) {
        for (; 0 < coinList.get(index);) {
            int currentMoney = Coin.values()[index].moneyExchange(inputMoney);
            if (currentMoney < 0)
                break;
            exchangeList.set(index, exchangeList.get(index) + 1);
            coinList.set(index, coinList.get(index) - 1);
            this.inputMoney = currentMoney;
        }
    }

    private String getExchangeString(List<Integer> exchangeList) {
        String exchangeResult = "";
        for (int index = 0; index < exchangeList.size(); index++) {
            if( exchangeList.get(index) > 0) {
                exchangeResult += Coin.values()[index].toString(exchangeList.get(index)) + "\n";
            }
        }
        return exchangeResult;
    }

    public boolean CanPurchase() {
        if(inputMoney < cheapest || !checkGoodsIsPurchaseAble())
            return false;
        return true;
    }

    private boolean checkGoodsIsPurchaseAble() {
        int cantBuyCount = 0;
        for(int index = 0;index<goodsList.size();index++) {
            if(goodsList.get(index).amount<=0)
                cantBuyCount++;
        }
        if(cantBuyCount == goodsList.size())
            return false;
        return true;
    }

}
