package xinting.dart;

import xinting.effect.Orientation;
import xinting.effect.Position;
import xinting.effect.ThreeVector;
import xinting.srv.Coordinate;
import xinting.srv.Dart;
import xinting.srv.Person;
import xinting.srv.Player;
import xinting.srv.DartBoard;
import xinting.view.YardView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class YardActivity extends Activity {
	
	private YardView yard = null;
	
	private Player player = null;
	private Dart dart = null;
	private DartBoard board = null;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_yard );
		
		yard = (YardView) findViewById( R.id.yard );
		
		Coordinate.setScreenX( new Orientation( 1, 0, 0 ) );
		Coordinate.setScreenY( new Orientation( 0, -1, 0 ) );
		Coordinate.setScreenZ( new Orientation( 0, 0, 1 ) );
		Coordinate.setLogicX( new Orientation( 1, 0, 0 ) );
		Coordinate.setLogicY( new Orientation( 0, 1, 0 ) );
		Coordinate.setLogicZ( new Orientation( 0, 0, 1 ) );
		
		player = new Person();
		player.setStand( new Position( 0.1, 0, 0 ) );
		
		dart = new Dart();
		
		board = new DartBoard();
		board.setPosition( new ThreeVector( 1.0, 1.73, 0 ) );
		board.setNormal( new ThreeVector( -1, 0, 0 ) );
		
		yard.Attach( player );
		yard.addPlayer( player );
		yard.setDart( dart );
		yard.setTarget( board );
		
		/*yard.setSystemUiVisibility( View.SYSTEM_UI_FLAG_LOW_PROFILE 
				| View.SYSTEM_UI_FLAG_FULLSCREEN
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION );*/
	}
}
