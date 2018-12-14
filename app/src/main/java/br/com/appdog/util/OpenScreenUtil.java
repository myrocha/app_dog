package br.com.appdog.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class OpenScreenUtil {

    public static void openScreen(final Context context, final String action, final Bundle bundle, final boolean clearTask) {
        final Intent intent = new Intent(action);
        if (clearTask) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
}
