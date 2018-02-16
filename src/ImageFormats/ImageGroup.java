package ImageFormats;

import ImageFormats.Image;

import java.util.ArrayList;


/**
 * Class to store images in a group and calculate total storage size in bytes.
 */
public class ImageGroup {

    private ArrayList<Image> imageList = new ArrayList<>();

    /**
     * @param image to be added to group
     * @return return true if image added successfully
     */
    public boolean addImage(Image image){
        if(!image.isInGroup()){
            imageList.add(image);
            image.setInGroup(true);
            return true;
        }
        return false;
    }

    /**
     * @return total storage size of the group in bytes.
     */
    public int getSize(){
        int totalSize = 0;
        for (int i = 0; i < imageList.size(); i++) {
            totalSize += imageList.get(i).calculateSize();
        }
        return  (int) (totalSize / Math.log( imageList.size()+3));
    }
}
