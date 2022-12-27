import org.bytedeco.javacv.*;
import org.bytedeco.opencv.opencv_core.IplImage;

import java.io.File;

//import static org.bytedeco.opencv.global.opencv_core.cvFlip;
import static org.bytedeco.opencv.helper.opencv_imgcodecs.cvSaveImage;

public class Test implements Runnable {
    CanvasFrame canvas = new CanvasFrame("Web Cam");

    public Test() {
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }

    public void run() {

        new File("G:/My Drive/12 Computer Science/Connect 4 AI/Connect 4 Bot Final/images").mkdir();

        try (FrameGrabber grabber = new OpenCVFrameGrabber(0)) {
            OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
            IplImage img;
            try {
                grabber.start();
                while (true) {
                    Frame frame = grabber.grab();

                    img = converter.convert(frame);

                    // the grabbed frame will be flipped, re-flip to make it right
                    //cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise
                    
                    // save
                    cvSaveImage("G:/My Drive/12 Computer Science/Connect 4 AI/Connect 4 Bot Final/images" + File.separator + "board.jpg", img);

                    canvas.showImage(converter.convert(img));

                    // canvas.setVisible(false); //you can't see me!
                    // canvas.dispose(); //Destroy the JFrame object
                    // grabber.close();
                    Thread.sleep(500);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Test gs = new Test();
        Thread th = new Thread(gs);
        th.start();
    }
}
