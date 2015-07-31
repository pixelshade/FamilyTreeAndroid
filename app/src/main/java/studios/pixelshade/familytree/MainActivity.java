package studios.pixelshade.familytree;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.VideoView;

import java.util.Date;
import java.util.LinkedList;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{
    static final int REQUEST_VIDEO_CAPTURE = 1;
    static final String TAG = "MainActivity";

    private VideoView mVideoView;
    private GridView mFamilyPicker;

    private LinkedList<Person> taggedInVideo;
    private String nameVideo;
    private String infoVideo;
    private Date dateOfVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinkedList<Person> family = new LinkedList<>();

        FamilyArrayAdapter familyArrayAdapter = new FamilyArrayAdapter(this,R.layout.person_cell_layout,family);

        mVideoView = (VideoView) findViewById(R.id.videoView);
        mFamilyPicker = (GridView) findViewById(R.id.familyPicker);
        mFamilyPicker.setOnItemClickListener(this);
        mFamilyPicker.setAdapter(familyArrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void onCaptureVideoBtnClick(View view){
        dispatchTakeVideoIntent();
    }



    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = intent.getData();
            mVideoView.setVideoURI(videoUri);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }



}
