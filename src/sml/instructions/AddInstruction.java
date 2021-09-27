package sml.instructions;

import sml.Instruction;
import sml.Machine;

/**
 * This class represents an add instruction.
 *
 * @author Sicong Chen (schen08)
 */
public final class AddInstruction extends Instruction {

    private int rResult;
    private int r1;
    private int r2;

    /**
     * Constructor: an add instruction
     *
     * @param label label of the instruction
     * @param rResult the register stores the computation result
     * @param r1 the register stores the first operand
     * @param r2 the register stores the second operand
     */
    public AddInstruction(String label, int rResult, int r1, int r2) {
        super(label, "add");
        this.rResult = rResult;
        this.r1 = r1;
        this.r2 = r2;
    }

    /**
     * Executes the add instruction, probably modifying the registers.
     *
     * @param m the machine in which to execute the instruction.
     */
    @Override
    public void execute(Machine m) {
        int value1 = m.getRegisters().getRegister(r1);
        int value2 = m.getRegisters().getRegister(r2);
        m.getRegisters().setRegister(rResult, value1 + value2);
    }

    /**
     * String representation of the instruction
     *
     * @return string representation of the instruction
     */
    @Override
    public String toString() {
        return super.getLabel() + ": " + super.getOpcode() + " r" + r1 + " and r" + r2 + " to r" + rResult;
    }
}
