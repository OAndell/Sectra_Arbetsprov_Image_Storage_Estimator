import Exceptions.ImageAlreadyInGroupException;
import Exceptions.InvalidImageTypeException;
import ImageFormats.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *SECTRA programming assignment - The Image Storage Estimator
 *@author Oscar Andell
 */
public class ImageStorageEstimator {

    private static String[] VALID_INPUT_JPEG = {"J","JPG"};
    private static String[] VALID_INPUT_JPEG2000 = {"JP2","JPEG2000"};
    private static String[] VALID_INPUT_BMP = {"BMP"};

    private static String COMMAND_QUIT = "q";
    private static String COMMAND_GROUP = "G";

    private static ArrayList<Image> images = new ArrayList<>();
    private static ArrayList<ImageGroup> imageGroups = new ArrayList<>();


    public static void main(String[] args){
        printHeader();
        getInput();
        printResults();
    }

    private static void printHeader(){
        System.out.println("Storage calculator by Oscar Andell. "
                + "Enter one line for each image/group on the format \"type width height\", "
                + "or \"G i, i, ...\". Exit with \"Q\".");
    }

    private static void getInput(){
        Scanner in = new Scanner(System.in);
        boolean inputPhase = true;
        while (inputPhase){
            try{
                String[] inputArray = in.nextLine().split(" ");
                if(inputArray[0].equals(COMMAND_QUIT)){
                    inputPhase=false;
                }
                else if(inputArray[0].equals(COMMAND_GROUP)){
                    int groupIndexes[] = new int[inputArray.length-1];
                    for (int i = 0; i < groupIndexes.length; i++) {
                        groupIndexes[i] = Integer.parseInt(inputArray[i+1]);
                    }
                    createGroup(groupIndexes);
                }
                else{
                    String imageType = inputArray[0];
                    int width = Integer.parseInt(inputArray[1]);
                    int height = Integer.parseInt(inputArray[2]);
                    createImage(imageType,width,height);
                }
            }
            catch (ImageAlreadyInGroupException alreadyInGroupException){
                System.out.println("Group was not created since image " + alreadyInGroupException.getInvalidIndex()
                        + " already belongs to a another group");
            }
            catch (InvalidImageTypeException invalidTypeException){
                System.out.println("\"" + invalidTypeException.getInValidType() + "\" is not a valid image type");
            }catch (Exception e){
                //Handle general exceptions, such as wrong format/data types.
                System.out.println("Invalid input. Please use the format \"type width height\"");
            }
        }
    }

    /**
     * Create a new Image object with the specified parameters.
     * @throws InvalidImageTypeException throws exception if the specified image type is invalid.
     */
    private static void createImage(String imageType, int width, int height) throws InvalidImageTypeException {
        if(Arrays.asList(VALID_INPUT_JPEG).contains(imageType)){
            images.add(new JPEGImage(width,height));
        }
        else if(Arrays.asList(VALID_INPUT_JPEG2000).contains(imageType)){
            images.add(new JPEG2000Image(width,height));
        }
        else if(Arrays.asList(VALID_INPUT_BMP).contains(imageType)){
            images.add(new BMPImage(width,height));
        }
        else{
            throw new InvalidImageTypeException(imageType);
        }
    }

    /**
     * Creates a group object with the images in the specified indexes
     * @throws ImageAlreadyInGroupException throws exception if image is already in a group.
     */
    private static void createGroup(int[] indexes) throws ImageAlreadyInGroupException {
        ImageGroup imageGroup = new ImageGroup();
        for (int i = 0; i < indexes.length; i++) {
            if(!imageGroup.addImage(images.get(indexes[i]-1))){
                throw new ImageAlreadyInGroupException(indexes[i]);
            }
        }
        imageGroups.add(imageGroup);
    }

    private static void printResults(){
        int totalSize = 0;
        for (int i = 0; i < imageGroups.size(); i++) {
            totalSize += imageGroups.get(i).getSize();
        }
        for (int i = 0; i < images.size(); i++) {
            if(!images.get(i).isInGroup()){
                totalSize += images.get(i).calculateSize();
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        System.out.println("Total size: " + decimalFormat.format(totalSize) + " bytes");
    }
}
