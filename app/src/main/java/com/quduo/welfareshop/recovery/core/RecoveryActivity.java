package com.quduo.welfareshop.recovery.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.quduo.welfareshop.recovery.tools.RecoverySharedPrefsUtil;
import com.quduo.welfareshop.recovery.tools.RecoveryUtil;

import java.util.ArrayList;

/**
 * Created by zhengxiaoyong on 16/8/26.
 */
public final class RecoveryActivity extends AppCompatActivity {

    public static final String RECOVERY_MODE_ACTIVE = "recovery_mode_active";

    private boolean isDebugModeActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.e(getExceptionData().toString());
        boolean restart = RecoverySharedPrefsUtil.shouldRestartApp();
        if (restart)
            RecoverySharedPrefsUtil.clear();
        Toast.makeText(this, "发生未知错误，程序即将重启", Toast.LENGTH_SHORT).show();
        restart();
    }


    private RecoveryStore.ExceptionData getExceptionData() {
        return getIntent().getParcelableExtra(RecoveryStore.EXCEPTION_DATA);
    }

    private String getCause() {
        return getIntent().getStringExtra(RecoveryStore.EXCEPTION_CAUSE);
    }

    private String getStackTrace() {
        return getIntent().getStringExtra(RecoveryStore.STACK_TRACE);
    }

    private void restart() {
        Intent launchIntent = getApplication().getPackageManager().getLaunchIntentForPackage(this.getPackageName());
        if (launchIntent != null) {
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(launchIntent);
            overridePendingTransition(0, 0);
        }
        finish();
    }

    private void recoverTopActivity() {
        Intent intent = getRecoveryIntent();
        if (intent != null && RecoveryUtil.isIntentAvailable(this, intent)) {
            intent.setExtrasClassLoader(getClassLoader());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra(RECOVERY_MODE_ACTIVE, true);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
            return;
        }
        restart();
    }

    private boolean isRecoverStack() {
        boolean hasRecoverStack = getIntent().hasExtra(RecoveryStore.RECOVERY_STACK);
        return !hasRecoverStack || getIntent().getBooleanExtra(RecoveryStore.RECOVERY_STACK, true);
    }

    private void recoverActivityStack() {
        ArrayList<Intent> intents = getRecoveryIntents();
        if (intents != null && !intents.isEmpty()) {
            ArrayList<Intent> availableIntents = new ArrayList<>();
            for (Intent tmp : intents) {
                if (tmp != null && RecoveryUtil.isIntentAvailable(this, tmp)) {
                    tmp.setExtrasClassLoader(getClassLoader());
                    availableIntents.add(tmp);
                }
            }
            if (!availableIntents.isEmpty()) {
                availableIntents.get(0).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                availableIntents.get(availableIntents.size() - 1).putExtra(RECOVERY_MODE_ACTIVE, true);
                startActivities(availableIntents.toArray(new Intent[availableIntents.size()]));
                overridePendingTransition(0, 0);
                finish();
                return;
            }
        }
        restart();
    }

    private Intent getRecoveryIntent() {
        boolean hasRecoverIntent = getIntent().hasExtra(RecoveryStore.RECOVERY_INTENT);
        if (!hasRecoverIntent)
            return null;
        return getIntent().getParcelableExtra(RecoveryStore.RECOVERY_INTENT);
    }

    private ArrayList<Intent> getRecoveryIntents() {
        boolean hasRecoveryIntents = getIntent().hasExtra(RecoveryStore.RECOVERY_INTENTS);
        if (!hasRecoveryIntents)
            return null;
        return getIntent().getParcelableArrayListExtra(RecoveryStore.RECOVERY_INTENTS);
    }

    private void killProcess() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && isDebugModeActive) {
            isDebugModeActive = false;
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        killProcess();
    }

}
