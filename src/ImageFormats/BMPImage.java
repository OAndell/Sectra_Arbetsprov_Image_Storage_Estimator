package ImageFormats;

/**
 * Format for uncompressed bitmaps. Extends Image.
 *@see Image
 */
public class BMPImage extends Image {

    public BMPImage(int width, int height) {
        super(width, height);
    }

    @Override
    public int calculateSize() {
        int height = this.getHeight();
        int width = this.getWidth();
        int totalSize = 0;
        while (height >= 128 && width >= 128){
            totalSize += height*width;
            height *= 0.5;
            width *= 0.5;
        }
        return totalSize;
    }
}
