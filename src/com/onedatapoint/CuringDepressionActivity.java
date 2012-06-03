package com.onedatapoint;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.onedatapoint.config.Config;
import com.onedatapoint.model.Question;

public class CuringDepressionActivity extends Activity
{
    //private final static String LOGTAG = "onedatapoint";
    //private final static String questionFileLocation = "/sdcard/onedatapoint-questions.xml";

    private Iterable<Question> questions;
    //private Vector<View> questionViews;
    private boolean canExit = true;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        questions = Config.getInstance().getQuestionRepository().getQuestions();
        //questionViews = new Vector<View>();

        //loadQuestions(questionFileLocation);
        //createQuestionViews();

        setContentView(R.layout.home);
    }


    // Home screen button onClick handlers
    public void openJournal(View v) {
        canExit = false;
        setContentView(R.layout.journal);
    }
    public void openReview(View v) {
        canExit = false;
        setContentView(R.layout.review);
    }
    public void openMedicine(View v) {
        canExit = false;
        setContentView(R.layout.medicine);
    }
    public void openGraphs(View v) {
        canExit = false;
        setContentView(R.layout.graphs);
    }

    public void saveAndGoHome(View v) {
        canExit = true;
        setContentView(R.layout.home);
    }

    public void saveAndQuit(View v) {
        moveTaskToBack(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (canExit) {
                moveTaskToBack(true);
                return true;
            }

            canExit = true;
            setContentView(R.layout.home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /*
    private void createQuestionViews() {
        for (Question question : questions) {
            View questionView = new View(this);
            // Create respective View object
            if (question.type.equals("graph"))
                // Instantiate View
                break;
            else if (question.type.equals("slider"))
                // Instantiate View
                break;
            else if (question.type.equals("buttons"))
                // Instantiate View
                break;
            questionViews.add(questionView);
        }
    }
    */

    private void showQuestions() {
        setContentView(R.layout.main);

        TextView text = (TextView)findViewById(R.id.xmlText);
        for (Question question : questions) {
            text.append("\n" + question.toString());
        }
    }
}
