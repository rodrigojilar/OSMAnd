package net.osmand.plus.activities;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by Denis
 * on 26.01.2015.
 */
public class TabActivity extends ActionBarProgressActivity {

	public TabItem getTabIndicator(int resId, Class<?> fragment){
		return new TabItem(getString(resId), fragment);
	}

	public static class TabItem {
		public final CharSequence mTitle;
		public final Class<?> fragment;

		public TabItem(CharSequence mTitle, Class<?> fragment) {
			this.mTitle = mTitle;
			this.fragment = fragment;
		}

	}

	protected void setViewPagerAdapter(ViewPager pager, List<TabItem> items){
		pager.setAdapter(new OsmandFragmentPagerAdapter(getSupportFragmentManager(), items));
	}

	public static class OsmandFragmentPagerAdapter extends FragmentPagerAdapter {

		private List<TabItem> mTabs;

		public OsmandFragmentPagerAdapter(FragmentManager fm, List<TabItem> items) {
			super(fm);
			mTabs = items;
		}

		/**
		 * Return the {@link android.support.v4.app.Fragment} to be displayed at {@code position}.
		 * <p>
		 */
		@Override
		public Fragment getItem(int i) {
			try {
				return (Fragment) mTabs.get(i).fragment.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public int getCount() {
			return mTabs.size();
		}

		// BEGIN_INCLUDE (pageradapter_getpagetitle)
		/**
		 * Return the title of the item at {@code position}. This is important as what this method
		 * returns is what is displayed in the {@link com.example.android.common.view.SlidingTabLayout}.
		 * <p>
		 */
		@Override
		public CharSequence getPageTitle(int position) {
			return mTabs.get(position).mTitle;
		}
	}
}
