package com.example.dhani.kholas.page;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.dhani.kholas.R;
import com.example.dhani.kholas.base.ObjectBox;
import com.example.dhani.kholas.dao.entity.Bookmark;
import com.example.dhani.kholas.dao.entity.Bookmark_;
import com.example.dhani.kholas.dao.service.BookmarkService;
import com.example.dhani.kholas.utils.Utils;
import com.example.dhani.kholas.adapter.GalleryStripAdapter;
import com.example.dhani.kholas.adapter.SlideShowPagerAdapter;
import com.example.dhani.kholas.model.GalleryItem;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

//Remember to implement  GalleryStripAdapter.GalleryStripCallBacks to fragment  for communication of fragment and GalleryStripAdapter
public class SlideShowFragment extends DialogFragment implements GalleryStripAdapter.GalleryStripCallBacks, ViewPager.OnPageChangeListener {

    //declare static variable which will serve as key of current position argument
    private static final String ARG_CURRENT_POSITION = "position";
    private static final String ARG_CURRENT_STATUS = "status";

    //Declare list of GalleryItems
    List<GalleryItem> galleryItems;

    SlideShowPagerAdapter mSlideShowPagerAdapter;
    //Deceleration of viewPager
    ViewPager mViewPagerGallery;

    private int mCurrentPosition;
    private int mStatus;

    int mPosisi;
    public SlideShowFragment() {
        // Required empty public constructor
    }

    Bookmark bookmark;
    BookmarkService bookmarkService;
    int target;
    int startHalaman;

    //This method will create new instance of SlideShowFragment
    public static SlideShowFragment newInstance(int position, int status) {
        SlideShowFragment fragment = new SlideShowFragment();
        //Create bundle
        Bundle args = new Bundle();
        //put Current Position in the bundle
        args.putInt(ARG_CURRENT_POSITION, position);
        args.putInt(ARG_CURRENT_STATUS, status);
        //set arguments of SlideShowFragment
        fragment.setArguments(args);
        //return fragment instance
        return fragment;
    }

    public void getToast(int position){
        Toast.makeText(getContext(),"tes"+position,Toast.LENGTH_SHORT).show();
        mCurrentPosition = position;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialise GalleryItems List
        galleryItems = new ArrayList<>();
        if (getArguments() != null) {
            //get Current selected position from arguments
            mCurrentPosition = getArguments().getInt(ARG_CURRENT_POSITION);
            mStatus = getArguments().getInt(ARG_CURRENT_STATUS);
            //get GalleryItems from activity
            galleryItems = ((MainActivity) getActivity()).galleryItems;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_silde_show, container, false);
        mViewPagerGallery = view.findViewById(R.id.viewPagerGallery);

        bookmark  = new Bookmark();
        bookmarkService = new BookmarkService();
        getBookmark();

        //Initialise View Pager Adapter
        mSlideShowPagerAdapter = new SlideShowPagerAdapter(getContext(), galleryItems);

        //set adapter to Viewpager
        mViewPagerGallery.setRotationY(180);
        mViewPagerGallery.setAdapter(mSlideShowPagerAdapter);
        mViewPagerGallery.addOnPageChangeListener(this);


        //tell viewpager to open currently selected item and pass position of current item
        setPagePosition(mCurrentPosition);

        if (mStatus == 1){
            mViewPagerGallery.setCurrentItem(mCurrentPosition);
        }
        else {
            if (mPosisi != 0){
                mPosisi = Integer.parseInt(getActivity().getIntent().getStringExtra("Halaman"));
                setPagePosition(mPosisi);
            }
        }
        return view;
    }



