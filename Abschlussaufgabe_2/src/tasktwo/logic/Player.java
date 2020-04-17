package tasktwo.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import tasktwo.userinterface.ErrorMessages;

/**
 * This class represents a Player. There is only one Player for each Game. A
 * Player can do Actions like drawing Cards or building things
 * 
 * @author Christopher Roth
 * @version 1.0
 *
 */
public class Player {

    /**
     * The List that holds the drawn Cards which are Resources.
     */
    private final List<Card> drawCards;
    /**
     * The List that holds all build Elements.
     */
    private final List<Buildables> buildElements;
    /**
     * A virtual Shack. This list is only filled if Player build a Shack.
     */
    private final List<Card> shack;
    /**
     * The Card the Player last draw.
     */
    private Card lastDrawnCard;

    /**
     * Constructor.
     */
    Player() {

        drawCards = new ArrayList<Card>();
        buildElements = new ArrayList<Buildables>();
        shack = new ArrayList<Card>();
    }

    /**
     * Adds a Card to the List of drawn Cards.
     * 
     * @param card the Card to be added.
     */
    public void addCard(Card card) {
        lastDrawnCard = card;
        if (card.getDrawable().isResource()) {
            this.drawCards.add(card);
        }

    }

    /**
     * The drawn cards toString.
     * 
     * @return a String Representation of the list of Cards.
     */
    public String drawCardsToString() {
        String toReturn = "";
        if (drawCards.isEmpty()) {
            toReturn = "EMPTY";
        }
        for (Card card : this.drawCards) {
            if (card != null) {
                toReturn += card.getDrawable().getMessage() + "\n";
            }
        }
        return toReturn.trim();
    }

    /**
     * Builds a Buildable and adds it to the buildElements List. Before the method
     * checks if the Element is even buildable, if not it throws a LogicException.
     * 
     * @param argument the Buildable that should be build.
     * @throws LogicException if the Buildable the Player trys to build cannot be
     *                        build.
     */
    public void build(String argument) throws LogicException {
        Buildables toBuild;
        List<Buildables> buildables = this.removeBuildBuildables(
                Buildables.checkForPossibleBuilds(this.numberOfWood(), this.numberOfMetal(), this.numberOfPlastic()));

        if (buildables.contains(Buildables.convertToBuildable(argument))) {
            toBuild = Buildables.convertToBuildable(argument);
        } else {
            throw new LogicException(ErrorMessages.NOT_BUILDABLE.getMessage());
        }
        if (toBuild != null) {
            buildElements.add(toBuild);
            this.removeCards(toBuild);

        } else {
            throw new LogicException(ErrorMessages.NOT_BUILDABLE.getMessage());
        }
    }

    /**
     * Removes Cards from the drawCards List. Removes the Number of each Resource
     * the Buildable needs.
     * 
     * @param toBuild the Buildable that should be build.
     */
    private void removeCards(Buildables toBuild) {
        this.removeCard(Drawables.WOOD, toBuild.getWoodCount());
        this.removeCard(Drawables.METAL, toBuild.getMetalCount());
        this.removeCard(Drawables.PLASTIC, toBuild.getPlasticCount());

    }

    /**
     * Removes <count> Cards of the given type <drawable>.The latest drawn Cards are
     * priorised.
     * 
     * @param drawable the type of Cards that should be removed.
     * 
     * @param count    the number of Cards that should be removed.
     */
    private void removeCard(Drawables drawable, int count) {
        int counter = 0;
        List<Card> keys = new ArrayList<Card>();
        for (int i = drawCards.size() - 1; i >= 0; i--) {
            if (count > counter && drawCards.get(i).getDrawable().equals(drawable)) {
                keys.add(drawCards.get(i));
                counter++;
            } else if (count <= counter) {
                break;
            }
        }

        for (int i = 0; i < keys.size(); i++) {
            this.drawCards.remove(keys.get(i));
        }
    }

    /**
     * Returns the current Build Elements as a String.
     * 
     * @return a String of all build Elements
     */
    public String returnBuildingsToString() {
        String toReturn = "";
        if (buildElements.isEmpty()) {
            toReturn = "EMPTY";
        } else {
            List<String> buildingsAsStringToSort = new ArrayList<String>();
            for (Buildables buildable : buildElements) {
                buildingsAsStringToSort.add(buildable.getName());
            }
            Collections.reverse(buildingsAsStringToSort);
            for (String string : buildingsAsStringToSort) {
                toReturn += string + "\n";
            }
        }
        return toReturn.trim();
    }

