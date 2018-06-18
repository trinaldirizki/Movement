package movement.com.movement.util;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

/**
 * Created by isma-ilou on 17.06.2018.
 */

public class ScreenNavigator {

    public static void navigateTo(Context context, Class<?> activity) {
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
    }

    public static void navigateTo(Context context, Class<?> activity, int flags) {
        Intent intent = new Intent(context, activity);
        intent.setFlags(flags);
        context.startActivity(intent);
    }

    public static void navigateTo(Context context, Class<?> activity, int flags, String key, Parcelable parcelable) {
        Intent intent = new Intent(context, activity);
        intent.setFlags(flags);
        intent.putExtra(key, parcelable);
        context.startActivity(intent);
    }
}
