package Exceptions;

/**
 *Exception that should be thrown when the specified image type does not match any valid image type.
 */
public class InvalidImageTypeException extends Exception {

    private String inValidType;

    /**
     * @param inValidType the invalid type string
     */
    public InvalidImageTypeException(String inValidType){
        this.inValidType = inValidType;
    }

    /**
     * @return the invalid type string.
     */
    public String getInValidType() {
        return inValidType;
    }
}
