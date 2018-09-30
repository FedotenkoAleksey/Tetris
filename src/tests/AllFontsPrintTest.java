package tests;

import java.awt.GraphicsEnvironment;

/**
 * The following class is a practice test which prints out all the fonts 
 * available on the current system. 
 * @author ALEXXX
 */
class AllFontsPrintTest {
    public static void main(String[] args) {
        
        // the statement writes all the available fonts on the current system to array
        String [] fonts = 
            GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        
        // print all the available fonts on current system
        for (int i = 0; i < fonts.length; i++) {
            System.out.println(fonts[i]);
        }
    }
}