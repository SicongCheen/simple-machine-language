package sml.instructions;

import sml.Instruction;
import sml.Machine;

/**
 * This class represents a lin instruction.
 *
 * @author Sicong Chen (schen08)
 */
public final class LinInstruction extends Instruction {

    private int r;
    private int value;

    /**
     * Constructor: a lin instruction
     *
     * @param label label of the instruction
     * @param register the register stores the value
     * @param value the value to be stored
     */
    public LinInstruction(String label, int register, int value) {
        super(label, "lin");
        this.r = register;
        this.value = value;
    }

    /**
     * Executes the lin instruction, probably modifying the registers.
     *
     * @param m the machine in which to execute the instruction.
     */
    @Override
    public void execute(Machine m) {
        m.getRegisters().setRegister(r, value);
    }

    /**
     * String representation of the instruction
     *
     * @return string representation of the instruction
     */
    @Override
    public String toString() {
        return super.getLabel() + ": " + super.getOpcode() + " r" + r + " value " + value;
    }
}
