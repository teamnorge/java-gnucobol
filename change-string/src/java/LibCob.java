import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

/**
 * libcob interface, initialising GnuCOBOL run time
 */
public interface LibCob extends Library  {
    libcob INSTANCE = Native.load("cob", libcob.class);
    void cob_init(int argc, Pointer argv);
}
