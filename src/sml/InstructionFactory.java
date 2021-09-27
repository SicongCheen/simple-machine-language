package sml;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Properties;

/**
 * This class represents an InstructionFactory class.
 *
 * @author Sicong Chen (schen08)
 */
public class InstructionFactory {

    private static InstructionFactory factory;

    private static final String FILENAME = "resources/instructions.properties";
    private Properties props;
    private Instruction instruction = null;


    /**
     * Constructor: an InstructionFactory
     */
    private InstructionFactory() {
        props = new Properties();
        try {
            props.load(new FileInputStream(FILENAME));
        }
        catch (IOException e) {
            System.err.println("Unable to load properties file.");
            System.exit(-1);
        }
    }


    /**
     * Gets the InstructionFactory instance.
     *
     * @return InstructionFactory
     */
    public static InstructionFactory getInstance() {
        if (factory == null) return new InstructionFactory();
        else return factory;
    }


    /**
     * Creates and returns the Instruction based on the opcode in the line.
     * Returns null if not succeed to create.
     *
     * @param label the label of the instruction
     * @param line the line in the file
     * @return Instruction created
     */
    public Instruction getInstruction(String label, String line) {

        // get instruction elements
        String[] elements = line.trim().split("\\s+");
        String opcode = elements[0];
        String[] operands = Arrays.copyOfRange(elements, 1, elements.length);

        // get Instruction class object
        Class instructionClass = null;
        try {
            instructionClass = getInstructionClass(opcode);
        } catch (ClassNotFoundException e) {
            System.err.println("Instruction class for opcode " + opcode + " not found (label " + label + ").");
            System.exit(-1);
        }

        // create Instruction instance
        Instruction instruction = null;
        try {
            // get param types
            Class[] paramTypes = getParamTypes(instructionClass);

            // get constructor arguments
            Object[] constructorArgs = getConstructorArgs(paramTypes, label, operands);

            // create instance
            instruction =  (Instruction) instructionClass.getConstructor(paramTypes).newInstance(constructorArgs);

        } catch (InstantiationException |  IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.err.println("Failed to create instruction object for opcode " + opcode + " (label " + label + ").");
            System.exit(-1);
        }

        return instruction;
    }


    /**
     * Creates and returns the Instruction class object.
     * Throws ClassNotFoundException if the opcode (class name) not in the properties file.
     *
     * @param opcode the opcode of the instruction
     * @return the Instruction class object
     */
    private Class getInstructionClass(String opcode) throws ClassNotFoundException {

        // get fully qualified class name, null if not found
        String className = props.getProperty(opcode);

        // create class object
        Class instructionClass;
        if (className != null) instructionClass = Class.forName(className);
        else throw new ClassNotFoundException();

        return instructionClass;
    }


    /**
     * Gets the parameter types of the Instruction class constructor.
     *
     * @param instructionClass the Instruction class
     * @return Class[] paramTypes
     */
    private Class[] getParamTypes(Class instructionClass) {

        // get constructors
        Constructor[] constructors = instructionClass.getConstructors();

        // get parameter types
        Class[] paramTypes = null;
        if (constructors.length == 1) paramTypes = constructors[0].getParameterTypes();

        return paramTypes;
    }


    /**
     * creates and returns the Instruction constructor arguments.
     *
     * @param paramTypes the parameter types of the constructor
     * @param label the label of the instruction
     * @param operands the operands of the instruction
     * @return the Instruction constructor arguments
     */
    private Object[] getConstructorArgs(Class[] paramTypes, String label, String[] operands) throws InstantiationException{

        if (paramTypes.length != operands.length + 1) throw new InstantiationException();

        Object[] constructorArgs = new Object[paramTypes.length];

        for (int i = 0; i < constructorArgs.length; i++) {
            if (i == 0) constructorArgs[i] = label;
            else {
                if (paramTypes[i].equals(String.class)) constructorArgs[i] = operands[i-1];
                if (paramTypes[i].equals(Integer.TYPE)) constructorArgs[i] = Integer.parseInt(operands[i-1]);
            }
        }

        return constructorArgs;
    }
}
