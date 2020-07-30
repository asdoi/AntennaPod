package de.danoeh.antennapod.fragment.preferences;

import android.app.Activity;
import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import de.danoeh.antennapod.R;
import de.danoeh.antennapod.activity.PreferenceActivity;
import de.danoeh.antennapod.dialog.SkipPreferenceDialog;
import de.danoeh.antennapod.dialog.VariableSpeedDialog;

public class PlaybackControlPreferencesFragment extends PreferenceFragmentCompat {
    private static final String PREF_PLAYBACK_SPEED_LAUNCHER = "prefPlaybackSpeedLauncher";
    private static final String PREF_PLAYBACK_REWIND_DELTA_LAUNCHER = "prefPlaybackRewindDeltaLauncher";
    private static final String PREF_PLAYBACK_FAST_FORWARD_DELTA_LAUNCHER = "prefPlaybackFastForwardDeltaLauncher";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences_playback_control);
        setupPlaybackControlScreen();
    }

    @Override
    public void onStart() {
        super.onStart();
        ((PreferenceActivity) getActivity()).getSupportActionBar().setTitle(R.string.playback_control);
    }

    private void setupPlaybackControlScreen() {
        final Activity activity = getActivity();

        findPreference(PREF_PLAYBACK_SPEED_LAUNCHER).setOnPreferenceClickListener(preference -> {
            new VariableSpeedDialog().show(getChildFragmentManager(), null);
            return true;
        });
        findPreference(PREF_PLAYBACK_REWIND_DELTA_LAUNCHER).setOnPreferenceClickListener(preference -> {
            SkipPreferenceDialog.showSkipPreference(activity, SkipPreferenceDialog.SkipDirection.SKIP_REWIND, null);
            return true;
        });
        findPreference(PREF_PLAYBACK_FAST_FORWARD_DELTA_LAUNCHER).setOnPreferenceClickListener(preference -> {
            SkipPreferenceDialog.showSkipPreference(activity, SkipPreferenceDialog.SkipDirection.SKIP_FORWARD, null);
            return true;
        });
    }
}
