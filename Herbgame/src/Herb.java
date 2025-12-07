import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Herb class
class Herb {
    private String name, scientificName, uses, funFact, hint;

    public Herb(String name, String scientificName, String uses, String funFact, String hint) {
        this.name = name;
        this.scientificName = scientificName;
        this.uses = uses;
        this.funFact = funFact;
        this.hint = hint;
    }

    public String getName() { return name; }
    public String getScientificName() { return scientificName; }
    public String getUses() { return uses; }
    public String getFunFact() { return funFact; }
    public String getHint() { return hint; }
}