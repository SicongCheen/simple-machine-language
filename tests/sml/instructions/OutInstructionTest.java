package sml.instructions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Machine;
import sml.Translator;

import static org.junit.jupiter.api.Assertions.*;

class OutInstructionTest {

    private static String fileName = "out.sml";
    private static Machine m;
    private static Translator t;

    @BeforeEach
    void setUp() {
        m = new Machine();
        t = new Translator(fileName);
        t.readAndTranslate(m.getLabels(), m.getProg());
    }

    @Test
    void executeTest1() {
        m.execute();
        assertEquals(1, m.getPc());
    }

}