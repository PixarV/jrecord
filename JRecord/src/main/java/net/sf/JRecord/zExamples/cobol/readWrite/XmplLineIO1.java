/*
 * @Author Bruce Martin
 * Created on 9/09/2005
 *
 * Purpose:
 *   This Example program demonstrates Reading a file using Line Based
 * Routines
 *
 * Requirements:
 *
 * 1) Check the values in Constants.java are correct !!!
 *
 */
package net.sf.JRecord.zExamples.cobol.readWrite;

import net.sf.JRecord.Common.Constants;
import net.sf.JRecord.Details.AbstractLine;
import net.sf.JRecord.External.CopybookLoader;
import net.sf.JRecord.IO.AbstractLineReader;
import net.sf.JRecord.IO.CobolIoProvider;
import net.sf.JRecord.Numeric.ICopybookDialects;
import net.sf.JRecord.common.TstConstants;
import net.sf.cb2xml.def.Cb2xmlConstants;

/**
 * This Example program demonstrates Reading a file using Line Based
 * Routines
 *
 * @author Bruce Martin
 *
 */
public final class XmplLineIO1 {

    /**
     *
     */
    private XmplLineIO1() {
        super();

        String vendorFile          = TstConstants.SAMPLE_DIRECTORY
		                           + "Ams_VendorDownload_20041229.txt";
        String copybookName        = TstConstants.COBOL_DIRECTORY
		                           + "AmsVendor.cbl";
        CobolIoProvider ioProvider = CobolIoProvider.getInstance();
        AbstractLine line;
        int lineNumber = 0;

        try {
        	AbstractLineReader reader  = ioProvider.getLineReader(
                    Constants.IO_TEXT_LINE, ICopybookDialects.FMT_INTEL,
                    CopybookLoader.SPLIT_NONE,  Cb2xmlConstants.USE_STANDARD_COLUMNS, 
                    copybookName, vendorFile
            );

            System.out.println("  Vendor \t Name");
            System.out.println("  ===========================================");


            while ((line = reader.read()) != null) {
                lineNumber += 1;
                    System.out.println(
                            "  "    + line.getFieldValue("Vendor-Number").asString()
                          + "  \t " + line.getFieldValue("Vendor-Name").asString());

            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error Line " + lineNumber + " " + e.getMessage());
            System.out.println("      File " + vendorFile);
            System.out.println();
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("Lines Read " + lineNumber);
    }


    /**
     * LineIO example
     *
     * @param args program arguments
     */
    public static void main(String[] args) {
        new XmplLineIO1();
    }
}
