package sml;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * This class represents a Translator class.
 *
 * @author Sicong Chen (schen08)
 */
public final class Translator {

    private static final String PATH = "";
    private final String fileName;
    private String line = "";

    /**
     * Constructor: Translator
     *
     * @param file the file to be translated
     */
    public Translator(String file) {
        this.fileName = PATH + file;
    }

    /**
     * Translates the small program in the file into lab (the labels) and prog (the program).
     *
     * @param lab the labels
     * @param prog the program
     * @return boolean true if no error
     */
    public boolean readAndTranslate(Labels lab, List<Instruction> prog) {

        try (var sc = new Scanner(new File(fileName), "UTF-8")) {

            lab.reset();
            prog.clear();

            try { line = sc.nextLine(); }
            catch (NoSuchElementException ioE) { return false; }

            while (line != null) {
                var label = scan();

                if (label.length() > 0) {
                    var instruction = getInstruction(label);

                    if (instruction != null) {
                        lab.addLabel(label);
                        prog.add(instruction);
                    }
                }

                try { line = sc.nextLine(); }
                catch (NoSuchElementException ioE) { return false; }
            }
        } catch (IOException ioE) {
            System.err.println("File: IO error " + ioE);
            System.exit(-1);
            return false;
        }

        return true;
    }

    /**
     * Translate line into an instruction and return the instruction.
     *
     * @param label the label of instruction
     * @return Instruction instruction translated
     */
    public Instruction getInstruction(String label) {
        if (line.equals("")) return null;
        else return InstructionFactory.getInstance().getInstruction(label, line);
    }

    /**
     * Returns the first word of line and remove it from line.
     * If there is no word, return "".
     *
     * @return String the first word
     */
    private String scan() {
        line = line.trim();
        if (line.length() == 0) {
            return "";
        }

        int i = 0;
        while (i < line.length() && line.charAt(i) != ' ' && line.charAt(i) != '\t') {
            i = i + 1;
        }
        String word = line.substring(0, i);
        line = line.substring(i);
        return word;
    }

    /**
     * Returns the first word of line as an integer.
     * If there is any error, return the maximum int.
     *
     * @return int the first word as an integer
     */
    private int scanInt() {

        String word = scan();

        if (word.length() == 0) {
            return Integer.MAX_VALUE;
        }

        try {
            return Integer.parseInt(word);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }

}
