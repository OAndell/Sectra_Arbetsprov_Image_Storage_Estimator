package ImageFormats;

/**
 * Format for jpeg 2000 images. Extends Image.
 *@see Image
 */
public class JPEG2000Image extends Image {

    public JPEG2000Image(int width, int height) {
        super(width, height);
    }

    @Override
    public int calculateSize() {
        int width = this.getWidth();
        int height = this.getHeight();
        return (int)  ( (width*height*0.4) / (Math.log(Math.log(width*height + 16))));
    }

}
