package blackJack.dto;

import blackJack.domain.User.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDto {
    private String name;
    private List<CardDto> cards;

    private int score;

    public UserDto(String name, List<CardDto> cards, int score) {
        this.name = name;
        this.cards = new ArrayList<>(cards);
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards.stream()
                .map((card) -> card.getCardInfo())
                .collect(Collectors.toUnmodifiableList());
    }

    public int getScore() {
        return score;
    }

    public static UserDto from(User user) {
        List<CardDto> collect = user.getCards().getDeck().stream()
                .map(CardDto::from)
                .collect(Collectors.toList());
        return new UserDto(user.getName(), collect, user.getScore());
    }

}
