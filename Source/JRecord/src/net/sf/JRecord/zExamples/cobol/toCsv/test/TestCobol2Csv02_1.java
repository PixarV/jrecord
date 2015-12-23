/**
 * 
 */
package net.sf.JRecord.zExamples.cobol.toCsv.test;

import net.sf.JRecord.zExamples.cobol.toCsv.Cobol2Csv;

/**
 * @author Bruce Martin
 *
 */
public class TestCobol2Csv02_1 {

	/**
	 * @param args
	 */
	public static void main(String[] a) {

		String inputFileName = TestCobol2Csv02_1.class.getResource("DTAR020.bin").getFile();
		String[] args1= {
				"-I", inputFileName, 
				"-O", ExampleConstants.TEMP_DIR + "DTAR020_02.csv", 
				"-C", TestCobol2Csv02_1.class.getResource("DTAR020.cbl").getFile(), 
				"-Q", "\"",                /* Quote           */
				"-FS", "Fixed_Length",     /* File Structure  */
				"-IC", "CP273",            /* Character set   */
		}; /* Field Seperator will default to \t */
		
		Cobol2Csv.main(args1); 
		
		String[] args2= {
				"-I", inputFileName, 
				"-O", ExampleConstants.TEMP_DIR + "DTAR020_02_NoHeading.csv", 
				"-C", TestCobol2Csv02_1.class.getResource("DTAR020.cbl").getFile(), 
				"-Q", "\"",                /* Quote           */
				"-FS", "Fixed_Length",     /* File Structure  */
				"-IC", "CP273",            /* Character set   */
				"-OFS", "Text_UNICODE"
		}; /* Field Seperator will default to \t */
		
		Cobol2Csv.main(args2); 
	}

}
