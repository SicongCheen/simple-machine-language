package sml;

/**
 * This class represents an abstract instruction class.
 *
 * @author Sicong Chen (schen08)
 */
public abstract class Instruction {

    private String label;
    private String opcode;

    /**
     * Constructor: an instruction with label and opcode
     *
     * @param label  label of this instruction
     * @param opcode opcode of this instruction
     */
    public Instruction(String label, String opcode) {
        setLabel(label);
        setOpcode(opcode);
    }

    /**
     * Returns the label of this instruction.
     *
     * @return label of this instruction
     */
    protected final String getLabel() {
        return label;
    }

    /**
     * Sets the label of this instruction.
     *
     * @param label label of this instruction
     */
    protected final void setLabel(String label) {
        this.label = label;
    }

    /**
     * Returns the opcode of this instruction.
     *
     * @return opcode of this instruction
     */
    protected final String getOpcode() {
        return opcode;
    }

    /**
     * Sets the opcode of this instruction.
     *
     * @param opcode opcode of this instruction
     */
    protected final void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    /**
     * the string representation of this Instruction
     *
     * @return the string representation of this Instruction
     */
    @Override
    public abstract String toString();

    /**
     * Execute this instruction on machine m.
     *
     * @param m the machine in which to execute the instruction.
     */
    public abstract void execute(Machine m);
}
