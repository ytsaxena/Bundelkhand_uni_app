package android.example.bunew.ui.courses;

import android.example.bunew.R;
import android.example.bunew.webviewcontroller;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.example.bunew.databinding.FragmentGalleryBinding;

import com.github.barteksc.pdfviewer.PDFView;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;
ProgressBar progressBar;
PDFView pdfView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        progressBar = root.findViewById(R.id.coursepb);
        progressBar.setVisibility(View.VISIBLE);

        pdfView = root.findViewById(R.id.pdfView);

        Handler handler = new Handler();
        new Thread(new Runnable() {
            public void run() {
                try{
                    Thread.sleep(3000);
                }
                catch (Exception e) { } // Just catch the InterruptedException

                // Now we use the Handler to post back to the main thread
                handler.post(new Runnable() {
                    public void run() {
                        // Set the View's visibility back on the main UI Thread
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();



        pdfView.fromAsset("course.pdf")
                .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .load();




//        webView = root.findViewById(R.id.coursesweb);
//       String url="https://bujhansi.ac.in/pdf/admission/ACPROGRAMME_2021_22.pdf";
//       String finalurl = "http://drive.google.com/viewerng/viewer?embedded=true&url="+url;
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setBuiltInZoomControls(true);
//
//        webView.setWebViewClient(new webviewcontroller());
/*        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setDomStorageEnabled(true);*/
//        webView.loadUrl("finalurl");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}