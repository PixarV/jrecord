# HERE will be main readme information

## RELEASE: SNAPSHOT

### 2018.12.19 Issue: "Task-368 RDW Non zero bytes"

    For a mainframe file with variable length records each record is preceded by a four byte Record Descriptor Word (RDW) 
    and a possible four byte Block Descriptor Word (BDW). The first two bytes of the RDW (and possible BDW) contains 
    a binary value that is the length of the record (or possible block). The second two bytes of the RDW 
    (and possible BDW) usually contain binary zeroes (or x'00').
    The problem starts, when second two bytes contain not binary zeroes. In this case we have IOException in VbByteReader.read(). 
    
## RELEASE: '0.85.1'