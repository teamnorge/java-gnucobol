import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class JavaGnuCobolChangeString {
    private static Pointer pointer;

    public static void main(String[] args) {
         
	/*
	* Try and initialise the GnuCOBOL run time
        * calling init with no parameters
        */
        try {
            LibCob.INSTANCE.cob_init(0, null);
        } catch (UnsatisfiedLinkError e) {
            System.out.println("LibCob Exception" + e);
        }

        /*
         * Call a GnuCOBOL program, passing parameters from InputModel file
        */
        try {

            int stringBufferSize = 60;
	    pointer = new Memory(stringBufferSize); 
            pointer.setMemory(0, stringBufferSize, (byte)32); // 32 is space
	    String stringParameter = "Test string"; 
	    byte[] bytes = stringParameter.getBytes();
	    int stringLength = bytes.length;
            pointer.write(0, bytes, 0, stringLength);

	    // call the GnuCOBOL program
            int rc = CobolChangeStringParameterByReference.INSTANCE.cobchangestr(pointer);

            // display return-code
            System.out.print("COBOL Return Code:");
            System.out.println(rc);
   	    
	    // Read bytes from memmory after COBOL program call
	    byte[] resultBytes = new byte[stringBufferSize];
            pointer.read(0, resultBytes, 0, stringBufferSize);
	    
	    // convert bytes to String
	    String resultString = new String(resultBytes);
	    System.out.print("Result:");
	    System.out.println(resultString); 
	    
        } catch (UnsatisfiedLinkError e) {
            System.out.println("GnuCobol program call Exception" + e);
        }
    }
}