    /**
     * Calculates the number of Wood the Player has.
     * 
     * @return the number of Wood.
     */
    private int numberOfWood() {
        int count = 0;
        for (int i = 0; i < drawCards.size(); i++) {
            if (drawCards.get(i) != null && drawCards.get(i).getDrawable().equals(Drawables.WOOD)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Calculates the number of Metal the Player has.
     * 
     * @return the number of Metal.
     */
    private int numberOfMetal() {
        int count = 0;
        for (int i = 0; i < drawCards.size(); i++) {
            if (drawCards.get(i) != null && drawCards.get(i).getDrawable().equals(Drawables.METAL)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Calculates the number of Plastic the Player has.
     * 
     * @return the number of Plastic.
     */
    private int numberOfPlastic() {
        int count = 0;
        for (int i = 0; i < drawCards.size(); i++) {
            if (drawCards.get(i) != null && drawCards.get(i).getDrawable().equals(Drawables.PLASTIC)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns a String of all possible Buildings at the current state. The String
     * is sorted alphabetically.
     * 
     * @return a String of all possible Buildings.
     */
    public String possibleBuildingsToString() {

        List<Buildables> buildings = Buildables.checkForPossibleBuilds(this.numberOfWood(), this.numberOfMetal(),
                this.numberOfPlastic());

        List<Buildables> possibleBuildings = this.removeBuildBuildables(buildings);
        List<String> buildingsAsStringToSort = new ArrayList<String>();
        for (Buildables buildable : possibleBuildings) {
            buildingsAsStringToSort.add(buildable.getName());
        }
        if (buildingsAsStringToSort.isEmpty()) {
            return "EMPTY";
        }
        Collections.sort(buildingsAsStringToSort);

        String toReturn = "";
        for (String string : buildingsAsStringToSort) {
            toReturn += string + "\n";
        }
        return toReturn.trim();

    }

    /**
     * Gets a List of Buildables and removes all Buildables that are already build.
     * Also removes Ballon and SteamBoat if a FirePlace is not existing.
     * 
     * @param buildings the List that should be transformed.
     * @return a transformed List with all Buildables that can really be build.
     */
    private List<Buildables> removeBuildBuildables(List<Buildables> buildings) {
        List<Buildables> buildsArranged = new ArrayList<Buildables>();
        for (Buildables buildable : buildings) {
            buildsArranged.add(buildable);
        }

        for (Buildables buildable : buildings) {
            if (this.buildElements.contains(buildable)) {
                buildsArranged.remove(buildable);
            }
        }
        if (!this.buildElements.contains(Buildables.FIREPLACE)) {
            buildsArranged.remove(Buildables.BALLON);
            buildsArranged.remove(Buildables.STEAMBOAT);
        }
        return buildsArranged;
    }

    /**
     * Method that is called if a thunderstorm Card is drawn. Removes the Fireplace
     * from the buildElements. Also removes all drawn Cards and refills it with the
     * cards from the Shack if the Shack is build.
     */
    public void catastrophe() {

        this.removeFireplace();
        this.fillShack();
        this.removeDrawnCards();
        this.addCardsFromShack();
    }

    /**
     * Method that fills a Shack if a Shack is already build.
     */
    public void fillShack() {
        final int shackSize = 5;
        int counter = 0;
        if (buildElements.contains(Buildables.SHACK)) {
            shack.clear();
            ListIterator<Card> iterator = drawCards.listIterator(drawCards.size());
            while (iterator.hasPrevious() && counter < shackSize) {
                shack.add(iterator.previous());
                counter++;
            }
            Collections.reverse(shack);
        }
    }

    /**
     * Method that refills the List of drawnCards with the Cards from the Shack.
     * Clears the Shack after refill.
     */
    public void addCardsFromShack() {

        for (int i = 0; i < this.shack.size(); i++) {
            this.drawCards.add(shack.get(i));
            this.shack.get(i).setId(i);
        }
        shack.clear();
    }

    /**
     * Getter for the last drawn Card.
     * 
     * @return the last drawn card
     */
    public Card getLastDrawnCard() {
        return this.lastDrawnCard;
    }

    /**
     * Removes a Fireplace from the List of buildElements. Method is only called
     * when a thunderstorm card is drawn.
     */
    private void removeFireplace() {

        this.buildElements.remove(Buildables.FIREPLACE);
    }

    /**
     * Clears the List of all buildings.
     */
    public void clearBuildings() {
        this.buildElements.clear();
    }

    /**
     * Clears a List of all drawn Cards.
     */
    public void removeDrawnCards() {
        this.drawCards.clear();

    }

    /**
     * Checks if the list of buildElements contains a given buildable.
     * 
     * @param buildable the buildable that should be matched.
     * @return the fitting buildable, or null
     */
    public Buildables hasBuilding(Buildables buildable) {
        return buildElements.stream().filter(s -> s.equals(buildable)).findAny().orElse(null);
    }

    /**
     * Returns if the Player can build no more Buildings
     * 
     * @return true if Player cannot build any Buildings, false if he can.
     */
    public boolean hasNoMoreBuildingsToBuild() {
        return this.possibleBuildingsToString().equals("EMPTY");
    }

}
