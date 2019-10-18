package com.hotpotsoft.hyperbird.android;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class AndroidLauncher extends AndroidApplication implements com.hotpotsoft.hyperbird.AdsController {
	private static final String BANNER_AD_UNIT_ID = "ca-app-pub-6601388206030043/9757099015";

	AdView bannerAd;
	private boolean isAdLoaded = false;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		setupAds();

		View gameView = initializeForView(new com.hotpotsoft.hyperbird.game.HotPotGame(this), config);

		// Define the layout
		final RelativeLayout layout = new RelativeLayout(this);
		layout.addView(gameView, ViewGroup.LayoutParams.MATCH_PARENT,
			ViewGroup.LayoutParams.MATCH_PARENT);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
			ViewGroup.LayoutParams.MATCH_PARENT,
			ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		layout.addView(bannerAd, params);

		setContentView(layout);

		bannerAd.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				super.onAdClosed();
			}

			@Override
			public void onAdFailedToLoad(int errorCode) {
				super.onAdFailedToLoad(errorCode);
			}

			@Override
			public void onAdLeftApplication() {
				super.onAdLeftApplication();
			}

			@Override
			public void onAdOpened() {
				super.onAdOpened();
			}

			@Override
			public void onAdLoaded() {
				super.onAdLoaded();
			}
		});
	}

	public void setupAds() {
		bannerAd = new AdView(this);
		bannerAd.setVisibility(View.INVISIBLE);
		bannerAd.setBackgroundColor(0xff000000); // black
		bannerAd.setAdUnitId(BANNER_AD_UNIT_ID);
		bannerAd.setAdSize(AdSize.SMART_BANNER);
	}

	@Override
	public void showBannerAd() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if( bannerAd.getVisibility() != View.VISIBLE) {
					bannerAd.setVisibility(View.VISIBLE);
					AdRequest.Builder builder = new AdRequest.Builder();
					AdRequest ad = builder.build();
					bannerAd.loadAd(ad);
				}
			}
		});
	}

	@Override
	public void hideBannerAd() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if( bannerAd.getVisibility() != View.INVISIBLE) {
					bannerAd.setVisibility(View.INVISIBLE);
				}
			}
		});
	}
}
