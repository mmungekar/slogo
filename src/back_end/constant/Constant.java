package back_end.constant;

public interface Constant {
	public static final String COMMAND_TYPE = "Command";
	public static final String CONSTANT_TYPE = "Constant";
	public static final String VARIABLE_TYPE = "Variable";
	public static final String COMMENT_TYPE = "Comment";
	public static final String LISTSTART_TYPE = "ListStart";
	public static final String LISTEND_TYPE = "ListEnd";
	public static final String GROUPSTART_TYPE = "GroupStart";
	public static final String GROUPEND_TYPE = "GroupEnd";
	
	// Turtle 
	public static final int NUM_FORWARD = 1;
	public static final int NUM_BACK = 1;
	// Math
    public static final int NUM_SUM = 2;
    public static final int NUM_DIFFERENCE = 2;
    public static final int NUM_PRODUCT = 2;
    public static final int NUM_QUOTIENT = 2;
    public static final int NUM_REMINDER = 2;
    public static final int NUM_MINUS = 1;
    public static final int NUM_RANDOM = 1;
    public static final int NUM_SIN = 1;
    public static final int NUM_COS = 1;
    public static final int NUM_TAN = 1;
    public static final int NUM_ATAN = 1;
    public static final int NUM_LOG = 1;
    public static final int NUM_POW = 2;
    public static final double PI = Math.PI;
    
    // Boolean
    public static final int NUM_LESS = 2;
    public static final int NUM_GREATER = 2;
    public static final int NUM_EQUAL = 2;
    public static final int NUM_NOTEQUAL = 2;
    public static final int NUM_AND = 2;
    public static final int NUM_OR = 2;
    public static final int NUM_NOT = 1;
}
