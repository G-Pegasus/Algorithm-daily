package dp;

public class JZ_63 {

    public static int maxProfit(int[] prices) {
        int cost = Integer.MAX_VALUE, profit = 0;

        for (int price: prices) {
            cost = Math.min(cost, price);
            profit = Math.max(profit, price - cost);
        }

        return profit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{2,1,4}));
    }
}
