import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import net.sf.JRecord.Common.RecordException;
import json2cbl.Cbl2JsonCodeTest;
import net.sf.cobolToJson.Data2Json;

public class RunData2Json01 {

	public static void main(String[] args) throws RecordException, IOException, JAXBException, XMLStreamException {
		String[] a = {
				"-cobol", Cbl2JsonCodeTest.getFullName("DTAR020.cbl"), "-font", "cp037",
				"-fileOrganisation", "FixedWidth", "-input", Cbl2JsonCodeTest.getFullName("DTAR020.bin"),
				"-output", "G:/Temp/DTAR020_A.json"
		};
 
		Data2Json.main(a);
		String[] a1 = {
				"-cobol", Cbl2JsonCodeTest.getFullName("DTAR020.cbl"), "-font", "cp037",
				"-fileOrganisation", "FixedWidth", "-input", Cbl2JsonCodeTest.getFullName("DTAR020_Tst1.bin"),
				"-output", "G:/Temp/DTAR020_B.json"
		};
 
		Data2Json.main(a1);
	}

}
