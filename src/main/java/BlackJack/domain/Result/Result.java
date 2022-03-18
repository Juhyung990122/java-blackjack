package blackJack.domain.Result;

import blackJack.domain.User.Dealer;
import blackJack.domain.User.Player;
import blackJack.domain.User.Players;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public enum Result {
    BlackJack((dealer, player) -> !dealer.isBlackJack() && player.isBlackJack(), 1.5),
    LOSE((dealer, player) -> player.isBurst() || !dealer.isBurst() && dealer.getScore() > player.getScore(), -1),
    WIN((dealer, player) -> dealer.isBurst() || !player.isBurst() && dealer.getScore() < player.getScore(), 1),
    DRAW((dealer, player) -> !player.isBurst() && dealer.getScore() == player.getScore(), 0);

    private final BiPredicate<Dealer, Player> predicate;
    private final double profit;

    Result(BiPredicate<Dealer, Player> predicate, double profit) {
        this.predicate = predicate;
        this.profit = profit;
    }

    public static Result judge(Dealer dealer, Player player) {
        return Arrays.stream(values())
                .filter(result -> result.predicate.test(dealer, player))
                .findFirst()
                .get();
    }

    public int calculateBenefit(Player player) {
        return (int) (player.getBettingMoney() * profit);
    }

    public static Map<String, Integer> makePlayerResult(Dealer dealer, Players players) {
        Map<Player, Result> results = new HashMap<>();
        for (Player player : players.getPlayers()) {
            results.put(player, Result.judge(dealer, player));
        }
        return calculateProfit(results);
    }

    public Map<String, Integer> makeBlackjackResult(Players players) {
        Map<Player, Result> results = new HashMap<>();
        for (Player player : players.getPlayers()) {
            results.put(player, player.judgeByBlackjack());
        }
        return calculateProfit(results);
    }

    public static Map<String, Integer> calculateProfit(Map<Player, Result> results) {
        Map<String, Integer> profits = new HashMap<>();
        for (Map.Entry<Player, Result> entry :  results.entrySet()) {
            profits.put(entry.getKey().getName(), entry.getValue().calculateBenefit(entry.getKey()));
        }
        return profits;
    }

    public static int makeDealerResult(Map<String, Integer> playerScore) {
        List<Integer> results = playerScore.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toUnmodifiableList());

        return (int) (results.stream().mapToDouble(Integer::intValue).sum() * -1);
    }
}
