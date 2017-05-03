package com.gamik.wrapradiogroup;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;


public class WrapRadioGroup extends FlexboxLayout implements View.OnClickListener {
    private ArrayList<RadioButton> buttons = new ArrayList<>();
    private WrapRadioGroup.OnCheckedChangeListener listener;

    public WrapRadioGroup(Context context) {
        super(context);
        init();
    }

    public WrapRadioGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    void init() {
        setFlexWrap(FlexboxLayout.FLEX_WRAP_WRAP);
        setJustifyContent(FlexboxLayout.JUSTIFY_CONTENT_SPACE_BETWEEN);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            buttons.add((RadioButton) getChildAt(i));
        }

        for (RadioButton button : buttons) {
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        checkButton(view.getId());
        listener.onCheckedChanged(this, view.getId());
    }

    void checkButton(int id) {
        for (RadioButton button : buttons) {
            if (button.getId() != id) {
                button.setChecked(false);
            }
        }
    }

    public void check(int id) {
        for (RadioButton button : buttons) {
            if (button.getId() != id) {
                button.setChecked(true);
            } else {
                button.setChecked(false);
            }
        }
    }

    public int getCheckedRadioButtonId() {
        for (RadioButton button : buttons) {
            if (button.isChecked())
                return button.getId();
        }
        return -1;
    }

    public void clearCheck() {
        for (RadioButton button : buttons) {
            button.setChecked(false);
        }
    }

    public void setOnCheckedChangeListener(WrapRadioGroup.OnCheckedChangeListener listener) {
        this.listener = listener;
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(WrapRadioGroup radioGroup, int id);
    }
}
