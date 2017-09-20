package org.huihui.jnidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.sample_text);
        JniDemo jniDemo = new JniDemo();
        tv.setText(JniDemo.getStaticString());
        jniDemo.sort(jniDemo.c);
        tv.setText(jniDemo.c.toString());
        tv.setText(JniDemo.jniCallStaticMethod());
        tv.setText(jniDemo.jniCallMethod() + "");
        tv.setText(JniMap.hello());
    }
}
