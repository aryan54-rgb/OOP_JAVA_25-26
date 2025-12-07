import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        ArrayList<Herb> herbs = new ArrayList<>();

        herbs.add(new Herb("Tulsi", "Ocimum sanctum",
                "Boosts immunity, reduces stress",
                "Considered sacred in India",
                "Used in teas to improve immunity"));

        herbs.add(new Herb("Mint", "Mentha",
                "Aids digestion, freshens breath",
                "Used in teas and cooking worldwide",
                "Leaves are aromatic and used in food"));

        herbs.add(new Herb("Aloe Vera", "Aloe barbadensis",
                "Soothes burns, moisturizes skin",
                "Also called 'Healing Plant'",
                "Gel inside the leaf is medicinal"));

        herbs.add(new Herb("Neem", "Azadirachta indica",
                "Acts as natural pesticide, purifies blood",
                "Neem twigs were used as toothbrushes",
                "Its bitter leaves are used in medicines"));

        herbs.add(new Herb("Turmeric", "Curcuma longa",
                "Used for skin care and healing wounds",
                "Contains curcumin with anti-inflammatory properties",
                "Bright yellow powder used in curries"));

        herbs.add(new Herb("Lemongrass", "Cymbopogon citratus",
                "Helps with digestion and relieves anxiety",
                "Its scent repels mosquitoes",
                "Has a lemon-like aroma and used in herbal teas"));

        herbs.add(new Herb("Ashwagandha", "Withania somnifera",
                "Reduces stress and boosts energy",
                "Known as 'Indian Ginseng'",
                "Commonly used in Ayurvedic tonics"));

        herbs.add(new Herb("Hibiscus", "Hibiscus rosa-sinensis",
                "Promotes hair growth and heart health",
                "Used to make bright red herbal tea",
                "Flowers are used in hair oils and shampoos"));

        herbs.add(new Herb("Ginger", "Zingiber officinale",
                "Aids digestion and fights cold",
                "Used in cooking and traditional medicine",
                "Root has a strong spicy flavor"));

        new HerbGame(herbs);
    }
}
