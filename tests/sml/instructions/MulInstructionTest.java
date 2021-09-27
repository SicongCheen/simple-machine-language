package sml.instructions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Machine;
import sml.Translator;

import static org.junit.jupiter.api.Assertions.*;

class MulInstructionTest {

    private static String fileName = "mul.sml";
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
        // r0(5) * r1(10) => r2(50)
        assertEquals(50, m.getRegisters().getRegister(2));
    }

    @Test
    void executeTest2() {
        m.execute();
        // r3(5) * r4(0) => r5(0)
        assertEquals(0, m.getRegisters().getRegister(5));
    }

    @Test
    void executeTest3() {
        m.execute();
        // r6(0) * r7(5) => r8(0)
        assertEquals(0, m.getRegisters().getRegister(8));
    }

    @Test
    void executeTest4() {
        m.execute();
        // r9(0) * r10(0) => r11(0)
        assertEquals(0, m.getRegisters().getRegister(11));
    }

    @Test
    void executeTest5() {
        m.execute();
        // r12(5) * r13(3) => r12(15)
        assertEquals(15, m.getRegisters().getRegister(12));
    }

    @Test
    void executeTest6() {
        m.execute();
        // r12(15) * r13(3) => r13(45)
        assertEquals(45, m.getRegisters().getRegister(13));
    }
}