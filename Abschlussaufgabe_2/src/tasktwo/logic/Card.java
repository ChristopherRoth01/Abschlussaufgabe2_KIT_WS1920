package tasktwo.logic;

/**
 * 
 * Class that represents a Card. Every Card has a drawable and a id. The id is
 * needed, thereby every Card is individual. Ids are distributed by the number
 * of the move.
 * 
 * @author Christopher Roth
 * @version 1.0
 *
 */
public class Card {

    /**
     * The drawable of the Card.
     */
    private final Drawables drawable;

    /**
     * The id of the Card.
     */
    private int id;

    /**
     * Constructor.
     * 
     * @param drawable drawable of the Card.
     * @param id       id of the Card.
     */
    public Card(Drawables drawable, int id) {
        this.drawable = drawable;
        this.id = id;
    }

    /**
     * Getter for the Drawable of the Card.
     * 
     * @return the Drawable of the Card.
     */
    public Drawables getDrawable() {
        return drawable;
    }

    /**
     * Setter for the Id of the card. Is needed when a user loses, because then ids
     * need to be distributed new.
     * 
     * @param id new id of the card.
     */
    public void setId(int id) {
        this.id = id;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        Card src = (Card) obj;

        if (src.getId() == this.getId()) {
            return true;

        } else {
            return false;
        }

    }

    /**
     * Getter for the id of a Card.
     * 
     * @return the id of a Card.
     */
    public int getId() {
        return this.id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

}
