package sml.instructions;

import sml.Instruction;
import sml.Machine;

/**
 * This class represents a bnz instruction.
 *
 * @author Sicong Chen (schen08)
 */
public final class BnzInstruction extends Instruction {

    private int r;
    private String labelNext;

    /**
     * Constructor: a bnz instruction
     *
     * @param label label of the instruction
     * @param register the register stores the value
     * @param labelNext the label jump to
     */
    public BnzInstruction(String label, int register, String labelNext) {
        super(label, "bnz");
        this.r = register;
        this.labelNext = labelNext;
    }

    /**
     * Executes the bnz instruction, probably modifying the registers.
     *
     * @param m the machine in which to execute the instruction.
     */
    @Override
    public void execute(Machine m) {
        if (m.getRegisters().getRegister(r) != 0)
            m.setPc(m.getLabels().indexOf(labelNext));
    }

    /**
     * String representation of the instruction
     *
     * @return string representation of the instruction
     */
    @Override
    public String toString() {
        return super.getLabel() + ": " + super.getOpcode() + " r" + r + " label " + labelNext;
    }
}
