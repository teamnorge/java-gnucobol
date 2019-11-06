import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

/**
 * Gnu COBOL program interface, change String parameter passed by reference
 */
public interface CobolChangeStringParameterByReference extends Library {
    CobolChangeStringParameterByReference INSTANCE = Native.load("cobchangestr",
            CobolChangeStringParameterByReference.class);
    int cobchangestr(Pointer aValue);
}

