package github.sangmin0602.schedule;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestUtils {

	public static boolean assertPropertyNotNull(Object obj) {
		return assertPropertyNotNull(obj, new String[0]);
	}
	
	public static boolean assertPropertyNotNull(Object obj, String [] excludedFieldNames){
	    if ( obj == null ) fail ( "Null object. Check parameter");
	    Class<?> cls = obj.getClass();
	    String clsName = cls.getName().substring(cls.getName().lastIndexOf(".")+1);
	    Field [] fields = cls.getDeclaredFields();
	    Method [] methods = cls.getDeclaredMethods();
	    String fName = "";
	    String methodName = "";
	    int idx = 0;
	    
	    skip:
	    for (int i = 0; i < fields.length; i++) {
	        try {
	            fName = fields[i].getName();
	            
	            for(idx = 0 ; idx < excludedFieldNames.length ; idx++) {
	                if ( excludedFieldNames[idx].equals(fName) )    continue skip;
	            }
	            
	            fields[i].setAccessible(true);
	            
	            if ( fields[i].get(obj) == null ) {
	            	fail ( "field [" +fName+ "] of " + clsName + " is null.");
	            }
	            /*methodName = "get" + fName.substring(0, 1).toUpperCase() + fName.substring(1);
	            for (int j = 0; j < methods.length; j++) {
	                if ( methods[j].getName().equals(methodName) ){
	                    try {
	                        Object value = methods[j].invoke(obj);
	                        assertNotNull ( "field <" +fName+ "> of " + clsName + " is null.", value );
	                    } catch (IllegalAccessException e) {
	                        fail (clsName + "." + methods[j].getName() + " is not public method.");
	                    } catch (InvocationTargetException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }*/
	        } catch (IllegalArgumentException e) {
	            e.printStackTrace();
	        } catch (IllegalAccessException e) {
				e.printStackTrace();
			} 
	    }
	    
	    return true;
	}

}
