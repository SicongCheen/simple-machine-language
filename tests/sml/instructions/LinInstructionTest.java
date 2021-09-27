package sml.instructions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Machine;
import sml.Translator;

import static org.junit.jupiter.api.Assertions.*;

class LinInstructionTest {

    private static String fileName = "lin.sml";
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
        // r0(5)
        assertEquals(5, m.getRegisters().getRegister(0));
    }

    @Test
    void executeTest2() {
        m.execute();
        // r1(15)
        assertEquals(15, m.getRegisters().getRegister(1));
    }

    @Test
    void executeTest3() {
        m.execute();
        // r2(0)
        assertEquals(0, m.getRegisters().getRegister(2));
    }

}