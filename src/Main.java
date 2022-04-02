import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Main
{
    public static void main(String[] args) 
    {
        Random rand = new Random();

        int packSize = 0;

        Vector<Path> packPaths = new Vector<Path>();
        Vector<Integer> avaliblePaths = new Vector<Integer>();

        Vector<BufferedImage> packImages = new Vector<BufferedImage>();
        Vector<Integer> avalibleImgs = new Vector<Integer>();

        HashMap<Path, BufferedImage> newPack = new HashMap<Path, BufferedImage>();

        File path = null;

        ErrorHandler.Assert(args.length == 1, "Program must have one argument", true);

        try { path = new File(args[0]); } 
        catch (Exception e) { ErrorHandler.Error(e.toString(), true); }

        ErrorHandler.Assert(path.isDirectory(), "Program argument must be a directory", true);

        for (File file : path.listFiles())
        {
            try 
            { 
                packPaths.add(file.toPath());
                packImages.add(ImageIO.read(file));
                packSize++;
            }
            catch (Exception e) { ErrorHandler.Error(e.toString(), false); }
        }

        if (packSize > 1)
        {
            for (int i = 0; i < packSize; i++)
            {
                avalibleImgs.add(i);
                avaliblePaths.add(i);
            }

            for (int i = 0; i < packSize; i++)
            {
                int pathIdx = 0;
                int imgIdx = 0;

                while (pathIdx == imgIdx)
                {           
                    pathIdx = rand.nextInt(avaliblePaths.size());
                    imgIdx = rand.nextInt(avalibleImgs.size());
                }

                avaliblePaths.remove(pathIdx);
                avalibleImgs.remove(imgIdx);

                newPack.put(packPaths.get(pathIdx), packImages.get(imgIdx));
            }
        }

        newPack.forEach((key, value) -> 
        {
            try { ImageIO.write(value, "png", key.toFile()); }
            catch (Exception e) { ErrorHandler.Error("Could not save new image to " + key.toString() + "\n", false); }
        });

        return;
    }
}