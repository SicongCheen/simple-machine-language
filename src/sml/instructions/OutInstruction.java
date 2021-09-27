package sml.instructions;

import sml.Instruction;
import sml.Machine;

/**
 * This class represents an out instruction.
 *
 * @author Sicong Chen (schen08)
 */
public final class OutInstruction extends Instruction {

    private int r;

    /**
     * Constructor: an out instruction
     *
     * @param label label of the instruction
     * @param register the register whose value to be printed out
     */
    public OutInstruction(String label, int register) {
        super(label, "out");
        this.r = register;
    }

    /**
     * Executes the out instruction, probably modifying the registers.
     *
     * @param m the machine in which to execute the instruction.
     */
    @Override
    public void execute(Machine m) {
        System.out.println(m.getRegisters().getRegister(r));
    }

    /**
     * String representation of the instruction
     *
     * @return string representation of the instruction
     */
    @Override
    public String toString() {
        return super.getLabel() + ": " + super.getOpcode() + " r" + r;
    }
}
