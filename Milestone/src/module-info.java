/**
 * 
 */
/**
 * @author vgfrb
 *
 */
module Milestone {
	// Export the 'inventory' package to the Jackson modules
    exports inventory to com.fasterxml.jackson.databind;

    // Require the necessary Jackson modules
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;

	
}