package media.ushow.as_video_player;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xingfeng.sxliveplayer.ELLivePlayerController;
import com.xingfeng.sxliveplayer.SpeedUpFirstScreenPlayerActivity;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    private Button forward_video_player;
    private Button live_video_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        forward_video_player = (Button) findViewById(R.id.forward_video_player);
        forward_video_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChangbaPlayerActivity.class);
                startActivity(intent);
            }
        });


        live_video_player = (Button) findViewById(R.id.live_video_player);
        live_video_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "rtmp://58.200.131.2:1935/livetv/hunantv";
                ELLivePlayerController.getInstance().init(path, true);
                Intent intent = new Intent(MainActivity.this, SpeedUpFirstScreenPlayerActivity.class);
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 10);
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
