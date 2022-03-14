package blackJack.view;

import blackJack.dto.DealerResultDto;
import blackJack.dto.PlayerResultDto;
import blackJack.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DRAW_MESSAGE = "딜러와 %s에게 2장의 나누었습니다.\n";
    private static final String CARD_FORMAT = "%s카드: %s";
    private static final String DEALER = "딜러";
    private static final String DELIMITER = ", ";
    private static final String ADD_DEALER_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    public static final String SCORE_FORMAT = " - 결과: %d";
    public static final String FINAL_RESULT_MESSAGE = "## 최종 승패";
    public static final String NAME_FORMAT = "%s:";
    public static final String DEALER_RESULT_FORMAT = "%d승 %d패 %d무";
    public static final String PLAYER_RESULT_FORMAT = "%s";

    public static void printDrawMessage(List<String> userNames) {
        System.out.printf(DRAW_MESSAGE, userNames.stream().collect(Collectors.joining(DELIMITER)));
    }

    public static void printTotalUserCards(List<UserDto> users) {
        for (UserDto userDto : users) {
            if (userDto.getName().equals(DEALER)) {
                System.out.println(String.format(CARD_FORMAT, userDto.getName(), userDto.getCards().get(0)));
                continue;
            }
            printPlayerCard(userDto);
        }
    }

    public static void printPlayerCard(UserDto userDto) {
        String cards = userDto.getCards().stream().collect(Collectors.joining(DELIMITER));
        System.out.println(String.format(CARD_FORMAT, userDto.getName(), cards));
    }

    public static void printAddDealerCard() {
        System.out.println(ADD_DEALER_CARD_MESSAGE);
    }

    public static void printTotalResult(List<UserDto> userDtos) {
        for (UserDto userDto : userDtos) {
            String cards = userDto.getCards().stream().collect(Collectors.joining(DELIMITER));
            System.out.println(String.format(CARD_FORMAT + SCORE_FORMAT, userDto.getName(), cards, userDto.getScore()));
        }
    }

    public static void printFinalResult(List<PlayerResultDto> resultDtos, DealerResultDto dealerDto) {
        System.out.println(FINAL_RESULT_MESSAGE);

        System.out.println(String.format(NAME_FORMAT + DEALER_RESULT_FORMAT, dealerDto.getName(), dealerDto.getDealerWinCount(), dealerDto.getDealerLoseCount(), dealerDto.getDealerDrawCount()));
        for (PlayerResultDto resultDto : resultDtos) {
            System.out.println(String.format(NAME_FORMAT + PLAYER_RESULT_FORMAT, resultDto.getName(), resultDto.getResult()));
        }

    }
}
