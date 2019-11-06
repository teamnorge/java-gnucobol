cobsub*
       identification division.
       program-id. cobchangestr.
       data division.
       linkage section.
       01 StringParameter PIC X(60).
       procedure division using
       by reference     StringParameter.
       A-Main Section.
               display 'Starting: cobchangestr.cbl'
               display 'Called With [' StringParameter ']'
               move 'String Parameter changed' to StringParameter 
               display 'Changed to [' StringParameter ']'
               move 2 TO return-code.

               goback.
