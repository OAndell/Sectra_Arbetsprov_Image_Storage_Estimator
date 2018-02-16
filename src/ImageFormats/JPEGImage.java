package ImageFormats;


/**
 * Format for base line jpeg images. Extends Image.
 *@see Image
 */
public class JPEGImage extends Image {

    public JPEGImage(int width, int height) {
        super(width, height);
    }

    @Override
    public int calculateSize() {
        int height = this.getHeight();
        int width = this.getWidth();
        int totalSize = 0;
        while (height >= 128 && width >= 128){
            totalSize += height*width*0.2;
            height *= 0.5;
            width *= 0.5;
        }
        return totalSize;
    }
}
