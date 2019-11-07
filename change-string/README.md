Sample program. Calling GnuCOBOL program from Java with JNA
==============================

In this example we pass String to a GnuCOBOL program as a parameter by reference, change it inside GnuCOBOL program, and then see changes on the Java side. 

**Cobol**
------


`cobol/cobchangestr.cbl`
```
linkage section.
01 StringParameter PIC X(60).
```
`StringParameter` is a character field of size 60.

```
display 'Starting: cobchangestr.cbl'
display 'Called With [' StringParameter ']'
move 'String Parameter changed' to StringParameter 
display 'Changed to [' StringParameter ']'
move 2 TO return-code.
goback.
```

Display current value of the `StringParameter`, change to "String Parameter changed", display updated value, return to a calling program with
return code - 2.

**Java**
------


`LibCob.java`
```
/**
 * libcob interface, initialising GnuCOBOL run time
 */
public interface LibCob extends Library  {
    libcob INSTANCE = Native.load("cob", libcob.class);
    void cob_init(int argc, Pointer argv);
}
```
LibCob Java interface is used to initialize and test GnuCOBOL runtime. We will make a test call towards `libcob.so` shared library.
`libcob.so` comes with the installation of the GnuCOBOL. 

---

`CobolChangeStringParameterByReference.java` 
```
/**
 * GnuCOBOL program interface, change String parameter passed by reference
 */
public interface CobolChangeStringParameterByReference extends Library {
    CobolChangeStringParameterByReference INSTANCE = Native.load("cobchangestr",
            CobolChangeStringParameterByReference.class);
    int cobchangestr(Pointer aValue);
}
```
GnuCOBOL program interface, it provides access to a native compiled GnuCOBOL program. It looks for a library with the name `cobchangestr` (`libcobchangestr.so` for the Linux environments) and call a procedure with the name `cobchangestr`. See Cobol program code above.
