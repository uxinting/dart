package xinting.dart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DartActivity extends Activity {

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_dart );
		
		Button btn = (Button) findViewById( R.id.start_practice );
		
		btn.setOnClickListener( new StartPracticeClickListener() );
	}
	
	private class StartPracticeClickListener implements OnClickListener {

		@Override
		public void onClick( View v ) {
			Intent intent = new Intent();
			
			intent.setClass( getBaseContext(), YardActivity.class );
			
			startActivity( intent );
		}
		
	}
}
