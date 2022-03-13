package BlackJack.domain.User;

import BlackJack.domain.Card.Card;
import BlackJack.domain.Card.Cards;
import BlackJack.domain.Card.Number;
import BlackJack.domain.Card.Shape;
import BlackJack.domain.Result;
import BlackJack.domain.User.Dealer;
import BlackJack.domain.User.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("딜러가 소지한 카드가 16 이하면 true를 반환한다.")
    void checkScoreWhenUnder16Test() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Shape.HEART, Number.JACK));
        assertThat(dealer.checkPossibleAdd(dealer.getScore())).isEqualTo(true);
    }

    @Test
    @DisplayName("딜러가 소지한 카드가 16 초과면 false를 반환한다.")
    void checkScoreWhenOver16Test() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Shape.HEART, Number.JACK));
        dealer.addCard(new Card(Shape.HEART, Number.TEN));
        assertThat(dealer.checkPossibleAdd(dealer.getScore())).isEqualTo(false);
    }
//
//    @Test
//    @DisplayName("플레이어의 카드 합이 21이 넘으면 플레이어가 패배한다.")
//    void PlayerIsLose_WhenOver21() {
//         Cards over21Cards = new Cards(Arrays.asList(
//                new Card(Shape.HEART, Number.JACK),
//                new Card(Shape.HEART, Number.TEN),
//                new Card(Shape.CLOVER, Number.TWO)));
//        Player player = new Player("test");
//        Dealer dealer = new Dealer();
//        Result actual = dealer.compare(player);
//        Result expected = Result.LOSE;
//        assertThat(actual).isEqualTo(expected);
//    }
//
//    @Test
//    @DisplayName("플레이어의 카드 합이 21이하이며, 딜러보다 21에 가까울때 플레이어가 승리한다.")
//    void PlayerWinTest_WhenUnder21AndOverThanDealer() {
//        Cards playerCards = new Cards(Arrays.asList(
//               new Card(Shape.HEART, Number.JACK),
//               new Card(Shape.HEART, Number.TEN)));
//        Cards dealerCards = new Cards(Arrays.asList(
//                       new Card(Shape.HEART, Number.JACK)));
//       Player player = new Player("test");
//       Dealer dealer = new Dealer();
//       Result actual = dealer.compare(player);
//       Result expected = Result.WIN;
//       assertThat(actual).isEqualTo(expected);
//    }
//
//    @Test
//    @DisplayName("딜러의 카드가 21을 넘으면 21이 안넘은 플레이어는 승리한다.")
//    void PlayerIsWinTest_WhenUnder21AndDealerOverThan21() {
//        Cards playerCards = new Cards(Arrays.asList(
//                       new Card(Shape.HEART, Number.JACK),
//                       new Card(Shape.HEART, Number.TEN)));
//        Cards dealerCards = new Cards(Arrays.asList(
//                       new Card(Shape.HEART, Number.JACK),
//                new Card(Shape.CLOVER, Number.JACK),
//                new Card(Shape.HEART, Number.EIGHT)));
//        Player player = new Player("test");
//        Dealer dealer = new Dealer();
//        Result actual = dealer.compare(player);
//        Result expected = Result.WIN;
//        assertThat(actual).isEqualTo(expected);
//    }


}