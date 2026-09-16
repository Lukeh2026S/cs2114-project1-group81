package collections;

public class User {

    private Deck userHand = new Deck(0);
    private int chips;

    public User(int chips) {
        this.chips = chips;
    }


    public void drawNumCards(int num) {
        for (int i = 0; i < num; i++) {
            userHand.addCard(Game.drawPile.drawCard(0));
        }
    }


    public int getUserHandLength() {
        return userHand.getDeckLength();
    }


    public int userHandValue() {
        return userHand.getDeckValue();
    }


    public Card getUserCard(int cardNum) {
        return userHand.getCard(cardNum);
    }


    public void addUserCard(Card card) {
        userHand.addCard(card);
    }


    public int getUserChips() {
        return chips;
    }


    public void setUserChips(int Chips) {
        chips = Chips;
    }


    public void addUserChips(int Chips) {
        chips += Chips;
    }


    public boolean betUserChips(int Chips) {
        if (Chips <= chips) {
            chips -= Chips;
            return true;
        }
        return false;
    }

    public void printUserCards() {
        for (int i = 0; i < userHand.getDeckLength(); i++) {
            System.out.print(userHand.getCard(i).getSuitAndRank() + " ");
        }
    }


    public void userOptions(Card dealerCard) {
        System.out.print("1. Hit\n2. Stand");
        if (userHand.getDeckLength() <= 2) {
            System.out.print("\n3. Double");
        }
        if (userHand.getCard(0).getRank().equals(userHand.getCard(1).getRank())
            && userHand.getDeckLength() == 2) {
            System.out.println("4. Split");
            if (dealerCard.getRank() == "A") {
                System.out.println("5. Insurance");
            }
        }
        else {
            if (dealerCard.getRank() == "A" && userHand.getDeckLength() == 2) {
                System.out.println("4. Insurance");
            }
        }
    }


    public boolean userAction(String input, Card dealerCard) {
        int inputInt;
        if (input == null) {
            System.out.println(
                "Please input an option or a number corresponding to an option.");
            return true;
        }
        try {
            inputInt = Integer.parseInt(input);
            if (inputInt == 1) {
                userHand.addCard(Game.drawPile.drawCard(0));
                return true;
            }
            else if (inputInt == 2) {
                return false;
            }
            else if (inputInt == 3 && userHand.getDeckLength() <= 2) {
                userHand.addCard(Game.drawPile.drawCard(0));
                return false;
            }
            else if (inputInt == 4) {
                if (userHand.getCard(0).getRank().equals(userHand.getCard(1)
                    .getRank())) {
                    return true;
                } else if (dealerCard.getRank() == "A") {
                    return true;
                } else {
                    System.out.println(
                        "Please input an option or a number corresponding to an option.");
                    return true;
                }
                    
            }
            else if (inputInt == 5 && dealerCard.getRank() == "A" && userHand
                .getCard(0).getRank().equals(userHand.getCard(1).getRank())) {
                return true;
            }
            else {
                System.out.println(
                    "Please input an option or a number corresponding to an option.");
                return true;
            }

        }
        catch (NumberFormatException e) {
            if (input.toLowerCase().equals("hit")) {
                userHand.addCard(Game.drawPile.drawCard(0));
                return true;
            }
            else if (input.toLowerCase().equals("stand")) {
                return false;
            }
            else if (input.toLowerCase().equals("double") && userHand.getDeckLength() <= 2) {
                userHand.addCard(Game.drawPile.drawCard(0));
                return false;
            }
            else if (input.toLowerCase() == "split" && userHand.getCard(0)
                .getRank().equals(userHand.getCard(1).getRank())) {
                return true;
            }
            else if (input.toLowerCase() == "insurance" && dealerCard
                .getRank() == "A") {
                return true;
            }
            else {
                System.out.println(
                    "Please input an option or a number corresponding to an option.");
                return true;
            }
        }
    }
}
