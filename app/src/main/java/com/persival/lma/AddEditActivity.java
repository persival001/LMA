package com.persival.lma;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.persival.lma.databinding.ActivityAddEditBinding;
import com.persival.lma.model.Course;

public class AddEditActivity extends AppCompatActivity {

    public static final String COURSE_ID = "courseId";
    public static final String COURSE_NAME = "courseName";
    public static final String UNIT_PRICE = "unitPrice";
    private Course course;
    private ActivityAddEditBinding activityAddEditBinding;
    private AddAndEditActivityClickHandlers clickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        course = new Course();
        activityAddEditBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_add_edit);
        activityAddEditBinding.setCourse(course);


        clickHandlers = new AddAndEditActivityClickHandlers(this);
        activityAddEditBinding.setClickHandler(clickHandlers);


        Intent i = getIntent();
        if (i.hasExtra(COURSE_ID)) {
            // RecyclerView item Clicked

            setTitle("Edit Course");
            course.setCourseName(i.getStringExtra(COURSE_NAME));
            course.setUnitPrice(i.getStringExtra(UNIT_PRICE));

        } else {

            // FAB is Clicked
            setTitle("Create New Course");
        }


    }


    public class AddAndEditActivityClickHandlers {
        Context context;

        public AddAndEditActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void onSubmitButtonClicked(View view) {
            if (course.getCourseName() == null) {
                Toast.makeText(context, "Course Name is Empty", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent();
                i.putExtra(COURSE_NAME, course.getCourseName());
                i.putExtra(UNIT_PRICE, course.getUnitPrice());
                setResult(RESULT_OK, i);
                Log.v("TAG", course.getCourseName());
                finish();
            }
        }
    }
}