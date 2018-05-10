package com.yxld.yxchuangxin.ui.adapter.goods;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author yuan
 * @package com.yxld.yxchuangxin.ui.adapter.goods
 * @date 17-6-14
 * @description
 */

public class MarketFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> titles;
    public MarketFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return Math.min(fragments.size(),titles.size());
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    public void onDestroy(){
        fragments.clear();
        fragments = null;
        titles.clear();
        titles = null;
    }
}
