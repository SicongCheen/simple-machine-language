package sml.instructions;

import sml.Instruction;
import sml.Machine;

/**
 * This class represents a div instruction.
 *
 * @author Sicong Chen (schen08)
 */
public final class DivInstruction extends Instruction {

    private int rResult;
    private int r1;
    private int r2;

    /**
     * @param label label of the instruction
     * @param rResult the register stores the computation result
     * @param r1 the register stores the first operand
     * @param r2 the register stores the second operand
     */
    public DivInstruction(String label, int rResult, int r1, int r2) {
        super(label, "div");
        this.rResult = rResult;
        this.r1 = r1;
        this.r2 = r2;
    }

    /**
     * Executes the div instruction, probably modifying the registers.
     *
     * @param m the machine in which to execute the instruction.
     */
    @Override
    public void execute(Machine m) {
        int value1 = m.getRegisters().getRegister(r1);
        int value2 = m.getRegisters().getRegister(r2);
        try { m.getRegisters().setRegister(rResult, value1 / value2); }
        catch (ArithmeticException e) {
            m.getRegisters().setRegister(rResult, 0);
            System.out.println("Divide by zero - the value in r" + r2 + " is zero.");
        }
    }

    /**
     * String representation of the instruction
     *
     * @return string representation of the instruction
     */
    @Override
    public String toString() {
        return super.getLabel() + ": " + super.getOpcode() + " r" + r1 + " by r" + r2 + " to r" + rResult;
    }
}






