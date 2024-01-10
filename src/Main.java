import java.util.Arrays;

public class Main {
    public static String calculateEarnings(double dailyExpenses, String[] expeditions) {
        int totalBottles = 0;
        double totalEarnings = 0.0;

        for (String expedition : expeditions) {
            String[] parts = expedition.split(" ");
            int hours = Integer.parseInt(parts[0]);
            String path = parts[1];
            double price = Double.parseDouble(parts[2]);

            int loopSize = path.length();
            int loopCount = hours / loopSize;
            int remainingHours = hours % loopSize;

            int bottlesInLoop = (int) path.chars().filter(ch -> ch == 'B').count();
            totalBottles += loopCount * bottlesInLoop;

            if (remainingHours > 0) {
                int bottlesInRemainder = (int) path.substring(0, remainingHours).chars().filter(ch -> ch == 'B').count();
                totalBottles += bottlesInRemainder;
            }

            totalEarnings += totalBottles * price;
        }

        double averageEarnings = totalEarnings / expeditions.length;

        if (averageEarnings > dailyExpenses) {
            double extraMoneyPerDay = averageEarnings - dailyExpenses;
            return String.format("Good earnings. Extra money per day: %.2f", extraMoneyPerDay);
        } else {
            double moneyNeeded = dailyExpenses * expeditions.length - totalEarnings;
            return String.format("Hard times. Money needed: %.2f", moneyNeeded);
        }
    }

    public static void main(String[] args) {
        double dailyExpenses1 = 20.0;
        String[] expeditions1 = {"8 ABMRB 24.50", "7 ABMRBA 22.50", "10 ABC 20.00"};

        double dailyExpenses2 = 30.0;
        String[] expeditions2 = {"5 BBB 15.00", "6 ABMAB 18.00"};

        System.out.println(calculateEarnings(dailyExpenses1, expeditions1));
        System.out.println(calculateEarnings(dailyExpenses2, expeditions2));
    }
}