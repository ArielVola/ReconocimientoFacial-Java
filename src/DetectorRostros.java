import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class DetectorRostros {
	private CascadeClassifier clasificador;
	 //clasificador = new CascadeClassifier("C:/Users/Ariel Vola/Documents/EclipseWorkplace/ReconociminetoFacial/opencv/sources/data/haarcascades/haarcascade_frontalface_alt.xml");
	public DetectorRostros() {
		//Se lee el archivo Haar que le permite a OpenCV detectar rostros frontales en una imagen
		clasificador = new CascadeClassifier("C:/Users/Ariel Vola/Documents/EclipseWorkplace/ReconociminetoFacial/opencv/sources/data/haarcascades/haarcascade_frontalface_alt.xml");
		if (clasificador.empty()) {
			System.out.println("Error de lectura.");
			return;
		} else {
			System.out.println("Detector de rostros leido.");
		}
	}
 
	public Mat detecta(Mat frameDeEntrada) {
		Mat mRgba = new Mat();
		Mat mGrey = new Mat();
		MatOfRect rostros = new MatOfRect();
		frameDeEntrada.copyTo(mRgba);
		frameDeEntrada.copyTo(mGrey);
		Imgproc.cvtColor(mRgba, mGrey, Imgproc.COLOR_BGR2GRAY);
		Imgproc.equalizeHist(mGrey, mGrey);
		clasificador.detectMultiScale(mGrey, rostros);
		System.out.println(String.format("Detectando %s rostros", rostros.toArray().length));
		for (Rect rect : rostros.toArray()) {
			//Se dibuja un rectángulo donde se ha encontrado el rostro
			Imgproc.rectangle(mRgba, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255, 0, 0));
 
		}
		return mRgba;
	}
}
