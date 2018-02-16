package ImageFormats;

/**
 * Abstract Image class.
 */
public abstract class Image {

    private int width;
    private int height;
    private boolean inGroup = false;

    /**
     * @param width in pixels
     * @param height in pixels
     */
    public Image(int width, int height){
        this.width = width;
        this.height = height;
    }

    /**
     * @return image height in pixels
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return image width in pixels
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return true if the image has been added to a group.
     */
    public boolean isInGroup(){
        return inGroup;
    }

    /**
     * @param inGroup Should be set to true when the image is added to a group.
     */
    public void setInGroup(boolean inGroup) {
        this.inGroup = inGroup;
    }

    /**
     * @return returns the storage size the image in bytes
     */
    public abstract int calculateSize();

}
