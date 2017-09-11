package com.itp341.singh.himmat.finalprojhimmatsinghitp341;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.itp341.singh.himmat.finalprojhimmatsinghitp341.AccountInfo.AccountPage;

/**
 * Created by himmatsingh on 4/30/17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ReligionListFragment tab1 = new ReligionListFragment();
                return tab1;
            case 1:
                MessageBoardFragment tab2 = new MessageBoardFragment();
                return tab2;
            case 2:
                AccountPage tab3 = new AccountPage();
                return tab3;
            case 3:
                AboutFragment tab4 = new AboutFragment();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
