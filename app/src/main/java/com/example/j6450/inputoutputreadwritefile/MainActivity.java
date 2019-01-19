package com.example.j6450.inputoutputreadwritefile;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity
{

    Context context = null;

    TextView infoText;
    EditText inputOutputText;
    EditText fileText;
    CharSequence textBuffer;
    //CharSequence fileText;
    String fileName = null;
    boolean nameGiven = false;
    boolean timerRunOut = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        System.out.println("kansion sijainti: " + context.getFilesDir());
        infoText = (TextView) findViewById(R.id.textView);
        inputOutputText = (EditText) findViewById(R.id.editText);
        fileText = (EditText) findViewById(R.id.editText2);
        enterFileName();
    }

    //
    public void enterFileName ()
    {
        infoText.setText("Give filename");
        /*
        inputOutputText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        inputOutputText.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (keyCode == KeyEvent.KEYCODE_ENTER)
                {
                    fileName = inputOutputText.getText().toString();
                    return nameGiven = true;
                }

                return nameGiven = false;
            }
        });
        */

    }


    // load/ read existing file
    public void loadFile (View v)
    {

        try
        {
            textBuffer = inputOutputText.getText();
            fileName = fileText.getText().toString();
            InputStream fileLoad = context.openFileInput(fileName);
            String fileLoadStrig = "";
            BufferedReader inputBuff = new BufferedReader(new InputStreamReader(fileLoad));

            while ((fileLoadStrig = inputBuff.readLine()) != null)
            {
                inputOutputText.append(fileLoadStrig);
            }

            fileLoad.close();
        }
        catch(IOException e)
        {
            Log.e("IOexception","Syötevirhe");
        }
        finally
        {
            infoText.setText("File read");
        }

    }

    // save / write file
    public void saveFile (View v)
    {

        try
        {
            textBuffer = inputOutputText.getText();
            fileName = fileText.getText().toString();
            OutputStreamWriter saveFile = new OutputStreamWriter(context.openFileOutput(fileName, context.MODE_PRIVATE));
            String saveFileString = "";

            saveFile.write(textBuffer.toString());

            saveFile.close();


        }
        catch(IOException e)
        {
            Log.e( "IOexception",  "Syötevirhe");
        }
        finally
        {
            infoText.setText("File saved");
        }
    }

}
