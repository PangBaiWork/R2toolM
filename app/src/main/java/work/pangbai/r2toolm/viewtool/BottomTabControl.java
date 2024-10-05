package work.pangbai.r2toolm.viewtool;

import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;

import work.pangbai.r2toolm.databinding.ActivityMainJavaBinding;
import work.pangbai.r2toolm.databinding.LayoutTerminalBinding;


public class BottomTabControl extends BottomSheetBehavior.BottomSheetCallback implements TabLayout.OnTabSelectedListener {
    TabLayout tabLayout;
    ActivityMainJavaBinding binding;
    LayoutTerminalBinding cmdBinding;
    BottomSheetBehavior behavior;
    BottomTabCallBack bottomTabCallBack;
    
  public   interface  BottomTabCallBack{
    public void OnClose(int status);
  }

    public BottomTabControl(ActivityMainJavaBinding binding, LayoutTerminalBinding cmdBinding, MainViewPagerAdapter fragmentAdapter){
         this.binding=binding;
         this.tabLayout=binding.tabLayout;
         this.cmdBinding=cmdBinding;
         binding.viewpager.setAdapter(fragmentAdapter);

    }
    public void setup(BottomTabCallBack callBack){
      this.bottomTabCallBack=callBack;
        //Repair pager content reconstruction
        binding.viewpager.setOffscreenPageLimit(2);


        // tabLayout跟viewpager关联
        tabLayout.setupWithViewPager(binding.viewpager);

        behavior = BottomSheetBehavior.from(binding.bottomSheet);
        behavior.setHalfExpandedRatio(0.3F);
        behavior.addBottomSheetCallback(this);



        tabLayout.addOnTabSelectedListener(this);
    }
    @Override
    public void onStateChanged(@NonNull View bottomSheet, int newState) {
        if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
            binding.bottomBar.setVisibility(View.VISIBLE);
            bottomTabCallBack.OnClose(tabLayout.getSelectedTabPosition());
        } else {
            binding.bottomBar.setVisibility(View.GONE);
        }
        // focus on cmdview;
        if (newState == BottomSheetBehavior.STATE_EXPANDED) {
            if (tabLayout.getSelectedTabPosition() == 0) {
                cmdBinding.terminal.requestFocus();
            }
        }
    }

    @Override
    public void onSlide(@NonNull View bottomSheet, float slideOffset) {

    }



    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        // focus on cmdview;
        if (tab.getPosition() == 1) {
           cmdBinding.terminal.requestFocus();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}