    //Overridden method by GalleryStripAdapter.GalleryStripCallBacks for communication on gallery strip item selected
    @Override
    public void onGalleryStripItemSelected(int position) {
        //set current item of viewpager
        mViewPagerGallery.setCurrentItem(position);
//        Toast.makeText(getContext(),"tes"+position,Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void setPagePosition(int position){
        if (position == 0){
            mViewPagerGallery.setCurrentItem(Utils.AL_FATIHAH);
        }else if (position == 1){
            mViewPagerGallery.setCurrentItem(Utils.AL_BAKARAH);
        }else if (position == 2){
            mViewPagerGallery.setCurrentItem(Utils.ALI_IMRAN);
        }else if (position == 3){
            mViewPagerGallery.setCurrentItem(Utils.AN_NISA);
        }else if (position == 4){
            mViewPagerGallery.setCurrentItem(Utils.AL_MAIDAH);
        }else if (position == 5){
            mViewPagerGallery.setCurrentItem(Utils.AL_ANAM);
        }else if (position == 6){
            mViewPagerGallery.setCurrentItem(Utils.AL_ARAF);
        }else if (position == 7){
            mViewPagerGallery.setCurrentItem(Utils.AL_ANFAL);
        }else if (position == 8){
            mViewPagerGallery.setCurrentItem(Utils.AT_TAUBAH);
        }else if (position == 9){
            mViewPagerGallery.setCurrentItem(Utils.YUNUS);
        }else if (position == 10){
            mViewPagerGallery.setCurrentItem(Utils.HUD);
        }else if (position == 11){
            mViewPagerGallery.setCurrentItem(Utils.YUSUF);
        }else if (position == 12){
            mViewPagerGallery.setCurrentItem(Utils.AR_RAD);
        }else if (position == 13){
            mViewPagerGallery.setCurrentItem(Utils.IBRAHIM);
        }else if (position == 14){
            mViewPagerGallery.setCurrentItem(Utils.AL_HIJR);
        }else if (position == 15){
            mViewPagerGallery.setCurrentItem(Utils.AN_NAHL);
        }else if (position == 16){
            mViewPagerGallery.setCurrentItem(Utils.AL_ISRA);
        }else if (position == 17){
            mViewPagerGallery.setCurrentItem(Utils.AL_KAHFI);
        }else if (position == 18){
            mViewPagerGallery.setCurrentItem(Utils.MARYAM);
        }else if (position == 19){
            mViewPagerGallery.setCurrentItem(Utils.TAHA);
        }else if (position == 20){
            mViewPagerGallery.setCurrentItem(Utils.AL_ANBIYA);
        }else if (position == 21){
            mViewPagerGallery.setCurrentItem(Utils.AL_HAJJ);
        }else if (position == 22){
            mViewPagerGallery.setCurrentItem(Utils.AL_MUKMINUN);
        }else if (position == 23){
            mViewPagerGallery.setCurrentItem(Utils.AN_NUR);
        }else if (position == 24){
            mViewPagerGallery.setCurrentItem(Utils.AL_FURQON);
        }else if (position == 25){
            mViewPagerGallery.setCurrentItem(Utils.AS_SYUARO);
        }else if (position == 26){
            mViewPagerGallery.setCurrentItem(Utils.AN_NAML);
        }else if (position == 27){
            mViewPagerGallery.setCurrentItem(Utils.AL_QASAS);
        }else if (position == 28){
            mViewPagerGallery.setCurrentItem(Utils.AL_ANKABUT);
        }else if (position == 29){
            mViewPagerGallery.setCurrentItem(Utils.AR_RUM);
        }else if (position == 30){
            mViewPagerGallery.setCurrentItem(Utils.AL_LUKMAN);
        }else if (position == 31){
            mViewPagerGallery.setCurrentItem(Utils.AS_SAJDAH);
        }else if (position == 32){
            mViewPagerGallery.setCurrentItem(Utils.AL_AHJAB);
        }else if (position == 33){
            mViewPagerGallery.setCurrentItem(Utils.AS_SABA);
        }else if (position == 34){
            mViewPagerGallery.setCurrentItem(Utils.FATIR);
        }else if (position == 35){
            mViewPagerGallery.setCurrentItem(Utils.YASIN);
        }else if (position == 36){
            mViewPagerGallery.setCurrentItem(Utils.AS_SHAFAT);
        }else if (position == 37){
            mViewPagerGallery.setCurrentItem(Utils.SAD);
        }else if (position == 38){
            mViewPagerGallery.setCurrentItem(Utils.AZ_ZUMAR);
        }else if (position == 39){
            mViewPagerGallery.setCurrentItem(Utils.GOFFIR);
        }else if (position == 40){
            mViewPagerGallery.setCurrentItem(Utils.FUSILAT);
        }else if (position == 41){
            mViewPagerGallery.setCurrentItem(Utils.AS_SYURA);
        }else if (position == 42){
            mViewPagerGallery.setCurrentItem(Utils.AZ_ZUHRUF);
        }else if (position == 43){
            mViewPagerGallery.setCurrentItem(Utils.AD_DUKHON);
        }else if (position == 44){
            mViewPagerGallery.setCurrentItem(Utils.AL_JASIAH);
        }else if (position == 45){
            mViewPagerGallery.setCurrentItem(Utils.AL_AHQOF);
        }else if (position == 46){
            mViewPagerGallery.setCurrentItem(Utils.MUHAMMAD);
        }else if (position == 47){
            mViewPagerGallery.setCurrentItem(Utils.AL_FATH);
        }else if (position == 48){
            mViewPagerGallery.setCurrentItem(Utils.HUJURAT);
        }else if (position == 49){
            mViewPagerGallery.setCurrentItem(Utils.QAF);
        }else if (position == 50){
            mViewPagerGallery.setCurrentItem(Utils.AZ_ZARIYAT);
        }else if (position == 51){
            mViewPagerGallery.setCurrentItem(Utils.AT_TUR);
        }else if (position == 52){
            mViewPagerGallery.setCurrentItem(Utils.AN_NAJM);
        }else if (position == 53){
            mViewPagerGallery.setCurrentItem(Utils.AL_QOMAR);
        }else if (position == 54){
            mViewPagerGallery.setCurrentItem(Utils.AR_RAHMAN);
        }else if (position == 55){
            mViewPagerGallery.setCurrentItem(Utils.AL_WAQIAH);
        }else if (position == 56){
            mViewPagerGallery.setCurrentItem(Utils.AL_HADID);
        }else if (position == 57){
            mViewPagerGallery.setCurrentItem(Utils.AL_MUJADILLAH);
        }else if (position == 58){
            mViewPagerGallery.setCurrentItem(Utils.AL_HASR);
        }else if (position == 59){
            mViewPagerGallery.setCurrentItem(Utils.AL_MUMTAHANAN);
        }else if (position == 60){
            mViewPagerGallery.setCurrentItem(Utils.AS_SHOF);
        }else if (position == 61){
            mViewPagerGallery.setCurrentItem(Utils.AL_JUMUAH);
        }else if (position == 62){
            mViewPagerGallery.setCurrentItem(Utils.AL_MUNAFIQUN);
        }else if (position == 63){
            mViewPagerGallery.setCurrentItem(Utils.AL_TAGOBUN);
        }else if (position == 64){
            mViewPagerGallery.setCurrentItem(Utils.AT_TALAQ);
        }else if (position == 65){
            mViewPagerGallery.setCurrentItem(Utils.AT_TAHRIM);
        }else if (position == 66){
            mViewPagerGallery.setCurrentItem(Utils.AL_MULK);
        }else if (position == 67){
            mViewPagerGallery.setCurrentItem(Utils.AL_QALAM);
        }else if (position == 68){
            mViewPagerGallery.setCurrentItem(Utils.AL_HAQOH);
        }else if (position == 69){
            mViewPagerGallery.setCurrentItem(Utils.AL_MAARIJ);
        }else if (position == 70){
            mViewPagerGallery.setCurrentItem(Utils.NUH);
        }else if (position == 71){
            mViewPagerGallery.setCurrentItem(Utils.JIN);
        }else if (position == 72){
            mViewPagerGallery.setCurrentItem(Utils.AL_MUZAMMIL);
        }else if (position == 73){
            mViewPagerGallery.setCurrentItem(Utils.AL_MUDATSIR);
        }else if (position == 74){
            mViewPagerGallery.setCurrentItem(Utils.AL_QIYAMAH);
        }else if (position == 75){
            mViewPagerGallery.setCurrentItem(Utils.AL_INSAN);
        }else if (position == 76){
            mViewPagerGallery.setCurrentItem(Utils.AL_MURSALAT);
        }else if (position == 77){
            mViewPagerGallery.setCurrentItem(Utils.AN_NABA);
        }else if (position == 78){
            mViewPagerGallery.setCurrentItem(Utils.AN_NAJIAT);
        }else if (position == 79){
            mViewPagerGallery.setCurrentItem(Utils.ABASSA);
        }else if (position == 80){
            mViewPagerGallery.setCurrentItem(Utils.AT_TAQWIR);
        }else if (position == 81){
            mViewPagerGallery.setCurrentItem(Utils.AL_INFITOR);
        }else if (position == 82){
            mViewPagerGallery.setCurrentItem(Utils.AL_MUTAFIFIN);
        }else if (position == 83){
            mViewPagerGallery.setCurrentItem(Utils.AL_INSIQOQ);
        }else if (position == 84){
            mViewPagerGallery.setCurrentItem(Utils.AL_BURUJ);
        }else if (position == 85){
            mViewPagerGallery.setCurrentItem(Utils.AT_TARIQ);
        }else if (position == 86){
            mViewPagerGallery.setCurrentItem(Utils.AL_ALA);
        }else if (position == 87){
            mViewPagerGallery.setCurrentItem(Utils.AL_GOSIAH);
        }else if (position == 88){
            mViewPagerGallery.setCurrentItem(Utils.AL_FAJR);
        }else if (position == 89){
            mViewPagerGallery.setCurrentItem(Utils.AL_BALAD);
        }else if (position == 90){
            mViewPagerGallery.setCurrentItem(Utils.ASY_SYAMS);
        }else if (position == 91){
            mViewPagerGallery.setCurrentItem(Utils.AL_LAIL);
        }else if (position == 92){
            mViewPagerGallery.setCurrentItem(Utils.AD_DUHA);
        }else if (position == 93){
            mViewPagerGallery.setCurrentItem(Utils.AL_INSYIROH);
        }else if (position == 94){
            mViewPagerGallery.setCurrentItem(Utils.AT_TIN);
        }else if (position == 95){
            mViewPagerGallery.setCurrentItem(Utils.AL_ALAQ);
        }else if (position == 96){
            mViewPagerGallery.setCurrentItem(Utils.AL_QODR);
        }else if (position == 97){
            mViewPagerGallery.setCurrentItem(Utils.AL_BAYYINAH);
        }else if (position == 98){
            mViewPagerGallery.setCurrentItem(Utils.AL_JALJALAH);
        }else if (position == 99){
            mViewPagerGallery.setCurrentItem(Utils.AL_ADIYAT);
        }else if (position == 100){
            mViewPagerGallery.setCurrentItem(Utils.AL_QORIAH);
        }else if (position == 101){
            mViewPagerGallery.setCurrentItem(Utils.AT_TAKASUR);
        }else if (position == 102){
            mViewPagerGallery.setCurrentItem(Utils.AL_ASR);
        }else if (position == 103){
            mViewPagerGallery.setCurrentItem(Utils.AL_HUMAJAH);
        }else if (position == 104){
            mViewPagerGallery.setCurrentItem(Utils.AL_FILL);
        }else if (position == 105){
            mViewPagerGallery.setCurrentItem(Utils.AL_QURAISH);
        }else if (position == 106){
            mViewPagerGallery.setCurrentItem(Utils.AL_MAUN);
        }else if (position == 107){
            mViewPagerGallery.setCurrentItem(Utils.AL_KAUSTAR);
        }else if (position == 108){
            mViewPagerGallery.setCurrentItem(Utils.AL_KAFIRUN);
        }else if (position == 109){
            mViewPagerGallery.setCurrentItem(Utils.AN_NASR);
        }else if (position == 110){
            mViewPagerGallery.setCurrentItem(Utils.AL_LAHAB);
        }else if (position == 111){
            mViewPagerGallery.setCurrentItem(Utils.AL_IKHLAS);
        }else if (position == 112){
            mViewPagerGallery.setCurrentItem(Utils.AL_FALAQ);
        }else if (position == 113){
            mViewPagerGallery.setCurrentItem(Utils.AN_NAS);
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        getToast(i);

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), getTheme()) {
            @Override
            public void onBackPressed() {
                //your code
//                Toast.makeText(getContext(),"back",Toast.LENGTH_SHORT).show();
                int posisi = mCurrentPosition +1;
                int sisa = target - posisi;
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                if (sisa != 0 && target != 0){
                    builder.setMessage("Apakah anda ingin keluar? \n TARGET ANDA "+target+" HALAMAN," +
                            "SISA "+sisa+" HALAMAN LAGI.");

                    List<Bookmark> bookmarkList = bookmarkService.findBookmark();
                    if (bookmarkList.size() > 0) {
                        for (int i = 0; i < bookmarkList.size(); i++) {
                            if (bookmarkList.get(i).isStatus() == true) {
                                bookmarkList.get(0).setLastRead(posisi);
                                bookmarkService.updateBookmark(bookmarkList);
                            }
                        }
                    }

                }else {
                    builder.setMessage("Apakah anda ingin keluar?");
                }

                builder.setTitle("Konfirmasi");

                builder.setCancelable(true);
                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        };

        return dialog;
    }

    public void getBookmark(){
        //get database by find bookmark with id =1
        List<Bookmark> bookmarkList = bookmarkService.findBookmark();
        if(bookmarkList.size() > 0){
            for (int i=0;i < bookmarkList.size() ; i++){
                if (bookmarkList.get(i).getTime() != null){
                    target = bookmarkList.get(0).getTarget();
                    startHalaman = bookmarkList.get(0).getPage();
                }
            }
        }
    }
}
