package net.sf.JRecord.zExamples.utilities.copy;

import java.io.BufferedWriter;
import java.io.FileWriter;

import net.sf.JRecord.Common.Constants;
import net.sf.JRecord.Details.AbstractLine;
import net.sf.JRecord.Details.LayoutDetail;
import net.sf.JRecord.External.CopybookLoader;
import net.sf.JRecord.IO.AbstractLineReader;
import net.sf.JRecord.IO.CobolIoProvider;
import net.sf.JRecord.Numeric.ICopybookDialects;
import net.sf.JRecord.common.TstConstants;
import net.sf.cb2xml.def.Cb2xmlConstants;

/**
 * This example shows how to write a generic utility
 * and access the fields by field-number rather than name.
 * It assumes there is only <b>one record</b> in the copybook 
 * 
 * @author Bruce Martin
 *
 */
public final class XmplCopyToCsv01 {

    private String installDir     = TstConstants.SAMPLE_DIRECTORY;
    private String salesFile      = installDir + "DTAR020.bin";
    private String salesFileOut   = installDir + "DTAR020out.csv";
    private String copybookName   = TstConstants.COBOL_DIRECTORY + "DTAR020.cbl";

    /**
     * Example of LineReader / LineWrite classes
     */
    private XmplCopyToCsv01() {
        super();

        int lineNum = 0;

        AbstractLine saleRecord;
        System.out.println(" Input File: " + salesFile);
        System.out.println("Output File: " + salesFileOut);

        try {
            int fileStructure = Constants.IO_FIXED_LENGTH;
            CobolIoProvider ioProvider = CobolIoProvider.getInstance();
            AbstractLineReader reader  = ioProvider.getLineReader(
                   fileStructure, ICopybookDialects.FMT_MAINFRAME,
                    CopybookLoader.SPLIT_NONE,  Cb2xmlConstants.USE_STANDARD_COLUMNS, 
                    copybookName, salesFile
            );
            LayoutDetail l = reader.getLayout();
            int fieldCount = l.getRecord(0).getFieldCount(); // This assumes there is only one record in the copybook
 
            BufferedWriter w = new BufferedWriter(new FileWriter(salesFileOut));

            while ((saleRecord = reader.read()) != null) {
                String sep = "";
                for (int i = 0; i < fieldCount; i++) {
            		w.write(sep);
            		w.write(saleRecord.getFieldValue(0, i).asString()); // again assuming 1 record per file
            		sep = ",";
            	}
                w.newLine();
            }

            reader.close();
            w.close();
        } catch (Exception e) {
            System.out.println("~~> " + lineNum + " " + e.getMessage());
            System.out.println();

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	new XmplCopyToCsv01();
    }
}
