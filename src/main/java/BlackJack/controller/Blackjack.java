package blackJack.controller;

import blackJack.domain.Card.Deck;
import blackJack.domain.Result.Result;
import blackJack.domain.User.Dealer;
import blackJack.domain.User.Player;
import blackJack.domain.User.Players;
import blackJack.domain.User.User;
import blackJack.view.InputView;
import blackJack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Blackjack {

    private static final int HAND_OUT_COUNT = 2;

    public void run() {
        final Dealer dealer = new Dealer();
        final Deck deck = new Deck();
        List<String> playerNames = InputView.inputPlayerNames();
        final Players players = initPlayers(playerNames);

        handOutInitCard(dealer, players, deck);

        OutputView.printDrawMessage(playerNames);
        OutputView.printTotalUserCards(dealer, players);

        if (!dealer.isBlackJack()) {
            playGame(dealer, players, deck);
        }

        Map<Player, Integer> playerResult = Result.makePlayerResult(dealer, players);
        int dealerResult = Result.calculateDealerProfit(playerResult);

        printProfit(dealer, playerResult, dealerResult);
    }

    private Players initPlayers(List<String> playerNames) {
        List<Player> initPlayersWithBettingMoney = playerNames.stream()
                .map((name) -> new Player(name, InputView.inputBettingMoney(name)))
                .collect(Collectors.toList());
        return new Players(initPlayersWithBettingMoney);
    }

    private void printProfit(Dealer dealer, Map<Player, Integer> playerResults, int dealerResult) {
        OutputView.printFinalResult(
                dealer.getName(),
                playerResults,
                dealerResult
        );
    }

    private List<User> makeUserList(Dealer dealer, Players players) {
        List<User> users = new ArrayList<>();
        users.add(dealer);
        for (Player player : players.getPlayers()) {
            users.add(player);
        }
        return users;
    }

    private void playGame(Dealer dealer, Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            addCardPerPlayer(player, deck);
        }
        while (dealer.isPossibleToAdd()) {
            OutputView.printAddDealerCard();
            dealer.requestCard(deck.getCard());
        }
        OutputView.printTotalResult(makeUserList(dealer, players));
    }

    private void addCardPerPlayer(Player player, Deck deck) {
        while (player.isPossibleToAdd() && InputView.askOneMoreCard(player)) {
            player.requestCard(deck.getCard());
            OutputView.printPlayerCard(player);
        }
    }

    private void handOutInitCard(Dealer dealer, Players players, Deck deck) {
        for (int i = 0; i < HAND_OUT_COUNT; i++) {
            dealer.dealCard(deck.getCard());
            players.dealCardToPlayers(deck);
        }
    }
}
