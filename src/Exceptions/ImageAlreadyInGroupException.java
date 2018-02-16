package Exceptions;

/**
 *Exception that should be thrown when an image cannot be added to a imageGroup because it already belongs to an ImageGroup.
 *@see ImageFormats.Image
 *@see ImageFormats.ImageGroup
 */
public class ImageAlreadyInGroupException extends Exception {

    private int invalidIndex;

    /**
     * @param invalidIndex the index for the image already in a group.
     */
    public ImageAlreadyInGroupException(int invalidIndex){
        this.invalidIndex = invalidIndex;
    }

    /**
     * @return the index of the image that already belonged to a group.
     */
    public int getInvalidIndex() {
        return invalidIndex;
    }
}
