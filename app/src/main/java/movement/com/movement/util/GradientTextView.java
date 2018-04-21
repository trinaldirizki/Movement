package movement.com.movement.util;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import movement.com.movement.R;

/**
 * Created by isma-ilou on 22.04.2018.
 */

public class GradientTextView extends android.support.v7.widget.AppCompatTextView {
    public GradientTextView(Context context) {
        super(context);
    }

    public GradientTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            getPaint().setShader(new LinearGradient(
                    0,
                    0,
                    0,
                    getHeight(),
                    ContextCompat.getColor(getContext(), R.color.colorPrimary),
                    ContextCompat.getColor(getContext(), R.color.colorAccent),
                    Shader.TileMode.CLAMP));
        }
    }
}
