package br.com.appdog.util;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import br.com.appdog.view.activity.LoginActivity;
import br.com.appdog.viewmodel.MainViewModel;

public class DialogUtil {

    /**
     * method responsible for creating the exit application dialog.
     * @param activity
     * @param resourceLayout
     * @param inflater
     * @param positiveButton
     * @param negativeButton
     * @param viewModel
     */
    public static void showDialogLogout(final Activity activity, final int resourceLayout,
                                        final LayoutInflater inflater, final String positiveButton,
                                        final String negativeButton, final MainViewModel viewModel) {
        final View alertLayout = inflater.inflate(resourceLayout, null);

        final AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setPositiveButton(positiveButton, (dialog, which) -> {
            viewModel.clearToken();
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            activity.finish();


        });

        alert.setNegativeButton(negativeButton, (dialogInterface, i) -> dialogInterface.dismiss());


        final AlertDialog dialog = alert.create();
        dialog.show();

    }
}
