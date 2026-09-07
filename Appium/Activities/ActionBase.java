package activities;
import java.time.Duration;
import java.util.Arrays;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;
import io.appium.java_client.AppiumDriver;
public class ActionBase {
	private final PointerInput finger = new PointerInput(Kind.TOUCH,"finger");
	
	public void doSwipe(AppiumDriver driver,int duration, Point start , Point end) {
		
		Sequence swipe = new Sequence(finger,1);
		
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), Origin.viewport(), start.getX(),start.getY()));
		swipe.addAction(finger.createPointerDown(0)); //0 --> Left Button
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(duration), Origin.viewport(), end.getX(),end.getY()));
		swipe.addAction(finger.createPointerUp(0));
		
		driver.perform(Arrays.asList(swipe));
	}
	
	
	public void doLongPress(AppiumDriver driver) {
		
		Sequence swipe = new Sequence(finger,1);
		
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), Origin.viewport(),162,504));
		swipe.addAction(finger.createPointerDown(0)); //0 --> Left Button
		swipe.addAction(new Pause(finger,Duration.ofSeconds(1)));
		swipe.addAction(finger.createPointerUp(0));
		
		driver.perform(Arrays.asList(swipe));
	}
}
