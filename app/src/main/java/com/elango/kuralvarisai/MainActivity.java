package com.elango.kuralvarisai;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,helperTV;
    Button scoreButton,helpButton;
    Button refreshImageButton;
    int score =0;
    String kuralText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        init();

        refreshImageButton = (Button) findViewById(R.id.refreshXMLB);
        refreshImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                init();
            }
        });




// This defines your touch listener

    }

    private void init(){
        Resources res = getResources();
        String[] kuralArray = res.getStringArray(R.array.kural_array);
        kuralText = returnRandom(kuralArray);

        Log.v(TAG, "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        Log.v(TAG, kuralText);
        String[] kuralWords = kuralText.split("\\s+");
        Log.v(TAG, "size ="+kuralWords.length);

        kuralWords = shuffleElements(kuralWords);

        for(int i=0;i<kuralWords.length;i++){
            Log.v(TAG, i+kuralWords[i]);

        }

        tv1= findViewById(R.id.cell1);
        ///tv1.setText(kuralWords[0]);
        tv1.setOnTouchListener(new StartTouchListener());
        tv1.setOnDragListener(new DragDropListener());

        tv2= findViewById(R.id.cell2);
        //tv2.setText(kuralWords[1]);
        tv2.setOnTouchListener(new StartTouchListener());
        tv2.setOnDragListener(new DragDropListener());

        tv3= findViewById(R.id.cell3);
        //tv3.setText(kuralWords[2]);
        tv3.setOnTouchListener(new StartTouchListener());
        tv3.setOnDragListener(new DragDropListener());

        tv4= findViewById(R.id.cell4);
        //tv4.setText(kuralWords[3]);
        tv4.setOnTouchListener(new StartTouchListener());
        tv4.setOnDragListener(new DragDropListener());


        tv5= findViewById(R.id.cell5);
        //tv5.setText(kuralWords[4]);
        tv5.setOnTouchListener(new StartTouchListener());
        tv5.setOnDragListener(new DragDropListener());

        tv6= findViewById(R.id.cell6);
        // tv6.setText(kuralWords[5]);
        tv6.setOnTouchListener(new StartTouchListener());
        tv6.setOnDragListener(new DragDropListener());

        tv7= findViewById(R.id.cell7);
        //tv7.setText(kuralWords[6]);
        tv7.setOnTouchListener(new StartTouchListener());
        tv7.setOnDragListener(new DragDropListener());

        //tv8= findViewById(R.id.cell8);
        //tv8.setOnTouchListener(new StartTouchListener());
        //tv8.setOnDragListener(new DragDropListener());
        scoreButton = findViewById(R.id.scoreXMLB);
        scoreButton.setText(Integer.toString(score));

        //set Text
        initTVTxt(kuralWords);


        tv1.setBackgroundColor(Color.parseColor("#03DAC6"));
        tv2.setBackgroundColor(Color.parseColor("#03DAC6"));
        tv3.setBackgroundColor(Color.parseColor("#03DAC6"));
        tv4.setBackgroundColor(Color.parseColor("#03DAC6"));
        tv5.setBackgroundColor(Color.parseColor("#03DAC6"));
        tv6.setBackgroundColor(Color.parseColor("#03DAC6"));
        tv7.setBackgroundColor(Color.parseColor("#03DAC6"));


        helpButton = (Button) findViewById(R.id.helpXMLB);
        helperTV= findViewById(R.id.helpXMLTV);
        helperTV.setText("*");

        helpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                helperTV.setText(getKuralText());

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        helperTV.setText(".");

                    }
                }, 3000);

            }
        });

    }

    private String getKuralText(){

        return this.kuralText;
    }



    private void lockViews (){


        tv1.setBackgroundColor(Color.parseColor("#75E900"));
        tv2.setBackgroundColor(Color.parseColor("#75E900"));
        tv3.setBackgroundColor(Color.parseColor("#75E900"));
        tv4.setBackgroundColor(Color.parseColor("#75E900"));
        tv5.setBackgroundColor(Color.parseColor("#75E900"));
        tv6.setBackgroundColor(Color.parseColor("#75E900"));
        tv7.setBackgroundColor(Color.parseColor("#75E900"));


        tv1.setOnTouchListener(null);
        tv2.setOnTouchListener(null);
        tv3.setOnTouchListener(null);
        tv3.setOnTouchListener(null);
        tv4.setOnTouchListener(null);
        tv5.setOnTouchListener(null);
        tv6.setOnTouchListener(null);
        tv7.setOnTouchListener(null);
    }

    private void help(){

    }

    private boolean  compareTVTxt() {

        StringBuffer str =new StringBuffer();
        str.append(tv1.getText()+" ");
        str.append(tv2.getText()+" ");
        str.append(tv3.getText()+" ");
        str.append(tv4.getText()+" ");
        str.append(tv5.getText()+" ");
        str.append(tv6.getText()+" ");
        str.append(tv7.getText());
        Log.v(TAG, "----TV Text  ="+str);
        int stringMatchResult = kuralText.compareToIgnoreCase(str.toString());
        Log.v(TAG, "----Comparison   ="+stringMatchResult);
        if(stringMatchResult==0) {
            score += 1;
            scoreButton.setText(Integer.toString(score));
            lockViews ();
            return true;
        }
        else
            return false;

    }

    private void  initTVTxt(String[] arr) {


    tv1.setText(arr[0]);
    tv2.setText(arr[1]);
    tv3.setText(arr[2]);
    tv4.setText(arr[3]);
    tv5.setText(arr[4]);
    tv6.setText(arr[5]);
    tv7.setText(arr[6]);



    }

    private static String[] shuffleElements(String[] array){

        List<String> list = Arrays.asList(array);
        Collections.shuffle(list);

        String[] arry = new String [list.size()];
        return list.toArray(arry);

    }

    private static String returnRandom(String[] arr){
        List<String> list = Arrays.asList(arr);
        Random rand = new Random();
        int i = rand.nextInt(arr.length);
        Log.v(TAG, "--"+i);
        return arr[i];

    }



    /**
     * ChoiceTouchListener will handle touch events on draggable views
     *
     */
    private final class StartTouchListener implements View.OnTouchListener {
        @SuppressLint("NewApi")
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                /*
                 * Drag details: we only need default behavior
                 * - clip data could be set to pass data as part of drag
                 * - shadow can be tailored
                 */
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * DragListener will handle dragged views being dropped on the drop area
     * - only the drop action will have processing added to it as we are not
     * - amending the default behavior for other parts of the drag process
     *
     */
    @SuppressLint("NewApi")
    private class DragDropListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    //Toast.makeText(MainActivity.this, "ACTION_DRAG_ENTERED", Toast.LENGTH_SHORT).show();
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    //Toast.makeText(MainActivity.this, "ACTION_DRAG_EXITED", Toast.LENGTH_SHORT).show();
                    break;
                case DragEvent.ACTION_DROP:

                    //Toast.makeText(MainActivity.this, "ACTION_DROP", Toast.LENGTH_SHORT).show();
                    //handle the dragged view being dropped over a drop view
                    View view = (View) event.getLocalState();
                    //stop displaying the view where it was before it was dragged
                    //view.setVisibility(View.INVISIBLE);
                    //view dragged item is being dropped on
                    TextView targetTV = (TextView) v;
                    //view being dragged and dropped
                    TextView sourceTV = (TextView) view;

                    CharSequence sourceTxt = sourceTV.getText();
                    CharSequence targetTxt  = targetTV.getText();

                    Object targetTag = targetTV.getTag();
                    Object sourceTag = sourceTV.getTag();
                    //////
                    //update the text in the target view to reflect the data being dropped
                    targetTV.setText(sourceTxt);
                    //make it bold to highlight the fact that an item has been dropped
                    //targetTV.setTypeface(Typeface.DEFAULT_BOLD);
                    //if an item has already been dropped here, there will be a tag


                    //if there is already an item here, set it back visible in its original place
                    if(targetTag!=null)
                    {
                        //the tag is the view id already dropped here
                        int existingID = (Integer)targetTag;
                        //set the original view visible again
                        findViewById(existingID).setVisibility(View.VISIBLE);
                    }
                    //set the tag in the target view being dropped on - to the ID of the view being dropped
                    //targetTV.setTag(sourceTV.getId());
                    //NEWWWW-------
                    sourceTV.setText(targetTxt);
                    //make it bold to highlight the fact that an item has been dropped
                    //sourceTV.setTypeface(Typeface.DEFAULT_BOLD);
                    //if an item has already been dropped here, there will be a tag
                    //if there is already an item here, set it back visible in its original place
                    if(sourceTag!=null)
                    {
                        //the tag is the view id already dropped here
                        int existingID = (Integer)sourceTag;
                        //set the original view visible again
                        findViewById(existingID).setVisibility(View.VISIBLE);
                    }
                    //set the tag in the target view being dropped on - to the ID of the view being dropped
                    sourceTV.setTag(targetTV.getId());

                    compareTVTxt();


                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    //Toast.makeText(MainActivity.this, "ACTION_DRAG_ENDED", Toast.LENGTH_SHORT).show();

                    break;
                default:
                    break;
            }
            return true;
        }
    }



}