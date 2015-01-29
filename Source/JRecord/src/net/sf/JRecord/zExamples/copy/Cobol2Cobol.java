/*
 * @Author Bruce Martin
 * Created on 26/01/2006
 *
 * Purpose:
 */
package net.sf.JRecord.zExamples.copy;

import net.sf.JRecord.Details.LayoutDetail;
import net.sf.JRecord.External.CopybookLoader;
import net.sf.JRecord.External.ExternalRecord;
import net.sf.JRecord.utilityClasses.Copy;
import net.sf.JRecord.utilityClasses.SchemaLoader;




/**
 * copy a "Cobol" data file to a CSV file (mathing fields names)
 * 
 * <p>At the moment (19-Oct-2014) this should be considered a Test program; 
 * so use it with <b>Caution</
 *
 * @author Bruce Martin
 *
 */
public class Cobol2Cobol {


    /**
     * convert a cobol file to a CSV file. It does this by <ul>
     *   <li> Loading the Cobol Copybook
     *   <li> Converting the Cobol Copybook to a Csv-Copybook
     *   <li> Calling a Standard Copy Method (using input / Output Layouts)
     * </ul>
     * 
     * @param arguments program arguments
     */
    public static void main(String[] arguments) { 

		try {
		    LayoutDetail inputLayout, ouputtLayout;
		    ExternalRecord inSschema, outSchema;
		   
		    ParseArgsCobol2Cobol copyArgs = new ParseArgsCobol2Cobol(arguments);

		    if (copyArgs.infilePresent) {
		    			// Load the Cobol Copybook and set the file-structure (supplied as an input parameter 
		        inSschema = SchemaLoader.loadSchema(copyArgs.inCopybookName, CopybookLoader.SPLIT_NONE, copyArgs.inFont, copyArgs.inBinFormat);
		        inSschema.setFileStructure(copyArgs.inFileStructure);
		        outSchema = SchemaLoader.loadSchema(copyArgs.outCopybookName, CopybookLoader.SPLIT_NONE, copyArgs.outFont, copyArgs.outBinFormat);
		        outSchema.setFileStructure(copyArgs.outFileStructure);
		        		        
		        inputLayout = inSschema.asLayoutDetail();     // Create the input Cobol layout
				
				ouputtLayout = outSchema.asLayoutDetail();       // Create the output Cobol Layout
				                                         
				                                           // Since we are sure the field number's match
				                                           // in the input/output files
				                                           // lets copy and match by field number
				Copy.copyFileByFieldNumber(inputLayout, copyArgs.infile, ouputtLayout, copyArgs.outfile);
				                                           // This method sets
				                                           //    Output_Field[i] = Input_Field[i]
				                                           // for all the output fields
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
